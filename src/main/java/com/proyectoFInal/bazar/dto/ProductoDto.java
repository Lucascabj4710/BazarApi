package com.proyectoFInal.bazar.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Builder
@Validated
@Data
@AllArgsConstructor
public class ProductoDto {

    @NotNull
    @Min(value = 0, message = "La cantidad no puede ser un valor negativo")
    private Long id;

    @NotNull
    @Min(value = 0, message = "La cantidad no puede ser un valor negativo")
    private int cantidad;
}
