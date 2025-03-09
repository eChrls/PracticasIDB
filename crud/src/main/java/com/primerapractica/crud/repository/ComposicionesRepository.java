package com.primerapractica.crud.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.primerapractica.crud.entity.ComposicionesEntity;
import com.primerapractica.crud.entity.MovimientosEntity;

public interface ComposicionesRepository extends CrudRepository<ComposicionesEntity, Long>{ 

    List<ComposicionesEntity> findAll();
} 
