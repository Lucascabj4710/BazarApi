package com.proyectoFInal.bazar.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity @Data @Builder
public class Producto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long codigoProducto;
    private String nombre;
    private String marca;
    private Double costo;

	@Column(name = "cantidad_disponible")
    private Double cantidadDisponible;


    
    
}
