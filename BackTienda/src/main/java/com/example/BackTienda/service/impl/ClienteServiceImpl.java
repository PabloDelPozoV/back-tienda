package com.example.BackTienda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.BackTienda.dto.ClienteDTOs.ClienteCreateDTO;
import com.example.BackTienda.dto.ClienteDTOs.ClienteResponseDTO;
import com.example.BackTienda.dto.ClienteDTOs.ClienteUpdateDTO;
import com.example.BackTienda.model.Cliente;
import com.example.BackTienda.repository.ClienteRepository;
import com.example.BackTienda.service.IClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteResponseDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::convertEntityToResponseDTO)
                .toList();
    }

    @Override
    public Optional<ClienteResponseDTO> buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(this::convertEntityToResponseDTO);
    }

    @Override
    public ClienteResponseDTO guardarCliente(ClienteCreateDTO clienteDTO) {
        Cliente cliente = convertCreateDTOToEntity(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return convertEntityToResponseDTO(clienteGuardado);

    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Optional<ClienteResponseDTO> actualizarCliente(Long id, ClienteUpdateDTO clienteActualizado) {
        return clienteRepository.findById(id)
        .map(cliente -> {
            if (clienteActualizado.getCiudad() != null) {
                cliente.setCiudad(clienteActualizado.getCiudad());
            }
            if (clienteActualizado.getUsername() != null) {
                cliente.setUsername(clienteActualizado.getUsername());
            }
            if (clienteActualizado.getPassword() != null) {
                cliente.setPassword(clienteActualizado.getPassword());
            }
            if (clienteActualizado.getEmail() != null) {
                cliente.setEmail(clienteActualizado.getEmail());
            }
            if (clienteActualizado.getDireccion() != null) {
                cliente.setDireccion(clienteActualizado.getDireccion());
            }
            if (clienteActualizado.getCodigo_postal() != null) {
                cliente.setCodigo_postal(clienteActualizado.getCodigo_postal());
            }
            if (clienteActualizado.getPais() != null) {
                cliente.setPais(clienteActualizado.getPais());
            }
            if (clienteActualizado.getTelefono() != null) {
                cliente.setTelefono(clienteActualizado.getTelefono());
            }
            Cliente clienteNuevo = clienteRepository.save(cliente);
            return convertEntityToResponseDTO(clienteNuevo);
            
        });
        
    }

    private ClienteResponseDTO convertEntityToResponseDTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellidos(cliente.getApellidos());
        dto.setCiudad(cliente.getCiudad());
        dto.setCodigo_postal(cliente.getCodigo_postal());
        dto.setDireccion(cliente.getDireccion());
        dto.setEmail(cliente.getEmail());
        dto.setPais(cliente.getPais());
        dto.setTelefono(cliente.getTelefono());
        dto.setUsername(cliente.getUsername());

        return dto;
    }

    private Cliente convertCreateDTOToEntity(ClienteCreateDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setApellidos(dto.getApellidos());
        cliente.setNombre(dto.getNombre());
        cliente.setCiudad(dto.getCiudad());
        cliente.setCodigo_postal(dto.getCodigo_postal());
        cliente.setDireccion(dto.getDireccion());
        cliente.setEmail(dto.getEmail());
        cliente.setPais(dto.getPais());
        cliente.setPassword(dto.getPassword());
        cliente.setTelefono(dto.getTelefono());
        cliente.setUsername(dto.getUsername());

        return cliente;
    }
}
