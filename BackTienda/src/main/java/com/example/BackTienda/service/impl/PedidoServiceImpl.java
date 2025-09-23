package com.example.BackTienda.service.impl;

import com.example.BackTienda.dto.PedidoDTOs.PedidoCreateDTO;
import com.example.BackTienda.dto.PedidoDTOs.PedidoResponseDTO;
import com.example.BackTienda.dto.PedidoDTOs.PedidoUpdateDTO;
import com.example.BackTienda.dto.PedidoItemDTOs.PedidoItemCreateDTO;
import com.example.BackTienda.dto.PedidoItemDTOs.PedidoItemResponseDTO;
import com.example.BackTienda.model.Cliente;
import com.example.BackTienda.model.Pedido;
import com.example.BackTienda.model.PedidoItem;
import com.example.BackTienda.model.Producto;
import com.example.BackTienda.repository.ClienteRepository;
import com.example.BackTienda.repository.PedidoRepository;
import com.example.BackTienda.repository.ProductoRepository;
import com.example.BackTienda.service.IPedidoService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements IPedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::convertEntityToResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
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
        Optional<Cliente> clienteOpt = clienteRepository.findById(pedidoDTO.getClienteId());

        if (clienteOpt.isEmpty()) {
            throw new IllegalArgumentException("No se ha encontrado el cliente");
        }

        List<Long> productsIds = pedidoDTO.getItems()
                .stream()
                .map(PedidoItemCreateDTO::getProductoId)
                .toList();
        List<Producto> productos = productoRepository.findAllById(productsIds);

        if (productsIds.size() > productos.size()) {
            throw new IllegalArgumentException("Algunos productos no existen");
        }

        Map<Long, Producto> productosMap = productos.stream()
                .collect(Collectors.toMap(producto -> producto.getId(), producto -> producto));

        List<PedidoItem> pedidoItems = new ArrayList<>();

        for (PedidoItemCreateDTO itemDTO : pedidoDTO.getItems()) {
            // Buscar el producto
            Producto producto = productosMap.get(itemDTO.getProductoId());

            // Crear el PedidoItem
            PedidoItem item = new PedidoItem();
            item.setProducto(producto);
            item.setCantidad(itemDTO.getCantidad());
            item.setPrecioUnitario(producto.getPrecio());
            item.calcularSubtotal();

            pedidoItems.add(item);
        }


        Pedido pedido = convertCreateDTOToEntity(pedidoDTO, clienteOpt);
        for (PedidoItem item : pedidoItems) {
            item.setPedido(pedido);
        }

        BigDecimal total = pedidoItems.stream()
        .map(PedidoItem::getSubtotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setItems(pedidoItems);
        pedido.setTotal(total);
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
        
        // Manejo seguro del cliente
        if (pedido.getCliente() != null) {
            dto.setClienteId(pedido.getCliente().getId());
        }
        
        // Campos básicos del pedido
        dto.setDireccionEnvio(pedido.getDireccionEnvio());
        dto.setEstado(pedido.getEstado());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setFechaRegistro(pedido.getFechaRegistro());
        dto.setTotal(pedido.getTotal());
        dto.setMetodoPago(pedido.getMetodoPago());
        
        // Mapear los items del pedido
        if (pedido.getItems() != null) {
            List<PedidoItemResponseDTO> itemsDTO = pedido.getItems().stream()
                    .map(this::convertPedidoItemToResponseDTO)
                    .collect(Collectors.toList());
            dto.setItems(itemsDTO);
        }
        
        return dto;
    }

    private Pedido convertCreateDTOToEntity(PedidoCreateDTO pedidoDTO, Optional<Cliente> clienteOpt) {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteOpt.get());
        pedido.setFechaPedido(pedidoDTO.getFechaPedido());
        pedido.setEstado(pedidoDTO.getEstado());
        pedido.setDireccionEnvio(pedidoDTO.getDireccionEnvio());
        pedido.setMetodoPago(pedidoDTO.getMetodoPago());
        // No asignamos id ni fechaRegistro (se generan automáticamente)
        return pedido;
    }

    private PedidoItemResponseDTO convertPedidoItemToResponseDTO(PedidoItem item) {
        PedidoItemResponseDTO dto = new PedidoItemResponseDTO();
        dto.setId(item.getId());
        dto.setProductoId(item.getProducto().getId());
        dto.setNombreProducto(item.getProducto().getNombre());
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }
}