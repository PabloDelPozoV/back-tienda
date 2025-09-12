package com.example.BackTienda.repository;

import com.example.BackTienda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(String categoria);
    
    @Query("SELECT DISTINCT p.categoria FROM Producto p ORDER BY p.categoria")
    List<String> findAllCategorias();
} 