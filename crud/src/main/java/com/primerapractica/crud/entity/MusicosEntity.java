package com.primerapractica.crud.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "musicos")    
public class MusicosEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idMusico;
    @Column(name = "DNI", unique = true)
    private String dniMusico;
    private String nombreMusico;
    private String apellidosMusico;
    private String direccionMusico; 
    private String telefonoMusico;
    private LocalDate fechaAltaMusico;



}
