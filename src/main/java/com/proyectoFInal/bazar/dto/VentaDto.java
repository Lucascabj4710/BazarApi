package com.proyectoFInal.bazar.dto;

import com.proyectoFInal.bazar.model.Cliente;
import com.proyectoFInal.bazar.model.Producto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class VentaDto {

    private LocalDate fecha_venta;
    private Double total;
    private List<Producto> listaProductos;
    private Cliente cliente;

}
