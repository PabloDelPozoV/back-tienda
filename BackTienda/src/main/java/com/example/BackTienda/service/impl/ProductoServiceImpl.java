package com.example.BackTienda.service.impl;

import com.example.BackTienda.model.Producto;
import com.example.BackTienda.repository.ProductoRepository;
import com.example.BackTienda.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    @Override
    public List<String> obtenerCategorias() {
        return productoRepository.findAllCategorias();
    }
} 