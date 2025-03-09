package com.videoclub.videoclub.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")

public class UsuarioEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long idUsuario;
private String nombre;
private String apellido;
private int edad; 
private LocalDate fechaInicio;
private String direccion;
@Column(name = "Descripción", length = 1000)
private String descripcion; 
private double saldo;

//BIDIRECCIONAL
// @OneToMany(mappedBy = "id_usuario", cascade = CascadeType.ALL)
// List <PeliculaEntity> peliculas;
    
}
