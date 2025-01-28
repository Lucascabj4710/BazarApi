package com.proyectoFInal.bazar.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    private String nombre;
    private String apellido;
    private String dni;
    @OneToMany(targetEntity = Venta.class, fetch = FetchType.LAZY,  mappedBy = "cliente")
	@JsonBackReference
    private List<Venta> ventas;

	public Cliente() {}

	@Builder
	public Cliente(String nombre, String apellido, String dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

}
