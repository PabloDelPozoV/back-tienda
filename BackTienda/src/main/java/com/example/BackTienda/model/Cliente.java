package com.example.BackTienda.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String codigo_postal;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private String telefono;


    public enum Rol {
            ADMIN,
            USER
        }
        
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol = Rol.USER;

    

    private LocalDateTime fechaRegistro;

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
}

public LocalDateTime getFechaRegistro() {
    return fechaRegistro;
}

} 