package com.example.BackTienda.service.impl;

import com.example.BackTienda.dto.ProductoDTOs.ProductoCreateDTO;
import com.example.BackTienda.dto.ProductoDTOs.ProductoResponseDTO;
import com.example.BackTienda.dto.ProductoDTOs.ProductoUpdateDTO;
import com.example.BackTienda.model.Producto;
import com.example.BackTienda.repository.ProductoRepository;
import com.example.BackTienda.service.IProductoService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements IProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAll()
        .stream()
        .map(this::convertEntityToProductResponseDTO)
        .toList();
    }

    @Override
    public Optional<ProductoResponseDTO> buscarProductoPorId(Long id) {
        return productoRepository.findById(id)
        .map(this::convertEntityToProductResponseDTO);
    }

    @Override
    public ProductoResponseDTO guardarProducto(ProductoCreateDTO productoDTO) {
        Producto producto = convertProductCreateDTOToEntity(productoDTO);
        productoRepository.save(producto);

        return convertEntityToProductResponseDTO(producto);
    }

    @Override
    public Optional<ProductoResponseDTO> actualizarProducto(Long id, ProductoUpdateDTO productoActualizado) {
        return productoRepository.findById(id)
        .map(productoExistente -> {
            if (productoActualizado.getDescripcion() != null) {
                productoExistente.setDescripcion(productoActualizado.getDescripcion());
            }
            if (productoActualizado.getPrecio() != null) {
                productoExistente.setPrecio(productoActualizado.getPrecio());
            }
            if (productoActualizado.getCategoria() != null) {
                productoExistente.setCategoria(productoActualizado.getCategoria());
            }
            if (productoActualizado.getStock() != null) {
                productoExistente.setStock(productoActualizado.getStock());
            }
            if (productoActualizado.getUrlImagen() != null) {
                productoExistente.setUrlImagen(productoActualizado.getUrlImagen());
            }
            if (productoActualizado.getActivo() != null) {
                productoExistente.setActivo(productoActualizado.getActivo());
            }

        Producto productoGuardado = productoRepository.save(productoExistente);
        return convertEntityToProductResponseDTO(productoGuardado);
        });


    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<ProductoResponseDTO> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria)
        .stream()
        .map(this::convertEntityToProductResponseDTO)
        .toList();
    }

    @Override
    public List<String> obtenerCategorias() {
        return productoRepository.findAllCategorias();
    }

    private ProductoResponseDTO convertEntityToProductResponseDTO(Producto producto) {
        ProductoResponseDTO productoDTO = new ProductoResponseDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setActivo(producto.getActivo());
        productoDTO.setCategoria(producto.getCategoria());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setStock(producto.getStock());
        productoDTO.setUrlImagen(producto.getUrlImagen());
        return productoDTO;
    }

    private Producto convertProductCreateDTOToEntity (ProductoCreateDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setUrlImagen(productoDTO.getUrlImagen());
        producto.setStock(productoDTO.getStock());
        
        return producto;
    }
} 