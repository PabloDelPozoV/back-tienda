package com.example.BackTienda.service;

import com.example.BackTienda.model.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listarProductos();
    Optional<Producto> buscarProductoPorId(Long id);
    Producto guardarProducto(Producto producto);
    void eliminarProducto(Long id);
    List<Producto> buscarPorCategoria(String categoria);
    List<String> obtenerCategorias();
} 