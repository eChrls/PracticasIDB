package com.videoclub.videoclub.entity;

import java.sql.Time;
import java.util.Set;

import com.videoclub.videoclub.enums.Categoria;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pelicula")
public class PeliculaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private Long idPelicula;
    private String nombre;
    private Categoria categoria;
    private Time duracion;
    private boolean todosPublicos;
    //FK
    @JoinColumn(name = "id_usuario")
    @ManyToOne
    private UsuarioEntity usuario;

    public PeliculaEntity(String nombre, Categoria categoria, Time duracion, boolean todosPublicos, UsuarioEntity usuario) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.duracion = duracion;
        this.todosPublicos = todosPublicos;
        this.usuario = usuario;
    }
    
      
}
