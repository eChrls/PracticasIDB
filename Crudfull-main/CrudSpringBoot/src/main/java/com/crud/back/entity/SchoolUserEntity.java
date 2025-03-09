package com.crud.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // para que se generen los getters, setters, toString ...
@Entity // para que spring boot sepa que es una entidad de la base de datos
@AllArgsConstructor // genera un constructor con todos los parametros
@NoArgsConstructor // genera un constructor sin parametros
@Table(name = "school_user") // para que sepa que nombre es la tabla
public class SchoolUserEntity {

    @Id //para saber que es la clave primeria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para que se genere automaticamente es decir de forma auto incrementable
    private Long id;

    @Column(nullable = false) //para que no pueda ser nulo
    private String name; 
    @Column(nullable = false) //para que no pueda ser nulo
    private String password;

   
}
