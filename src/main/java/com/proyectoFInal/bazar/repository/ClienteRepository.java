package com.proyectoFInal.bazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoFInal.bazar.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>  {

}
