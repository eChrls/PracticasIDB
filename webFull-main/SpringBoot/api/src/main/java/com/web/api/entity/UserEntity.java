package com.web.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // para que spring boot sepa que es una entidad de la base de datos
@Data // para que se generen los getters, setters, toString ...
@AllArgsConstructor // genera un constructor con todos los parametros
@NoArgsConstructor // genera un constructor sin parametros
@Table(name = "users") // para que sepa que nombre es la tabla
public class UserEntity {

    @Id //para saber que es la clave primeria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para que se genere automaticamente es decir de forma auto incrementable
    private Long id;

    @Column(unique = true) // para que la columana sea unica
    private String msisdn;

    //generamos un constructor sin el id
    public UserEntity(String msisdn){
        this.msisdn = msisdn;
    }
}
