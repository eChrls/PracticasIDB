package com.webapi.webapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComentario;
    private String name;
    private String email;
    private String numTlf;
    private String mensaje;
    @JoinColumn(name = "idUsuario")
    @ManyToOne
    private UsuarioEntity usuario;

    public ComentarioEntity(String name, String email, String numTlf, String mensaje, UsuarioEntity usuario) {

        this.name = name;
        this.email = email;
        this.numTlf = numTlf;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public void setCorreo(String email) {
        this.email = email;
    }

}
