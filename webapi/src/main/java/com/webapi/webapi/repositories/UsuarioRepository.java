package com.webapi.webapi.repositories;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapi.webapi.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {   
    

    List<UsuarioEntity> findAll();
    
    Optional<UsuarioEntity> findByMsisdn(String msisdn);
    
}
