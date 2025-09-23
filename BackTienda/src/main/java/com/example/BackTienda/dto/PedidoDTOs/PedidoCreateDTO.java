package com.example.BackTienda.dto.PedidoDTOs;

import java.time.LocalDateTime;
import java.util.List;

import com.example.BackTienda.dto.PedidoItemDTOs.PedidoItemCreateDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoCreateDTO {

    @NotNull
    private Long clienteId;

    private LocalDateTime fechaPedido;

    @Valid
    @NotEmpty(message = "El pedido debe contener al menos un producto")
    private List<PedidoItemCreateDTO> items;

    @NotBlank
    private String estado;

    @NotBlank
    private String direccionEnvio;

    @NotBlank
    private String metodoPago;

}
