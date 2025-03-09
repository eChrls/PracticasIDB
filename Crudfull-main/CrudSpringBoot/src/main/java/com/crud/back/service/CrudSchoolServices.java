package com.crud.back.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crud.back.entity.SchoolUserEntity;
import com.crud.back.repository.SchoolUserRepository;
import com.crud.back.utils.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //para poner logs
@Service // para saber que esta clase es un servicio de spring
@RequiredArgsConstructor //para que se inyecten los beans
public class CrudSchoolServices {

    private final SchoolUserRepository schoolUserRepository;
    private final TokenService tokenService;
    
    public ResponseEntity<String> insertSchool(SchoolUserEntity schoolUserEntity) {
        try {
            SchoolUserEntity school =  schoolUserRepository.save(schoolUserEntity); // guardamos en la base de datos la nueva escuela
            return ResponseEntity.ok(tokenService.generateToken(school)); // devolvemos una respuesta positiva y el token del usuario
        } catch (Exception e) {
            log.error("Error insert school. Error: {}", e.getMessage(),e); // controlamos que error ha salido
            return ResponseEntity.internalServerError().body("Error insert school"); //si hay cualquier tipo de excepcion devolvemos 500
        }
    }

    public ResponseEntity<String> getSchool(String name) {
        try {
            Optional<SchoolUserEntity> schoolUserEntityOpt = schoolUserRepository.findByName(name); // buscamos si ese nombre de colegio existe en la base de datos
            if(schoolUserEntityOpt.isPresent()) { // si el colegio existe deolvemos una respuesta positiva
                return ResponseEntity.ok(schoolUserEntityOpt.get().getId().toString());
            } else {// en caso contrario devolvemos un error (500)
                return ResponseEntity.internalServerError().body(Constants.NOT_FOUND_ERROR);
            }
        } catch (Exception e) {
            log.error("Error get school. Error: {}", e.getMessage(),e); // controlamos que error ha salido
            return ResponseEntity.internalServerError().body("Error get school"); //si hay cualquier tipo de excepcion devolvemos 500
        }
    }

    public ResponseEntity<String> loginSchool(String name, String password) {
        try {
            Optional<SchoolUserEntity> schoolUserEntityOpt = schoolUserRepository.findByNameAndPassword(name,password); // buscamos si ese usuario y contraseña esta en la base de datos
            if(schoolUserEntityOpt.isPresent()) { // si el colegio existe deolvemos una respuesta positiva y devolvemos el token que hemos generado 
                return ResponseEntity.ok(tokenService.generateToken(schoolUserEntityOpt.get()));
            } else { // en caso contrario devolvemos un error (500)
                return ResponseEntity.internalServerError().body(Constants.NOT_FOUND_ERROR);
            }
        } catch (Exception e) {
            log.error("Error login school. Error: {}", e.getMessage(),e); // controlamos que error ha salido
            return ResponseEntity.internalServerError().body("Error login school"); //si hay cualquier tipo de excepcion devolvemos 500
        }
    }

    public ResponseEntity<String> deleteSchool(Long id) {
        try {
            schoolUserRepository.deleteById(id);// borramos el usuario por el id 
            return ResponseEntity.ok("Success delete school"); // si se borro bien devolvemos un mensaje de que ha ido bien
        } catch (Exception e) {
            log.error("Error delete school. Error: {}", e.getMessage(),e); // controlamos que error ha salido
            return ResponseEntity.internalServerError().body("Error delete school"); //si hay cualquier tipo de excepcion devolvemos 500
        }
    }

    public ResponseEntity<String> updateSchool(Long id, SchoolUserEntity schoolUserEntity) {
        try {
            Optional<SchoolUserEntity> schoolUserEntityOpt = schoolUserRepository.findById(id); // buscamos el usuario por id
            if(schoolUserEntityOpt.isPresent()) { // si esta presente actualizamos el usuario
                SchoolUserEntity entitySchool =  schoolUserEntityOpt.get(); // obtenemos el usuario de la consulta
                entitySchool.setName(schoolUserEntity.getName());//actualizamos el nombre
                entitySchool.setPassword(schoolUserEntity.getPassword()); // actualizamos la contraseña
                schoolUserRepository.save(entitySchool); // guardamos el usuario que se ha actualizado para que haga el update
                return ResponseEntity.ok("Success update school"); // tras actualizar devolvemos se que ha actualizado correctamente
            } else { // si no esta en la base de datos devolvemos un mensaje de error (500)
                return ResponseEntity.internalServerError().body(Constants.NOT_FOUND_ERROR);
            }
        } catch (Exception e) {
            log.error("Error update school. Error: {}", e.getMessage(),e); // controlamos que error ha salido
            return ResponseEntity.internalServerError().body("Error update school"); //si hay cualquier tipo de excepcion devolvemos 500
        }

    }
}
