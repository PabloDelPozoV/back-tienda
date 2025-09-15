package com.example.BackTienda.controller;

import com.example.BackTienda.dto.PedidoDTOs.PedidoCreateDTO;
import com.example.BackTienda.dto.PedidoDTOs.PedidoResponseDTO;
import com.example.BackTienda.dto.PedidoDTOs.PedidoUpdateDTO;
import com.example.BackTienda.model.Pedido;
import com.example.BackTienda.service.IPedidoService;
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


    private final IPedidoService pedidoService;

    @GetMapping
    public List<PedidoResponseDTO> obtenerPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> obtenerPedidoPorId(@PathVariable Long id) {
        Optional<PedidoResponseDTO> pedido = pedidoService.buscarPedidoPorId(id);
        return pedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<PedidoResponseDTO>> obtenerPedidosPorClienteId(@PathVariable("id") Long clienteId) {
        List<PedidoResponseDTO> pedidos = pedidoService.buscarPedidosPorClienteId(clienteId);
        if (pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crearPedido(@RequestBody PedidoCreateDTO pedidoDTO) {
        PedidoResponseDTO nuevoPedido = pedidoService.guardarPedido(pedidoDTO);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> actualizarPedido(@PathVariable Long id, @RequestBody PedidoUpdateDTO pedidoActualizado) {
        Optional<PedidoResponseDTO> pedidoActualizadoOpt = pedidoService.actualizarPedido(id, pedidoActualizado);
            return pedidoActualizadoOpt.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();

    }
}