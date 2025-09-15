package com.example.BackTienda.service;

import com.example.BackTienda.dto.PedidoDTOs.PedidoCreateDTO;
import com.example.BackTienda.dto.PedidoDTOs.PedidoResponseDTO;
import com.example.BackTienda.dto.PedidoDTOs.PedidoUpdateDTO;
import com.example.BackTienda.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    List<PedidoResponseDTO> listarPedidos();
    Optional<PedidoResponseDTO> buscarPedidoPorId(Long id);
    List<PedidoResponseDTO> buscarPedidosPorClienteId(Long id);
    Optional<PedidoResponseDTO> actualizarPedido(Long id, PedidoUpdateDTO pedidoActualizado);
    PedidoResponseDTO guardarPedido(PedidoCreateDTO pedidoDTO);
    void eliminarPedido(Long id);
} 