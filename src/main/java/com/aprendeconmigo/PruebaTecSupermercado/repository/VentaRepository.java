package com.aprendeconmigo.PruebaTecSupermercado.repository;

import com.aprendeconmigo.PruebaTecSupermercado.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
