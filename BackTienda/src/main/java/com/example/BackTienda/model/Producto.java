package com.example.BackTienda.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String descripcion;
    
    @Column(nullable = false)
    private BigDecimal precio;
    
    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private String categoria;
    
    @Column(name = "url_imagen")
    private String urlImagen;
    
    @Column(nullable = false)
    private Boolean activo = true;
}
