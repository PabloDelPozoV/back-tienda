package com.example.BackTienda.dto.ProductoDTOs;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoCreateDTO {

    
    @NotBlank
    private String nombre;

    private String descripcion;

    @NotNull
    @Min(0)
    private BigDecimal precio;

    @NotNull
    @Min(0)
    private Integer stock;

    private String categoria;

    private String urlImagen;
    

}
