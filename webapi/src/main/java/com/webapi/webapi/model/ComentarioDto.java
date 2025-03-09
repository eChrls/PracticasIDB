package com.webapi.webapi.model;

import lombok.Data;

@Data
public class ComentarioDto {
    private String name; 
    private String email;
    private String numTlf; 
    private String mensaje;
    private Long idUsuario;
}
