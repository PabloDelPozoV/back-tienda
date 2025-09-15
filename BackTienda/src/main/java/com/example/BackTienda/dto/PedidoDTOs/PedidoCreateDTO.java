package com.example.BackTienda.dto.PedidoDTOs;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PedidoCreateDTO {

    private Long clienteId;
    private LocalDateTime fechaPedido;
    private String productoIds;
    private String cantidades;
    private Float precio;
    private String estado;
    private String direccionEnvio;
    private String metodoPago;

}
