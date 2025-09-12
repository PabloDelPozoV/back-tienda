package com.example.BackTienda.repository;

import com.example.BackTienda.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUsername(String username);
    Optional<Cliente> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
} 