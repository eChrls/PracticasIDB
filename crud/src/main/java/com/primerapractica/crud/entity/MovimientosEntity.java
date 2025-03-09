package com.primerapractica.crud.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movimientos")
public class MovimientosEntity {
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY) 

private Long idMovimiento;
private double duracion;
@OneToMany(mappedBy = "idComposicion", cascade = CascadeType.ALL)
    private Set<ComposicionesEntity> idComposicion;
    public MovimientosEntity(Integer duracion, Set<ComposicionesEntity> idComposicion){
        this.duracion=duracion;
        this.idComposicion=idComposicion;
    }
}
    