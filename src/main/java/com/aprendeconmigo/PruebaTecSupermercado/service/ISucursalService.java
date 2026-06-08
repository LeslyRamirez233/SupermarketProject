package com.aprendeconmigo.PruebaTecSupermercado.service;

import com.aprendeconmigo.PruebaTecSupermercado.dto.SucursalDTO;
import com.aprendeconmigo.PruebaTecSupermercado.model.Sucursal;

import java.util.List;

public interface ISucursalService {

List<SucursalDTO> traerSucursales();
SucursalDTO crearSucursal(SucursalDTO sucursalDto);
SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto);
void eliminarSucursal(Long id);
}
