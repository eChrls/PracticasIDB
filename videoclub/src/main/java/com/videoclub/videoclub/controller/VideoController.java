package com.videoclub.videoclub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videoclub.videoclub.dto.PeliculaDto;
import com.videoclub.videoclub.entity.ActoresEntity;
import com.videoclub.videoclub.entity.UsuarioEntity;
import com.videoclub.videoclub.repository.ActoresRepository;
import com.videoclub.videoclub.repository.PeliculaRepository;
import com.videoclub.videoclub.repository.UsuarioRepository;
import com.videoclub.videoclub.service.VideoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/video")
@RequiredArgsConstructor

public class VideoController {
    private final VideoService videoService;
    private final UsuarioRepository usuarioRepository;
    private final PeliculaRepository peliculaRepository;
    private final ActoresRepository actoresRepository;



//   ===========================================================================  USUARIO ================================================================================

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser (@RequestBody UsuarioEntity usuario) {
        //TODO: process POST request
        
        return videoService.addUser(usuario);
    }
    

    
    @GetMapping("/getUsers")
    public ResponseEntity getUsers(){

        return videoService.getUsers();
    }


    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        return videoService.deleteUser(id);
    }

    @PutMapping("update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UsuarioEntity usuario) {
        //TODO: process PUT request
        
        return videoService.updateUser(id, usuario);
    }

    @GetMapping("/getByName")
    public ResponseEntity getByName(@RequestParam String nombre){
        return videoService.getByName(nombre);
    }
    


//===========================================================================  PELICULAS ================================================================================

    @GetMapping("/getPelis")
    public ResponseEntity getPeliculas() {

        return videoService.getPeliculas();

    }

    @PostMapping("/addPeli")
    public ResponseEntity addPelicula(@RequestBody PeliculaDto peliculaDto) {
        return videoService.addPelicula(peliculaDto);
    }   
    
    @DeleteMapping("/deletePeli/{id}")  
    public ResponseEntity deletePelicula(@PathVariable Long id) {
        return videoService.deletePelicula(id);
    }

    @PutMapping("/updatePeli/{id}")
    public ResponseEntity updatePelicula(@PathVariable Long id, @RequestBody PeliculaDto peliculaDto) {
        return videoService.updatePelicula(id, peliculaDto);
    }

//================================================================ACTORES ================================================================================

@GetMapping("/getActores")
    public ResponseEntity getActores() {
        return videoService.getActores();
}



@PostMapping("/addActor")
    public ResponseEntity addActor(@RequestBody String actor) {
        return videoService.addActor(actor);
    }

    @DeleteMapping("/deleteActor/{id}")
    public ResponseEntity deleteActor(@PathVariable Long id) {
        return videoService.deleteActor(id);
    }

    @PutMapping("/updateActor/{id}")
    public ResponseEntity updateActor(@PathVariable Long id, @RequestBody ActoresEntity actor) {
        return videoService.updateActor(id, actor);
    }           

}







