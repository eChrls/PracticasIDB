package com.videoclub.videoclub.dto;

import java.sql.Time;

import com.videoclub.videoclub.entity.UsuarioEntity;
import com.videoclub.videoclub.enums.Categoria;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
public class PeliculaDto {
  
    
    private String nombre;
    private Categoria categoria;
    private Time duracion;
    private boolean todosPublicos;
    private Long idUsuario;

}
