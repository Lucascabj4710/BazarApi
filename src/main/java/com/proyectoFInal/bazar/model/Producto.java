package com.proyectoFInal.bazar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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


    public Producto() {
    }

    public Producto(Double cantidad_disponible, Double costo, String marca, String nombre, Long codigoProducto) {
        this.cantidadDisponible = cantidad_disponible;
        this.costo = costo;
        this.marca = marca;
        this.nombre = nombre;
        this.codigoProducto = codigoProducto;
    }

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getCantidad_disponible() {
		return cantidadDisponible;
	}

	public void setCantidad_disponible(Double cantidad_disponible) {
		this.cantidadDisponible = cantidad_disponible;
	}
    
    
}
