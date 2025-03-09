package com.primerapractica.crud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


import jakarta.persistence.Entity;


import lombok.Data;

@Data 
@Entity 
@Table(name = "composiciones")
public class ComposicionesEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComposicion;
    private String nombreComposicion;
    private String tipoComposicion;
    private String instrumental; 

}
