package com.example.BackTienda.controller;

import com.example.BackTienda.model.Cliente;
import com.example.BackTienda.service.impl.ClienteServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteServiceImpl clienteService;

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarClientePorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {

        if (cliente.getRol() == null) {
            cliente.setRol(Cliente.Rol.USER); // por defecto
        }

        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        Optional<Cliente> clienteActualizadoOpt = clienteService.actualizarCliente(id, clienteActualizado);
        return clienteActualizadoOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}