package com.crud.back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.back.entity.SchoolUserEntity;
import com.crud.back.service.CrudSchoolServices;
import com.crud.back.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;



@Slf4j //para poner logs
@CrossOrigin("*") //para que se pueda llamar desde cualquier sitio
@RestController //para hacer llamadas a la api y hacer una respuesta por el body
@RequestMapping("/crud") //mapeo de la url que aparece al hacer la llamada
@RequiredArgsConstructor //para que se inyecten los beans
public class CrudSchoolController {

    private final CrudSchoolServices crudSchoolServices;
    private final TokenService tokenService;
    
    /**
     * obetenemos un json con los datos del colegio para poder insertarlo en la base de datos
     * 
     * url: http://localhost:8080/crud/insertStudents
     * 
     * @param schoolUserEntity json del colegio
     * @return devuelve si ha ha ido bien un JWT con datos de la escuela y si ha ido mal un mensaje de error
     */
    @PostMapping("/insertSchool")
    public ResponseEntity<String> insertSchool(@RequestBody SchoolUserEntity schoolUserEntity) {
        return crudSchoolServices.insertSchool(schoolUserEntity);
    }

    /**
     * miramos si existe el colegio en la base de datos para que luego pueda cambiar la contraseña
     * 
     * url: http://localhost:8080/crud/findSchool?name={name}
     * 
     * @param name nombre del colegio
     * @return devuelve si ha ha ido bien un mensaje de que ha ido bien y si ha ido mal un mensaje de error
     */
    @GetMapping("/findSchool")
    public ResponseEntity<String> findSchool(@RequestParam String name) {
        return crudSchoolServices.getSchool(name);
    }

    /**
     * miramos si existe el colegio en la base de datos para que pueda entrar a la web
     * 
     * http://localhost:8080/crud/loginSchool?name={name}&password={password}
     * 
     * @param name //nombre del colegio
     * @param password //contraseña del colegio
     * @return devuelve si ha ha ido bien un JWT con datos de la escuela y si ha ido mal un mensaje de error
     */
    @PostMapping("/loginSchool")
    public ResponseEntity<String> getSchool(@RequestParam String name,@RequestParam String password) {
        return crudSchoolServices.loginSchool(name,password);
    }

    /**
     * borramos el colegio de la base de datos
     * 
     * url: http://localhost:8080/crud/deleteSchool
     * 
     * @param token jwt el cual esta la entidad School
     * @return devuelve si ha ha ido bien un mensaje de que ha ido bien y si ha ido mal un mensaje de error
     */
    @DeleteMapping("/deleteSchool")
    public ResponseEntity<String> deleteSchool(@RequestHeader String token) {
        try {
            SchoolUserEntity schoolUserEntity = tokenService.extractToken(token);
            return crudSchoolServices.deleteSchool(schoolUserEntity.getId());
        } catch (Exception e) {
            log.error("error in decode token. Error; {}", e.getMessage(),e);
            return ResponseEntity.status(401).body("no esta autorizado");
        }
        
    }

    /**
     * actualizamos los datos del colegio sobretodo lo principal es actualizar la contraseña
     * 
     * url: http://localhost:8080/crud/updateSchool/{id}
     * 
     * @param id id del colegio
     * @param entity json de los datos del colegio
     * @return
     */
    @PutMapping("updateSchool/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable Long id, @RequestBody SchoolUserEntity entity) {
        return crudSchoolServices.updateSchool(id, entity);
    }
    
}
