package com.aprendeconmigo.PruebaTecSupermercado.service;

import com.aprendeconmigo.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.aprendeconmigo.PruebaTecSupermercado.dto.VentaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService{


    @Override
    public List<DetalleVentaDTO> traerVentas() {
        return List.of();
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {
        return null;
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        return null;
    }

    @Override
    public void eliminarVenta(long id) {

    }
}
