package com.example.BackTienda.dto.ClienteDTOs;

import com.example.BackTienda.model.Cliente.Rol;

import lombok.Data;

@Data
public class ClienteResponseDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String username;
    private String email;
    private String direccion;
    private String ciudad;
    private String codigo_postal;
    private String pais;
    private String telefono;
    private Rol rol;

}
