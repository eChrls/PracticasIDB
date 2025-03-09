package com.sample.zelda.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.zelda.dto.JsonZelda;
import com.sample.zelda.utils.Constants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZeldaService {
    
    @Value("${url.api.zelda}")
    private String url; 

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ResponseEntity<String> getVideoGame() throws JsonProcessingException{
        
    // String url = Constants.URL; una manera de hacerlo llamando a la carpeta utils y a la clase Constants
    restTemplate.getForObject(url, JsonZelda.class);

    ResponseEntity<JsonZelda> responseJsonZelda = restTemplate.getForEntity(url, JsonZelda.class);

    if(responseJsonZelda.getStatusCode().is2xxSuccessful() && responseJsonZelda.getBody() != null){
       
        return ResponseEntity.ok().body(objectMapper.writeValueAsString(responseJsonZelda.getBody()));
    
        }
        return ResponseEntity.internalServerError().body("Error al obtener los datos");

    }






}