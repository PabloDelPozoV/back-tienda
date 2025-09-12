package com.example.BackTienda.service;

import com.example.BackTienda.model.Pedido;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    List<Pedido> listarPedidos();
    Optional<Pedido> buscarPedidoPorId(Long id);
    List<Pedido> buscarPedidosPorClienteId(Long id);
    Optional<Pedido> actualizarPedido(Long id, Pedido pedidoActualizado);
    Pedido guardarPedido(Pedido pedido);
    void eliminarPedido(Long id);
} 