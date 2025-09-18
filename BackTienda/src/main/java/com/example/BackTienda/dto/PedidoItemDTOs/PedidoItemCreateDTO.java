package com.example.BackTienda.dto.PedidoItemDTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PedidoItemCreateDTO {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @Positive
    private int cantidad;
}
