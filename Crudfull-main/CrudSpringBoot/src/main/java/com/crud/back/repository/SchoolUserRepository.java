package com.crud.back.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.crud.back.entity.SchoolUserEntity;

// es la conexion con la base de datos y aqui tiene las funcion de base de datos (select, insert, update, delete)
public interface SchoolUserRepository extends CrudRepository<SchoolUserEntity, Long> {

    // es lo mismo que en base de datos: SELECT * FROM school_user WHERE name = %name
    Optional<SchoolUserEntity> findByName(String name);

    // es lo mismo que en base de datos: SELECT * FROM school_user WHERE name = %name AND password = %password
    Optional<SchoolUserEntity> findByNameAndPassword(String name, String password);
    
} 