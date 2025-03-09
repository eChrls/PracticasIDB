package com.primerapractica.crud.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primerapractica.crud.dto.movimientoDto;
import com.primerapractica.crud.entity.ComposicionesEntity;
import com.primerapractica.crud.entity.MovimientosEntity;
import com.primerapractica.crud.entity.MusicosEntity;
import com.primerapractica.crud.repository.ComposicionesRepository;
import com.primerapractica.crud.repository.MovimientosRepository;
import com.primerapractica.crud.repository.MusicosRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j // sirve para mostrar toda la linea del log y sirve con info, error y debug. BBPP.
@RequiredArgsConstructor // requiere que los campos finales sean inicializados a través de un constructor
@Service // indica que la clase es un servicio
public class CrudService {
    
    private final MusicosRepository musicosRepository;
    private final ComposicionesRepository composicionesRepository;
    private final MovimientosRepository movimientosRepository;
    private final ObjectMapper objectMapper; // para convertir de json a objeto y viceversa


    public ResponseEntity<String> getMusicos(){
    //   return  musicosRepository.findAll(); // llamamos a repository que es como sql, y le damos a findAll
    try{
        // Iterable<ComposicionesEntity> composiciones = composicionesRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(musicosRepository.findAll())); 
        //ResponseEntity para dar la respuesta que quieras .ok (diferentes mensajes)
        //objectMapper para convertir de json a objeto y viceversa y writeValueAsString para convertir a string
        //e importamos la funcion composicionesRepository.findAll para dar respuesta a get Musicos
    } catch (JsonProcessingException e){
        log.error("Error musico, obtener datos : {}", e.getMessage(), e);
        return ResponseEntity.internalServerError().build(); // si hay error, se muestra mensaje de error   
    }

}


public ResponseEntity<String> getComposiciones(){
    //   return  musicosRepository.findAll(); // llamamos a repository que es como sql, y le damos a findAll
    try{
        // Iterable<ComposicionesEntity> composiciones = composicionesRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(composicionesRepository.findAll())); //ResponseEntity para dar la respuesta que quieras .ok (diferentes mensajes)
        //objectMapper para convertir de json a objeto y viceversa y writeValueAsString para convertir a string
        //e importamos la funcion composicionesRepository.findAll para dar respuesta a get Musicos
    } catch (JsonProcessingException e){
        log.error("Error composicion, obtener datos : {}", e.getMessage(), e);
        return ResponseEntity.internalServerError().build(); // si hay error, se muestra mensaje de error   
    }

}
    
public ResponseEntity<String> getMovimientos(){
    //   return  musicosRepository.findAll(); // llamamos a repository que es como sql, y le damos a findAll
    try{
        // Iterable<ComposicionesEntity> composiciones = composicionesRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(movimientosRepository.findAll())); //ResponseEntity para dar la respuesta que quieras .ok (diferentes mensajes)
        //objectMapper para convertir de json a objeto y viceversa y writeValueAsString para convertir a string
        //e importamos la funcion composicionesRepository.findAll para dar respuesta a get Musicos
    } catch (JsonProcessingException e){
        log.error("Error composicion, obtener datos : {}", e.getMessage(), e);
        return ResponseEntity.internalServerError().build(); // si hay error, se muestra mensaje de error   
    }

}
    public ResponseEntity<String>addMusico(MusicosEntity musico){
        try{
        return ResponseEntity.ok("Musico añadido correctamente" + musicosRepository.save(musico));
        // el ok se cierra al final y sirve para añadir mensaje de que se ha añadido correctamente
        }catch(Exception e){
        log.error("Error al insertar musico: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError().build();    
        }

    }    

    public ResponseEntity<String>addMovimiento(MovimientosEntity movimiento){
        try{
            return ResponseEntity.ok("Movimiento añadido correctamente" + movimientosRepository.save(movimiento));
            // el ok se cierra al final y sirve para añadir mensaje de que se ha añadido correctamente
            }catch(Exception e){
            log.error("Error al insertar movimiento: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();    
            }

    }

    public ResponseEntity<String>addComposicion(ComposicionesEntity composicion){
        try{
            return ResponseEntity.ok("Composición añadida correctamente" + composicionesRepository.save(composicion));
            // el ok se cierra al final y sirve para añadir mensaje de que se ha añadido correctamente
            }catch(Exception e){
            log.error("Error al insertar composición: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();    
            }

    }

    public ResponseEntity <String> deleteMusico(Long id){
        try{
            musicosRepository.deleteById(id);
            return ResponseEntity.ok("Musico eliminado correctamente");
        }catch(Exception e){
            log.error("Error al eliminar musico: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity <String> deleteComposicion(Long id){
        try{
            composicionesRepository.deleteById(id);
            return ResponseEntity.ok("Composición eliminada correctamente");
        }catch(Exception e){
            log.error("Error al eliminar composición: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity <String> deleteMovimiento(Long id){
        try{
            movimientosRepository.deleteById(id);
            return ResponseEntity.ok("Movimiento eliminado correctamente");
        }catch(Exception e){
            log.error("Error al eliminar movimiento: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }   

    public ResponseEntity <String> updateMovimiento(Long id, movimientoDto movimiento){

        try{
           if( movimientosRepository.findById(id).isPresent()){
            Set<ComposicionesEntity> movimientoSet = new HashSet<>();
            movimientoSet.add(composicionesRepository.findById(id).get());
            MovimientosEntity movimientoEntity = movimientosRepository.findById(id).get();
            //coge el objeto movimiento como un SELECT * FROM movimiento m WHERE m.id = 3
            
            // arriba lo coges, abajo lo seteas
            movimientoEntity.setDuracion(Double.parseDouble(movimiento.getDuracion().toString()));
            movimientoEntity.setIdComposicion(movimientoSet);
            movimientosRepository.save(movimientoEntity);
           }
           return ResponseEntity.ok("Movimiento actualizado correctamente");
        }catch(Exception e){
            log.error("Error al actualizar movimiento: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }


    public ResponseEntity<String> updateComposicion (Long id, ComposicionesEntity composicion){

        try{
            if(composicionesRepository.findById(id).isPresent()){
                
                ComposicionesEntity composicionesEntity = composicionesRepository.findById(id).get();

                composicionesEntity.setNombreComposicion(composicion.getNombreComposicion());
                composicionesEntity.setInstrumental(composicion.getInstrumental());
                composicionesEntity.setTipoComposicion(composicion.getTipoComposicion());
                
                composicionesRepository.save(composicionesEntity);
                
            }
            return ResponseEntity.ok("Composicion actualizada correctamente");

        }catch(Exception e){
            log.error("Error al actualizar composicion: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

    }

    public ResponseEntity<String> updateMusicos (Long id, MusicosEntity musico){

        try{
            if(musicosRepository.findById(id).isPresent()){
                
                MusicosEntity musicosEntity = musicosRepository.findById(id).get();

                musicosEntity.setDniMusico(musico.getDniMusico());
                musicosEntity.setNombreMusico(musico.getNombreMusico());
                musicosEntity.setApellidosMusico(musico.getApellidosMusico());
                musicosEntity.setDireccionMusico(musico.getDireccionMusico());
                musicosEntity.setTelefonoMusico(musico.getTelefonoMusico());
                musicosEntity.setFechaAltaMusico(musico.getFechaAltaMusico());

                musicosRepository.save(musicosEntity);
                
            }
            return ResponseEntity.ok("Músico actualizado correctamente");

        }catch(Exception e){
            log.error("Error al actualizar composicion: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

    }

    

}
