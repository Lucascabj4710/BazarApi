package com.proyectoFInal.bazar.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    @ManyToMany(targetEntity = Producto.class, fetch = FetchType.LAZY)
    @JoinTable(name = "venta_Producto",
    joinColumns = @JoinColumn(name ="venta_id"),
    inverseJoinColumns = @JoinColumn(name="producto_id"))
    private List<Producto> listaProductos;
    
    @ManyToOne(targetEntity = Cliente.class)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    public Venta() {

    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente cliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.cliente = cliente;
    }

	public Long getCodigo_venta() {
		return codigo_venta;
	}

	public void setCodigo_venta(Long codigo_venta) {
		this.codigo_venta = codigo_venta;
	}

	public LocalDate getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(LocalDate fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
    
    
}
