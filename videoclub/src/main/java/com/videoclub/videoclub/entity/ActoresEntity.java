package com.videoclub.videoclub.entity;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actores")
public class ActoresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_actor")
    private Long idActor;
    private String nombre;
    private String apellido;
    private int edad;
    private String origen; 
    private LocalDate fechaFallecimiento;

    @ManyToMany
    @JoinTable(name = "pelicula", joinColumns = @JoinColumn(name = "id_pelicula"), inverseJoinColumns = @JoinColumn(name = "id_actor"))

    private Set<PeliculaEntity> peliculas = new LinkedHashSet<>();
    




}
