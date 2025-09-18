package com.example.BackTienda.dto.ClienteDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteUpdateDTO {

    private String username;

    @Size(min= 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @Email(message = "El email debe tener un formato válido")
    private String email;

    private String direccion;

    private String ciudad;

    private String codigo_postal;

    private String pais;
    
    private String telefono;

}
