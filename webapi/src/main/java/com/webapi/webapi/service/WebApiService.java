package com.webapi.webapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapi.webapi.entity.ComentarioEntity;
import com.webapi.webapi.entity.UsuarioEntity;
import com.webapi.webapi.model.ComentarioDto;
import com.webapi.webapi.repositories.ComentarioRepository;
import com.webapi.webapi.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class WebApiService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private final ComentarioRepository comentarioRepository;

    // ==========================================================================
    // USUARIO
    // ================================================================================
    // GET
    public ResponseEntity addUser(String msisdn) {
        try {
            UsuarioEntity usuario = new UsuarioEntity(msisdn);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().body("OK");
        } catch (Exception e) {
            log.error("Error al guardar usuario", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST
    public ResponseEntity<String> getUser(String msisdn) {
        try {
            if(usuarioRepository.findByMsisdn(msisdn).isPresent()){
                return ResponseEntity.ok(objectMapper.writeValueAsString(usuarioRepository.findByMsisdn(msisdn).get()));
            }
            return ResponseEntity.internalServerError().body("Error");
        } catch (Exception e) {
            log.error("Error al obtener usuarios", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    // ===========================================================================
    // COMENTARIOS
    // ================================================================================
    //POST
    public ResponseEntity addComment(ComentarioDto comentario) {
        try {

            ComentarioEntity comentarioDto = new ComentarioEntity();
            comentarioDto.setName(comentario.getName());
            comentarioDto.setCorreo(comentario.getEmail());
            comentarioDto.setNumTlf(comentario.getNumTlf());
            comentarioDto.setMensaje(comentario.getMensaje());
            comentarioDto.setUsuario(usuarioRepository.findById(comentario.getIdUsuario()).get());

            comentarioRepository.save(comentarioDto);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error("Error al actualizar la pelicula", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la pelicula");
        }
    }

}
