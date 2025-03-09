package com.primerapractica.crud.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.primerapractica.crud.entity.MovimientosEntity;
import com.primerapractica.crud.entity.MusicosEntity;

public interface MovimientosRepository extends CrudRepository<MovimientosEntity, Long>{ 
    List<MovimientosEntity> findAll();  //Si quieres verlo como lista se hace en repository así  

   
}

