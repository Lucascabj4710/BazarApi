package com.proyectoFInal.bazar.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class ProductoDtoRequest {

    @NotBlank
    @Size(min = 3, max = 30)
    private String nombre;

    @NotBlank(message = "La marca no puede ser un valor nulo, ni estar vacio")
    @Size(min = 2, max = 60, message = "La marca debe tener entre 2 y 60 caracteres")
    private String marca;

    @NotNull
    @Min(value = 0, message = "El costo no puede ser un valor negativo")
    private Double costo;

    @NotNull
    @Min(value = 0, message = "La cantidad no puede ser un valor negativo")
    private Double cantidadDisponible;

}
