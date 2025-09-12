package com.example.BackTienda.service;

import com.example.BackTienda.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> listarClientes();
    Optional<Cliente> buscarClientePorId(Long id);
    Cliente guardarCliente(Cliente cliente);
    Optional<Cliente> actualizarCliente (Long id, Cliente clienteActualizado);
    void eliminarCliente(Long id);
} 