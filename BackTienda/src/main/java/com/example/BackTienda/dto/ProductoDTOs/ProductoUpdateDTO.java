package com.example.BackTienda.dto.ProductoDTOs;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoUpdateDTO {
    
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private String categoria;
    private String urlImagen;
    private Boolean activo;

}
