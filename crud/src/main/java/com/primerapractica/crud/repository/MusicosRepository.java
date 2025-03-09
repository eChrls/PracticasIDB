package com.primerapractica.crud.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.primerapractica.crud.entity.MusicosEntity;

public interface MusicosRepository extends CrudRepository<MusicosEntity, Long>{ 
    List<MusicosEntity> findAll();  //Si quieres verlo como lista se hace en repository así  
    
    List<MusicosEntity> findByDniMusico(String dniMusico);
    
    @Query(value = "SELECT * FROM musicos WHERE nombre_musico = ?1", nativeQuery = true)  
    //Query que busca por nombre de músico. Inserta funcion de SQL. ?1 es el primer parámetro que le pasamos. 
    //Puede ser tambien nombre_musico = :nombre  
    List<MusicosEntity> findByNombre(String nombre); //función que busca por nombre
}