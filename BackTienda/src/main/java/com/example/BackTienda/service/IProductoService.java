package com.example.BackTienda.service;

import com.example.BackTienda.dto.ProductoDTOs.ProductoCreateDTO;
import com.example.BackTienda.dto.ProductoDTOs.ProductoResponseDTO;
import com.example.BackTienda.dto.ProductoDTOs.ProductoUpdateDTO;
import com.example.BackTienda.model.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<ProductoResponseDTO> listarProductos();
    Optional<ProductoResponseDTO> buscarProductoPorId(Long id);
    ProductoResponseDTO guardarProducto(ProductoCreateDTO productoDTO);
    Optional<ProductoResponseDTO> actualizarProducto(Long id, ProductoUpdateDTO productoActualizado);
    void eliminarProducto(Long id);
    List<ProductoResponseDTO> buscarPorCategoria(String categoria);
    List<String> obtenerCategorias();
} 