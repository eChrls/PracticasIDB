package com.videoclub.videoclub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.videoclub.videoclub.entity.UsuarioEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    List<UsuarioEntity>findAll();

@Query(value = "SELECT * FROM usuario WHERE nombre = ?1 ", nativeQuery = true)    
List<UsuarioEntity> findByNombre(String nombre);

    
} 