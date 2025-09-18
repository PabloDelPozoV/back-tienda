package com.example.BackTienda.dto.PedidoItemDTOs;

import lombok.Data;

@Data
public class PedidoItemResponseDTO {

    private Long id;
    private Long productoId;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
}
