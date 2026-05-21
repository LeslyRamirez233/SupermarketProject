package com.aprendeconmigo.PruebaTecSupermercado.service;

import com.aprendeconmigo.PruebaTecSupermercado.dto.SucursalDTO;
import com.aprendeconmigo.PruebaTecSupermercado.model.Sucursal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService{
    @Override
    public List<SucursalDTO> traerSucursales() {
        return List.of();
    }

    @Override
    public SucursalDTO crearSucursal(Sucursal sucursalDto) {
        return null;
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto) {
        return null;
    }

    @Override
    public void eliminarSucursal(Long id) {

    }
}
