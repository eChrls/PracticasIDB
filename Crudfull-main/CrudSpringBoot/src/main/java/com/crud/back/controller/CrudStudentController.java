package com.crud.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.back.entity.SchoolUserEntity;
import com.crud.back.model.StudentDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.crud.back.service.CrudStudentServices;
import com.crud.back.service.TokenService;
import com.crud.back.utils.Constants;


@Slf4j //para poner logs
@CrossOrigin("*") //para que se pueda llamar desde cualquier sitio
@RestController //para hacer llamadas a la api y hacer una respuesta por el body
@RequestMapping("/crud") //mapeo de la url que aparece al hacer la llamada
@RequiredArgsConstructor //para que se inyecten los beans
public class CrudStudentController {
    
    private final CrudStudentServices crudStudentServices;
    private final TokenService tokenService;
    
    /**
     * insertar estudiantes en la base de datos
     * 
     * url: http://localhost:8080/crud/insertStudents
     * 
     * @param student entidad del estudante para meterlo
     * @param token jwt con la entidad School para enlazarlo
     * @return
     */
    @PostMapping("/insertStudents")
    public ResponseEntity<String> insertStudents(@RequestBody StudentDto student,@RequestHeader String token) {
        try {
            SchoolUserEntity schoolUserEntity = tokenService.extractToken(token);
            return crudStudentServices.insertStudent(student,schoolUserEntity);
        } catch (Exception e) {
            log.error(Constants.LOG_ERROR_TOKEN, e.getMessage(),e);
            return ResponseEntity.status(401).body(Constants.MESSAGE_401);
        }
        
    }

    /**
     * obtener todos los estudiantes de una escuela, por el token
     * 
     * @param token jwt con la entidad School para enlazarlo
     * @return
     */
    @GetMapping("/getStudent")
    public ResponseEntity<String> getAllStudents(@RequestHeader String token) {
        try {
            SchoolUserEntity schoolUserEntity = tokenService.extractToken(token);
            return crudStudentServices.getAllStudent(schoolUserEntity);
        } catch (Exception e) {
            log.error(Constants.LOG_ERROR_TOKEN, e.getMessage(),e);
            return ResponseEntity.status(401).body(Constants.MESSAGE_401);
        }
        
    }

    /**
     * buscamos un estudiante en concreto
     * 
     * localhost:8080/crud/getStudent/{id}
     * 
     * @param id del estudante que buscamos
     * @param token jwt con la entidad School para enlazarlo
     * @return
     */
    @GetMapping("/getStudent/{id}")
    public ResponseEntity<String> getStudent(@PathVariable Long id,@RequestHeader String token) {
        try {
            tokenService.extractToken(token);
            return crudStudentServices.getOneStudent(id);
        } catch (Exception e) {
            log.error(Constants.LOG_ERROR_TOKEN, e.getMessage(),e);
            return ResponseEntity.status(401).body(Constants.MESSAGE_401);
        }
        
    }

    /**
     * elimianar la estudante
     * 
     * url: localhost:8080/crud/deleteStudent/{id}
     * 
     * @param id estudiante que vamos a borrar
     * @param token jwt con la entidad School para enlazarlo y saber que se ha logeado
     * @return
     */
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id,@RequestHeader String token){
        try {
            tokenService.extractToken(token);
            return crudStudentServices.deleteStudent(id);
        } catch (Exception e) {
            log.error(Constants.LOG_ERROR_TOKEN, e.getMessage(),e);
            return ResponseEntity.status(401).body(Constants.MESSAGE_401);
        }
        
    }

    /**
     * actualizar los datos del estudante
     * 
     * @param id estudiante que vamos a borrar
     * @param student datos que obtenemos para actualizar
     * @param token jwt con la entidad School para enlazarlo
     * @return
     */
    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentDto student,@RequestHeader String token){
        log.info(student.toString());
        try {
            SchoolUserEntity schoolUserEntity = tokenService.extractToken(token);
            return crudStudentServices.updateStudent(id, student,schoolUserEntity);
        } catch (Exception e) {
            log.error(Constants.LOG_ERROR_TOKEN, e.getMessage(),e);
            return ResponseEntity.status(401).body(Constants.MESSAGE_401);
        }
        
    }
    
}
