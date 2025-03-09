package com.videoclub.videoclub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videoclub.videoclub.entity.ActoresEntity;

public interface ActoresRepository extends JpaRepository<ActoresEntity, Long> {
   
     List<ActoresEntity> findAll();
}
