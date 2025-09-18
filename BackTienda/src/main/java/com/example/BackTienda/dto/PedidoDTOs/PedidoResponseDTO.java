package com.example.BackTienda.dto.PedidoDTOs;

import java.time.LocalDateTime;
import java.util.List;

import com.example.BackTienda.dto.PedidoItemDTOs.PedidoItemResponseDTO;

import lombok.Data;

@Data
public class PedidoResponseDTO {

    private Long id;
    private Long clienteId;
    private LocalDateTime fechaPedido;
    private List<PedidoItemResponseDTO> items;
    private Float precio;
    private String estado;
    private String direccionEnvio;
    private String metodoPago;
    private LocalDateTime fechaRegistro;

}
