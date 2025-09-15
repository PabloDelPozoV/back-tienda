package com.example.BackTienda.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido;

    @Column(name = "producto_ids")
    private String productoIds;

    @Column(name = "cantidades")
    private String cantidades;

    @Column(name = "precio")
    private Float precio;

    @Column(nullable = false)
    private String estado;

    @Column(name = "direccion_envio")
    private String direccionEnvio;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
    }
}
