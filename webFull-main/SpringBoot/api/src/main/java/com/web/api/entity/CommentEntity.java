package com.web.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity // para que spring boot sepa que es una entidad de la base de datos
@Data // para que se generen los getters, setters, toString ...
@Table(name = "comments") // para que sepa que nombre es la tabla
public class CommentEntity {
    
    @Id //para saber que es la clave primeria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para que se genere automaticamente es decir de forma auto incrementable
    private Long id;

    private String email;
    private String phone;
    private String subject;
    private String comment;

    @ManyToOne // definimos el tipo de relacion que tiene con la tabla de usuarios
    @JoinColumn(name = "user_id") // definimos el nombre de la columna que se va a relacionar
    private UserEntity user;

    //generamos un constructor sin el id
    public CommentEntity(String email, String phone, String subject, String comment, UserEntity user){
        this.email = email;
        this.phone = phone;
        this.subject = subject;
        this.comment = comment;
        this.user = user;
    }
}
