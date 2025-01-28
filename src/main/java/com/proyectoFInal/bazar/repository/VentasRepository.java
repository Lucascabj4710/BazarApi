package com.proyectoFInal.bazar.repository;

import com.proyectoFInal.bazar.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentasRepository extends JpaRepository<Venta, Long> {
}
