package com.example.BackTienda.controller;

import com.example.BackTienda.dto.ProductoDTOs.ProductoCreateDTO;
import com.example.BackTienda.dto.ProductoDTOs.ProductoResponseDTO;
import com.example.BackTienda.dto.ProductoDTOs.ProductoUpdateDTO;
import com.example.BackTienda.model.Producto;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import com.example.BackTienda.service.IProductoService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final IProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public List<ProductoResponseDTO> obtenerProductos() {
        return productoService.listarProductos();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorId(@PathVariable Long id) {
        Optional<ProductoResponseDTO> producto = productoService.buscarProductoPorId(id);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener productos por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerProductosPorCategoria(@PathVariable String categoria) {
        List<ProductoResponseDTO> productos = productoService.buscarPorCategoria(categoria);
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

    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody ProductoCreateDTO productoDTO) {
        ProductoResponseDTO nuevoProducto = productoService.guardarProducto(productoDTO);
        return ResponseEntity.ok(nuevoProducto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long id,
            @RequestBody ProductoUpdateDTO productoActualizado) {

        Optional<ProductoResponseDTO> productoActualizadoOpt = productoService.actualizarProducto(id,
                productoActualizado);
        return productoActualizadoOpt.map(ResponseEntity::ok)
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
