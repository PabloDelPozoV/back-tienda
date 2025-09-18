package com.example.BackTienda.dto.PedidoDTOs;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoCreateDTO {

    @NotNull
    private Long clienteId;

    @NotNull
    private LocalDateTime fechaPedido;

    @NotBlank
    private String productoIds;

    @NotBlank
    private String cantidades;

    @NotNull
    private Float precio;

    @NotBlank
    private String estado;

    @NotBlank
    private String direccionEnvio;

    @NotBlank
    private String metodoPago;

}
