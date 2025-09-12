package com.example.BackTienda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.BackTienda.model.Cliente;
import com.example.BackTienda.repository.ClienteRepository;
import com.example.BackTienda.service.IClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Optional<Cliente> actualizarCliente(Long id, Cliente clienteActualizado) {
        if (clienteRepository.existsById(id)) {
            clienteActualizado.setId(id);
            return Optional.of(clienteRepository.save(clienteActualizado));
        } else {
            return Optional.empty();
        }
    }
}
