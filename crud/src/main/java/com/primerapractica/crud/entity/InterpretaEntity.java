package com.primerapractica.crud.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "interpreta")
public class InterpretaEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ESTO HACE EL AUTOINCREMENT DE ID

    // INTERPRETA(idMusico [fk: MUSICOS], idComposicion [fk: MOVIMIENTOS],
    // idMovimiento [fk: MOVIMIENTOS], fecha, cache);
    private Long idInterpreta;
    private LocalDate fecha;
    private String cache;
    
    



}
