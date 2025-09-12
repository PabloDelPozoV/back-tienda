package com.example.BackTienda.controller;

import com.example.BackTienda.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import com.example.BackTienda.service.IProductoService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")

public class ProductoController {

    @Autowired
    private IProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.listarProductos();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarProductoPorId(id);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener productos por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable String categoria) {
        List<Producto> productos = productoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    // Obtener todas las categorías disponibles
    @GetMapping("/categorias")
    public ResponseEntity<List<String>> obtenerCategorias() {
        List<String> categorias = productoService.obtenerCategorias();
        return ResponseEntity.ok(categorias);
    }

    // Crear un nuevo producto
    @PostMapping

    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id,
            @RequestBody Producto productoActualizado) {
        return productoService.buscarProductoPorId(id)
                .map(productoExistente -> {
                    productoActualizado.setId(id);
                    return ResponseEntity.ok(productoService.guardarProducto(productoActualizado));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        return productoService.buscarProductoPorId(id)
                .map(producto -> {
                    productoService.eliminarProducto(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
