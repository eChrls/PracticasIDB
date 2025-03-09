package com.web.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.api.entity.CommentEntity;

// es la conexion con la base de datos y aqui tiene las funcion de base de datos (select, insert, update, delete)
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    
}
