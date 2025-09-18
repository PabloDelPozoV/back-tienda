package com.example.BackTienda.service;

import com.example.BackTienda.dto.ClienteDTOs.ClienteCreateDTO;
import com.example.BackTienda.dto.ClienteDTOs.ClienteResponseDTO;
import com.example.BackTienda.dto.ClienteDTOs.ClienteUpdateDTO;
import com.example.BackTienda.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<ClienteResponseDTO> listarClientes();
    Optional<ClienteResponseDTO> buscarClientePorId(Long id);
    ClienteResponseDTO guardarCliente(ClienteCreateDTO clienteDTO);
    Optional<ClienteResponseDTO> actualizarCliente (Long id, ClienteUpdateDTO clienteActualizado);
    void eliminarCliente(Long id);
} 