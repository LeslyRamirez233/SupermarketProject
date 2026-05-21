package com.aprendeconmigo.PruebaTecSupermercado.service;

import com.aprendeconmigo.PruebaTecSupermercado.dto.ProductoDTO;
import com.aprendeconmigo.PruebaTecSupermercado.mapper.Mapper;
import com.aprendeconmigo.PruebaTecSupermercado.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository repo;
    @Override
    public List<ProductoDTO> traerProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        return null;
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {
        return null;
    }

    @Override
    public void eliminarProducto(Long id) {

    }
}
