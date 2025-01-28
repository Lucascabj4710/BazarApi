package com.proyectoFInal.bazar.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
public class ClienteDto {

    @NotBlank(message = "El apellido no puede estar vacio")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;
    @NotNull(message = "El dni no puede estar vacio")
    @Min(value = 0)
    @Column(unique = true)
    private String dni;


}
