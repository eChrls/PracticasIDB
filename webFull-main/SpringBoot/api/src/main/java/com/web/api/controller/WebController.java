package com.web.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.api.model.CommentCallDto;
import com.web.api.service.WebService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //para poner logs
@CrossOrigin("*") //para que se pueda llamar desde cualquier sitio
@RestController //para hacer llamadas a la api y hacer una respuesta por el body
@RequiredArgsConstructor //para que se inyecten los beans
@RequestMapping("/apiWeb") //mapeo de la url que aparece al hacer la llamada
public class WebController {
    
    private final WebService webService;

    /**
     *  Este controlador lo que hace es insertar un usuario en la base de datos. 
     *  Es una llamada Post con este formato de url:
     *  /apiWeb/addUser?msisdn={{msisdn}}
     * 
     * @param msisdn numero de telefono
     */
    @PostMapping("/addUser") 
    public ResponseEntity<String> addUser(@RequestParam String msisdn) {
        return webService.addUser(msisdn);
    }

    /**
     *  Comprobamos que el usuario este en la base de datos 
     *  Es una llamada Post con este formato de url:
     *  /apiWeb/getUser?msisdn={{msisdn}}
     * @param msisdn numero de telefono
     */
    @GetMapping("/getUser") 
    public ResponseEntity<String> getUser(@RequestParam String msisdn) {
        return webService.getUser(msisdn);
    }
    
    /**
     *  Comprobamos que el usuario este en la base de datos 
     *  Es una llamada Post con este formato de url:
     *  /apiWeb/addComment
     *  {
     *      "idUser": "%id",
     *      "email": "%email",
     *      "phone": "%phone",
     *      "subject": "%subject",
     *      "comment": "%comment"
     *   }
     * 
     * @param msisdn numero de telefono
     */
    @PostMapping("/addComment") 
    public ResponseEntity<String> addComment(@RequestBody CommentCallDto commentCallDto) {
        return webService.addComment(commentCallDto);
    }


}
