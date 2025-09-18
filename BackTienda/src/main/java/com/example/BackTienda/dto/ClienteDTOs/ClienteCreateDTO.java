package com.example.BackTienda.dto.ClienteDTOs;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotBlank
    @Size(min= 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank
    private String direccion;

    @NotBlank
    private String ciudad;

    @NotBlank
    private String codigo_postal;

    @NotBlank
    private String pais;

    private String telefono;




}
