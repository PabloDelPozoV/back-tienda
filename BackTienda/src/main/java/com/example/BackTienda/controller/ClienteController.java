package com.example.BackTienda.controller;

import com.example.BackTienda.dto.ClienteDTOs.ClienteCreateDTO;
import com.example.BackTienda.dto.ClienteDTOs.ClienteResponseDTO;
import com.example.BackTienda.dto.ClienteDTOs.ClienteUpdateDTO;
import com.example.BackTienda.service.IClienteService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteService clienteService;

    @GetMapping
    public List<ClienteResponseDTO> obtenerClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtenerClientePorId(@PathVariable Long id) {
        Optional<ClienteResponseDTO> cliente = clienteService.buscarClientePorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody ClienteCreateDTO clienteDTO) {
        ClienteResponseDTO nuevoCliente = clienteService.guardarCliente(clienteDTO);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteUpdateDTO clienteActualizado) {
        Optional<ClienteResponseDTO> clienteActualizadoOpt = clienteService.actualizarCliente(id, clienteActualizado);
        return clienteActualizadoOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}