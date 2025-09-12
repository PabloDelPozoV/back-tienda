package com.example.BackTienda.controller;

import com.example.BackTienda.model.Pedido;
import com.example.BackTienda.service.impl.PedidoServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {


    private final PedidoServiceImpl pedidoService;

    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPedidoPorId(id);
        return pedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorClienteId(@PathVariable("id") Long clienteId) {
        List<Pedido> pedidos = pedidoService.buscarPedidosPorClienteId(clienteId);
        if (pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.guardarPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoActualizado) {
        Optional<Pedido> pedidoActualizadoOpt = pedidoService.actualizarPedido(id, pedidoActualizado);
            return pedidoActualizadoOpt.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();

    }
}