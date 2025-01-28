package com.proyectoFInal.bazar.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;
    private LocalDate fechaVenta;
    private double Total;

    @ManyToMany(targetEntity = Producto.class, fetch = FetchType.LAZY)
    @JoinTable(name = "ventas_productos", joinColumns = @JoinColumn(name = "venta"),
    inverseJoinColumns = @JoinColumn(name = "producto"))
    List<Producto> productos;


    @ManyToOne(targetEntity = Cliente.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Cliente cliente;

    @Builder
    public Venta(LocalDate fechaVenta, double Total, List<Producto> productos, Cliente cliente) {
        this.fechaVenta = fechaVenta;
        this.Total = Total;
        this.productos = productos;
        this.cliente = cliente;
    }

}
