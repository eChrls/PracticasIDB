package com.sample.zelda.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.zelda.service.ZeldaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class ZeldaController {
    private final ZeldaService zeldaService;



    @GetMapping("/videogame")
    public ResponseEntity<String> getMethodName() {
        try {
            return zeldaService.getVideoGame();
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("Error al obtener los datos");
        }
      
    }
    

    


}
