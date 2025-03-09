package com.web.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.web.api.entity.UserEntity;
import java.util.Optional;


// es la conexion con la base de datos y aqui tiene las funcion de base de datos (select, insert, update, delete)
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    // es lo mismo que en base de datos: SELECT * FROM users WHERE msisdn = %msisdn
    Optional<UserEntity> findByMsisdn(String msisdn);
}
