package com.web.api.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.api.entity.CommentEntity;
import com.web.api.entity.UserEntity;
import com.web.api.model.CommentCallDto;
import com.web.api.repository.CommentRepository;
import com.web.api.repository.UserRepository;
import com.web.api.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //para poner logs
@Service // para saber que esta clase es un servicio de spring
@RequiredArgsConstructor //para que se inyecten los beans
public class WebService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ObjectMapper objectMapper; //para generar un json o pasar de json a objeto
    
    public ResponseEntity<String> addUser(String msisdn){
        try {
            UserEntity user = new UserEntity(msisdn);//creamos un usuario con el msisdn
            userRepository.save(user); // insertamos el ususario en la base de datos
            return ResponseEntity.ok().body("insertado correctamente"); // si se ha insertado correctamente devolvemos una respuesta 200
        } catch (Exception e) {
            log.error("error to add user. Error: {}", e.getMessage(),e);// controlamos que error ha salido
            return ResponseEntity.internalServerError().body(Constants.INTERNAL_ERROR);//si hay cualquier tipo de excepcion devolvemos 500
        }
    }

    public ResponseEntity<String> getUser(String msisdn) {
        try {
            Optional<UserEntity> userOpt = userRepository.findByMsisdn(msisdn);// buscamos si ese numero esta en la base de datos
            if(userOpt.isPresent()){ //miramos si esta en la base de datos
                return ResponseEntity.ok().body(objectMapper.writeValueAsString(userOpt.get()));//si esta devolvemos un ok con el usuario en formato json
            }
            return ResponseEntity.internalServerError().body("error database");//si no esta en la base de datos devolvemos un 500
        } catch (Exception e) {
            log.error("error to get user. Error: {}", e.getMessage(),e);// controlamos que error ha salido
            return ResponseEntity.internalServerError().body(Constants.INTERNAL_ERROR);//si hay cualquier tipo de excepcion devolvemos 500
        }
    }

    public ResponseEntity<String> addComment(CommentCallDto commentCallDto) {
        try {
            Optional<UserEntity> userOpt = userRepository.findById(commentCallDto.getIdUser());// buscamos si ese usuario esta en la base de datos por id
            if(userOpt.isPresent()){ //miramos si esta en la base de datos
                CommentEntity comment = new CommentEntity(commentCallDto.getEmail(), commentCallDto.getPhone(), commentCallDto.getSubject(), commentCallDto.getComment(), userOpt.get());//creamos un comentario con los datos que nos han pasado
                commentRepository.save(comment);//insertamos el comentario en la base de datos
                return ResponseEntity.ok().body("insertado correctamente");//si se ha insertado correctamente devolvemos una respuesta 200
            }
            return ResponseEntity.internalServerError().body("error database");//si no esta en la base de datos devolvemos un 500
        } catch (Exception e) {
            log.error("error to add Comment. Error: {}", e.getMessage(),e);// controlamos que error ha salido
            return ResponseEntity.internalServerError().body(Constants.INTERNAL_ERROR);//si hay cualquier tipo de excepcion devolvemos 500
        }
    }
}
