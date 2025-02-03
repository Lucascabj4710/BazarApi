package com.proyectoFInal.bazar.dto;

import com.proyectoFInal.bazar.model.Cliente;
import com.proyectoFInal.bazar.model.Producto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data @Builder
public class VentaDto {

    @NotNull(message = "La fecha no debe ser nula")
    private LocalDate fecha_venta;

    @Valid
    private List<ProductoDto> listaProductos;

    private Cliente cliente;

}
