package com.crud.back.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.crud.back.entity.SchoolUserEntity;
import com.crud.back.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {
    
    private final ObjectMapper objectMapper;// iniciamos el ObjectMapper para trasnformarlo en json

    public String generateToken(SchoolUserEntity school) throws JsonProcessingException, IllegalArgumentException, JWTCreationException{
        return JWT.create().// creamos el jwt
        withClaim("school", objectMapper.writeValueAsString(school)) // añadimos la infromacion que queremos insertar en el token que es el objeto como json
        .withIssuer(Constants.AUTH0).// la firma de la clave privada
        withExpiresAt(Date.from(Instant.now().plusSeconds(86400))) // expiracion del token
        .sign(Constants.ALGORITH); // la firma del token para que solo los que tengamso esa forma pueda descodificarla
    }

    public SchoolUserEntity extractToken(String token) throws JsonProcessingException{
        JWTVerifier verifer = JWT.require(Constants.ALGORITH).withIssuer(Constants.AUTH0).build(); // generamos un verificador del token con nuestra clave provada y firma
        DecodedJWT decodedJWT = verifer.verify(token);// verificamos que ese token tiene nuesta firma y clave primaria y lo descodifica
        String tokenString = decodedJWT.getClaim("school").asString();
        
        return objectMapper.readValue(tokenString, SchoolUserEntity.class) ; //obtenemos el string que queremos y lo transformamos en objeto
    }
}
