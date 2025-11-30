package com.levelupgamer.backend.dto;

import lombok.Data;

@Data
public class ProductoResponseDto {
    private Long id;
    private String imagen;
    private String codigo;
    private String categoria;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Boolean destacado;

}
