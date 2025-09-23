package com.example.BackTienda.dto.PedidoItemDTOs;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PedidoItemResponseDTO {

    private Long id;
    private Long productoId;
    private String nombreProducto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}
