package com.levelupgamer.backend.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class ProductoRequestDto {


    @NotBlank(message = "La imagen no puede estar vacía")
    private String imagen;

    @NotBlank(message = "El código es obligatorio")
    @Size(max = 50)
    private String codigo;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500)
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    private Boolean destacado = false;
}


