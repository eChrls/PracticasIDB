package com.primerapractica.crud.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primerapractica.crud.dto.movimientoDto;
import com.primerapractica.crud.entity.ComposicionesEntity;
import com.primerapractica.crud.entity.MovimientosEntity;
import com.primerapractica.crud.entity.MusicosEntity;
import com.primerapractica.crud.repository.ComposicionesRepository;
import com.primerapractica.crud.repository.MovimientosRepository;
import com.primerapractica.crud.service.CrudService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController // importacion de la libreria para el controlador
@RequestMapping("/api") // etiquetado para hacer la llamada
@RequiredArgsConstructor // importacion de la libreria para el controlador

public class CrudController {
    private final MovimientosRepository movimientosRepository;
    private final CrudService crudService;
    private final ComposicionesRepository composicionesRepository;
    @GetMapping("/api")	// etiquetado para hacer la llamada

    public String getApi() {
        return "Hola mundo";
    }  
    
    @GetMapping("/mus")	// etiquetado para hacer la llamada

    public ResponseEntity getMusicos() {
        
        return crudService.getMusicos();
        
    }
    @GetMapping("/comp")	// etiquetado para hacer la llamada
    public ResponseEntity getComposiciones() {
        
        return crudService.getComposiciones();
        
    }
    @GetMapping("/mov")	// etiquetado para hacer la llamada
    public ResponseEntity getMovimientos() {
        
        return crudService.getMovimientos();
        
    }
    
    @PostMapping("/addMusicos") //lo usamos para INSERTAR
    public ResponseEntity postMethodName(@RequestBody MusicosEntity musico) {
        //TODO: process POST request
        //OJO, crea un public String por defecto, y debe ser public ResponseEntity
        return crudService.addMusico(musico);

    }

    @PostMapping("/addMovimiento")
    public ResponseEntity postMethodName(@RequestBody movimientoDto movimiento) {
        //TODO: process POST request
        try{
            Set<ComposicionesEntity> composiciones = new HashSet<>();
            Optional<ComposicionesEntity> composicionOpt = composicionesRepository.findById(movimiento.getIdComposicion());
            if(composicionOpt.isPresent()){
                composiciones.add(composicionOpt.get());
                movimientosRepository.save(new MovimientosEntity(movimiento.getDuracion(), composiciones));
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }       
        return ResponseEntity.ok().build();
    }
    
    
    @PostMapping("/addComposicion") 
    public ResponseEntity postMethodN(@RequestBody movimientoDto composicionDto){
     
        Set<ComposicionesEntity> composiciones = new HashSet<>();
        Optional<ComposicionesEntity> composicionOpt = composicionesRepository.findById(composicionDto.getIdComposicion()); 
        if(composicionOpt.isPresent()){
            composiciones.add(composicionOpt.get());
            movimientosRepository.save(new MovimientosEntity(composicionDto.getDuracion(), composiciones));
        }
        return ResponseEntity.ok().build();
    }//lo usamos para INSERTAR
 
    @DeleteMapping("/deleteMusicos/{id}") //lo usamos para ELIMINAR
    public ResponseEntity <String> deleteMusico (@PathVariable Long id) {   

        return crudService.deleteMusico(id);
    }

    @DeleteMapping("/deleteComposiciones/{id}") 
    public ResponseEntity <String> deleteComposicion (@PathVariable Long id) {   

        return crudService.deleteComposicion(id);
    }   

    @DeleteMapping("/deleteMovimientos/{id}")
    public ResponseEntity <String> deleteMovimiento (@PathVariable Long id) {   

        return crudService.deleteMovimiento(id);
    }       

    @PutMapping("/updateMovimientos/{id}") //lo usamos para ACTUALIZAR
    public ResponseEntity updateMovimiento (@PathVariable Long Id, @RequestBody movimientoDto movimiento) {
        
        return crudService.updateMovimiento(Id, movimiento);

    }

    @PutMapping("/updateComposiciones/{id}")
    public ResponseEntity updateComposicion (@PathVariable Long Id, @RequestBody movimientoDto composicion) {
        
        return crudService.updateComposicion(Id, composicion);

    }

    @PutMapping("/updateMusicos/{id}")
    public ResponseEntity updateMusico (@PathVariable Long Id, @RequestBody MusicosEntity musico) {
        
        return crudService.updateMusico(Id, musico);

    }
        

        
    
    
}
