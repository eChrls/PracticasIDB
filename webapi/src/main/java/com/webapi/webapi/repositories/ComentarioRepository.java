package com.webapi.webapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapi.webapi.entity.ComentarioEntity;

public interface  ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {   
   
    List<ComentarioEntity> findAll();

    Optional<ComentarioEntity> findById(Long idComentario);

    
} 