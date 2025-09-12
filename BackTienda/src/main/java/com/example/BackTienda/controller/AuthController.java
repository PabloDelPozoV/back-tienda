package com.example.BackTienda.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BackTienda.model.Cliente;
import com.example.BackTienda.repository.ClienteRepository;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/auth")



public class AuthController {

    @Autowired
    private ClienteRepository clienteRepo;

    // REGISTRO
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Cliente cliente) {
        if (clienteRepo.existsByUsername(cliente.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario ya existe");
        }

        cliente.setRol(Cliente.Rol.USER); // rol por defecto
        cliente.setFechaRegistro(LocalDateTime.now());
        clienteRepo.save(cliente);

        return ResponseEntity.ok("Registro correcto");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> datos, HttpSession session) {
        String username = datos.get("username");
        String password = datos.get("password");

        Optional<Cliente> clienteOpt = clienteRepo.findByUsername(username);
        if (clienteOpt.isPresent() && clienteOpt.get().getPassword().equals(password)) {
            Cliente cliente = clienteOpt.get();
            session.setAttribute("clienteId", cliente.getId());
            session.setAttribute("rol", cliente.getRol().name());
            return ResponseEntity.ok("Login correcto");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

    // LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Sesión cerrada");
    }

    // PERFIL
    @GetMapping("/me")
    public ResponseEntity<Cliente> perfil(HttpSession session) {
        Long clienteId = (Long) session.getAttribute("clienteId");
        if (clienteId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return clienteRepo.findById(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
