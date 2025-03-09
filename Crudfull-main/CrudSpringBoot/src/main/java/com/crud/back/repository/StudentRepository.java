package com.crud.back.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crud.back.entity.SchoolUserEntity;
import com.crud.back.entity.StudentEntity;


// es la conexion con la base de datos y aqui tiene las funcion de base de datos (select, insert, update, delete)
public interface StudentRepository extends CrudRepository<StudentEntity, Long>{
    
     // es lo mismo que SELECT * FROM student WHERE school_id =:schoolUser
     List<StudentEntity> findBySchoolUser(SchoolUserEntity schoolUser);
}
