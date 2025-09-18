package com.example.BackTienda.service.impl;

import com.example.BackTienda.dto.PedidoDTOs.PedidoCreateDTO;
import com.example.BackTienda.dto.PedidoDTOs.PedidoResponseDTO;
import com.example.BackTienda.dto.PedidoDTOs.PedidoUpdateDTO;
import com.example.BackTienda.model.Pedido;
import com.example.BackTienda.repository.PedidoRepository;
import com.example.BackTienda.service.IPedidoService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements IPedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::convertEntityToResponseDTO)
                .toList();
    }

    @Override
    public Optional<PedidoResponseDTO> buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(this::convertEntityToResponseDTO);
    }

    @Override
    public List<PedidoResponseDTO> buscarPedidosPorClienteId(Long id) {
        return pedidoRepository.findAllByClienteId(id)
                .stream()
                .map(this::convertEntityToResponseDTO)
                .toList();
    }

    @Override
    public Optional<PedidoResponseDTO> actualizarPedido(Long id, PedidoUpdateDTO pedidoActualizado) {
        return pedidoRepository.findById(id)
            .map(pedidoExistente -> {
                // Actualizar solo los campos que vienen en el DTO
                if (pedidoActualizado.getEstado() != null) {
                    pedidoExistente.setEstado(pedidoActualizado.getEstado());
                }
                if (pedidoActualizado.getDireccionEnvio() != null) {
                    pedidoExistente.setDireccionEnvio(pedidoActualizado.getDireccionEnvio());
                }
                if (pedidoActualizado.getMetodoPago() != null) {
                    pedidoExistente.setMetodoPago(pedidoActualizado.getMetodoPago());
                }
                
                Pedido pedidoGuardado = pedidoRepository.save(pedidoExistente);
                return convertEntityToResponseDTO(pedidoGuardado);
            });
    }

    @Override
    public PedidoResponseDTO guardarPedido(PedidoCreateDTO pedidoDTO) {
        Pedido pedido = convertCreateDTOToEntity(pedidoDTO);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        return convertEntityToResponseDTO(pedidoGuardado);
    }

    @Override
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    private PedidoResponseDTO convertEntityToResponseDTO(Pedido pedido) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(pedido.getId());
        dto.setClienteId(pedido.getCliente().getId());
        dto.setDireccionEnvio(pedido.getDireccionEnvio());
        dto.setEstado(pedido.getEstado());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setFechaRegistro(pedido.getFechaRegistro());
        dto.setPrecio(pedido.getTotal());
        dto.setMetodoPago(pedido.getMetodoPago());
        return dto;
    };

    private Pedido convertCreateDTOToEntity(PedidoCreateDTO pedidoDTO){
        Pedido pedido = new Pedido();
        pedido.setCliente(pedidoDTO.getCliente());
        pedido.setFechaPedido(pedidoDTO.getFechaPedido());
        pedido.setTotal(pedidoDTO.getPrecio());
        pedido.setEstado(pedidoDTO.getEstado());
        pedido.setDireccionEnvio(pedidoDTO.getDireccionEnvio());
        pedido.setMetodoPago(pedidoDTO.getMetodoPago());
        // No asignamos id ni fechaRegistro (se generan automáticamente)
        return pedido;
    }
}