package com.videoclub.videoclub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videoclub.videoclub.entity.PeliculaEntity;


public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {

    List<PeliculaEntity> findAll(); 
}
