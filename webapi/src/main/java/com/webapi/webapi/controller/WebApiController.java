package com.webapi.webapi.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapi.webapi.entity.ComentarioEntity;
import com.webapi.webapi.entity.UsuarioEntity;
import com.webapi.webapi.model.ComentarioDto;
import com.webapi.webapi.repositories.ComentarioRepository;
import com.webapi.webapi.repositories.UsuarioRepository;
import com.webapi.webapi.service.WebApiService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/webapi")
@RequiredArgsConstructor
@CrossOrigin //("localhost:5500")
public class WebApiController {

    private final WebApiService webApiService;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;

    // ===========================================================================
    // USUARIO
    // ================================================================================

    @GetMapping("/addUser")
    public ResponseEntity addUser(@RequestParam String msisdn) {

        return webApiService.addUser(msisdn);
    }

    @PostMapping("/getUser")
    public ResponseEntity getUser(@RequestParam String msisdn) {

        return webApiService.getUser(msisdn);
    }

    // ===========================================================================
    // COMENTARIO
    // ================================================================================

    @PostMapping("/addComment")
    public ResponseEntity addComment(@RequestBody ComentarioDto comentarioDto) {
        // TODO: process POST request

        return webApiService.addComment(comentarioDto);
    }

}
