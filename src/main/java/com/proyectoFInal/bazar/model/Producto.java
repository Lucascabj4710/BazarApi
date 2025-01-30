package com.proyectoFInal.bazar.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@Entity
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
