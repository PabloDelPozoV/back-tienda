package com.example.BackTienda.dto.ProductoDTOs;

import lombok.Data;

@Data
public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String categoria;
    private String urlImagen;
    private Boolean activo;
}
