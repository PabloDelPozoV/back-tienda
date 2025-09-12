package com.example.BackTienda.service.impl;

import com.example.BackTienda.model.Pedido;
import com.example.BackTienda.repository.PedidoRepository;
import com.example.BackTienda.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> buscarPedidosPorClienteId(Long id) {
        return pedidoRepository.findAllByClienteId(id);
    }
    @Override
    public Optional<Pedido> actualizarPedido(Long id, Pedido pedidoActualizado) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        if (pedidoExistente.isPresent()) {
        pedidoActualizado.setId(id);
        pedidoRepository.save(pedidoActualizado);
        return Optional.of(pedidoActualizado);
        }
        return Optional.empty();
    }
    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
} 