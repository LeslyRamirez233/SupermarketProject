package com.aprendeconmigo.PruebaTecSupermercado.service;

import com.aprendeconmigo.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.aprendeconmigo.PruebaTecSupermercado.dto.VentaDTO;
import com.aprendeconmigo.PruebaTecSupermercado.exception.NotFoundException;
import com.aprendeconmigo.PruebaTecSupermercado.mapper.Mapper;
import com.aprendeconmigo.PruebaTecSupermercado.model.DetalleVenta;
import com.aprendeconmigo.PruebaTecSupermercado.model.Producto;
import com.aprendeconmigo.PruebaTecSupermercado.model.Sucursal;
import com.aprendeconmigo.PruebaTecSupermercado.model.Venta;
import com.aprendeconmigo.PruebaTecSupermercado.repository.ProductoRepository;
import com.aprendeconmigo.PruebaTecSupermercado.repository.SucursalRepository;
import com.aprendeconmigo.PruebaTecSupermercado.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private SucursalRepository sucursalRepo;

    @Override
    public List<VentaDTO> traerVentas() {

        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();

        VentaDTO dto;
        for (Venta v : ventas) {
            dto = Mapper.toDTO(v);
            ventasDto.add (dto);
        }

        return ventasDto;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {

        if (ventaDto == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Debe de incluir al menos un producto");

        //Buscar la sucursal
        Sucursal suc = sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);
        if (suc == null) {
            throw new NotFoundException("Sucursal no encontrada");
        }

        //Crear la venta
        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventaDto.getTotal());

        // La lista de detalles
        // --> Aca estan los productos
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetalleVentaDTO deTDO : ventaDto.getDetalle()) {
            // Buscar producto por id (tu detDTO usa id como id de producto)
            Producto p = productoRepo.findByNombre(deTDO.getNombreProd()).orElse(null);
            if (p == null)
            {throw new RuntimeException("Producto no encontrado: " + deTDO.getNombreProd());}

                //Crear Detalle
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProd(p);
            detalleVent.setPrecio(deTDO.getPrecio());
            detalleVent.setCantProd(deTDO.getCantProd());
            detalleVent.setVenta(vent);

            detalles.add(detalleVent);
            totalCalculado = totalCalculado+(deTDO.getPrecio()*deTDO.getCantProd());
        }
//seteamosla lista de detalle venta
        vent.setDetalle(detalles);

        // guardamos en la base de datos
        vent = ventaRepo.save(vent);

        //Mapeo de salida
        VentaDTO ventaSalida = Mapper.toDTO(vent);

        return ventaSalida;
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        //buscar si la venta existe para actualizarla
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");

        if (ventaDto.getFecha()!=null) {
            v.setFecha(ventaDto.getFecha());
        }
        if(ventaDto.getEstado()!=null) {
            v.setEstado(ventaDto.getEstado());
        }
        if (ventaDto.getTotal()!=null) {
            v.setTotal(ventaDto.getTotal());
        }
        if (ventaDto.getIdSucursal()!=null) {
            Sucursal suc = sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }
        ventaRepo.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(long id) {

        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");
        ventaRepo.delete(v);

    }
}
