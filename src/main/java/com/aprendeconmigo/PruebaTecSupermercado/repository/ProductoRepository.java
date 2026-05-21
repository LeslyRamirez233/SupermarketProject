package com.aprendeconmigo.PruebaTecSupermercado.repository;

import com.aprendeconmigo.PruebaTecSupermercado.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
