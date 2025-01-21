package com.proyectoFInal.bazar.repository;

import com.proyectoFInal.bazar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
