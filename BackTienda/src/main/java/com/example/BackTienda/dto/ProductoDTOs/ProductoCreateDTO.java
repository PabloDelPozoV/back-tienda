package com.example.BackTienda.dto.ProductoDTOs;

import lombok.Data;

@Data
public class ProductoCreateDTO {

    

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String categoria;
    private String urlImagen;
    

}
