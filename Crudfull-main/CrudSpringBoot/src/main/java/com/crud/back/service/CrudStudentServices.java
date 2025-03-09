package com.crud.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crud.back.entity.SchoolUserEntity;
import com.crud.back.entity.StudentEntity;
import com.crud.back.model.StudentDto;
import com.crud.back.repository.SchoolUserRepository;
import com.crud.back.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //para poner logs
@Service // para saber que esta clase es un servicio de spring
@RequiredArgsConstructor //para que se inyecten los beans
public class CrudStudentServices {
    
    private final StudentRepository studentRepository;
    private final SchoolUserRepository schoolUserRepository;

    private final ObjectMapper objectMapper;

    public ResponseEntity<String> insertStudent(StudentDto studentDto,SchoolUserEntity schoolUserEntity){
        try {
            Optional<SchoolUserEntity> schoolOpt = schoolUserRepository.findById(schoolUserEntity.getId());// buscamos la escuela donde se metera el alumno
            if(schoolOpt.isPresent()){
                studentRepository.save(new StudentEntity(studentDto, schoolOpt.get())); // insertamos el alumno y lo devolvemos en la respuesta
                return ResponseEntity.ok().body("Success insert student");
            }
            return ResponseEntity.internalServerError().body("Not find in the database");
        } catch (Exception e) {
            log.error("Error insert school. Error: {}", e.getMessage(),e); // controlamos que error ha salido
            return ResponseEntity.internalServerError().body("Error insert student"); //si hay cualquier tipo de excepcion devolvemos 500
        }
    }

    public ResponseEntity<String> getAllStudent(SchoolUserEntity schoolUserEntity){
        try {
            List<StudentEntity> listStudent = studentRepository.findBySchoolUser(schoolUserEntity);// sacamos todos los usuarios de esa escuela
            String listStudentString = objectMapper.writeValueAsString(listStudent); // lo convertimos en json
            return  ResponseEntity.ok().body(listStudentString); // enviamos al cuerpo el json anterior
        } catch (Exception e) {
            log.error("Error get all student. Error: {}", e.getMessage(),e); // controlamos que error ha salido
            return ResponseEntity.internalServerError().body("Error get all student"); //si hay cualquier tipo de excepcion devolvemos 500
        }
    }

    public ResponseEntity<String> getOneStudent(Long id){
        try {
            Optional<StudentEntity> optStudent = studentRepository.findById(id); // buscamos el alumno por id
            String optStudentString = objectMapper.writeValueAsString(optStudent.get()); // tras optenerlo lo convertimso en json
            return  ResponseEntity.ok().body(optStudentString); //pasamos ese json por el body
        } catch (Exception e) {
            log.error("Error get one student. Error: {}", e.getMessage(),e); // controlamos que error ha salido
            return ResponseEntity.internalServerError().body("Error get one student"); //si hay cualquier tipo de excepcion devolvemos 500
        }
    }

    public ResponseEntity<String> deleteStudent(Long id){
        try {
            studentRepository.deleteById(id);//buscamos el ususario y lo elimina
            return ResponseEntity.ok().body("detele fine");//devolvemos un mensaje de que ha ido bien
        } catch (Exception e) {
            log.error("Error delete student. Error: {}", e.getMessage(),e); // controlamos que error ha salido
            return ResponseEntity.internalServerError().body("Error delete student"); //si hay cualquier tipo de excepcion devolvemos 500
        }
    }

    public ResponseEntity<String> updateStudent(Long id, StudentDto studentDto,SchoolUserEntity schoolUserEntity ){
        try {
            Optional<StudentEntity> studentOpt = studentRepository.findById(id);//buscamos el usuario que queremos actualizar
            if(studentOpt.isPresent()){
                Optional<SchoolUserEntity> schoolOpt = schoolUserRepository.findById(schoolUserEntity.getId());// miramos si ese alumno esta en la escuela que queremos actualizar
                if(schoolOpt.isPresent()){
                    // obtenemos el usuario y actualizamos todos los valores
                    StudentEntity studen = studentOpt.get();
                    studen.setAddress(studentDto.getAddress());
                    studen.setAge(studentDto.getAge());
                    studen.setCity(studen.getCity());
                    studen.setCourse(studen.getCourse());
                    studen.setDateInit(studentDto.getDateInit());
                    studen.setEmail(studentDto.getEmail());
                    studen.setName(studentDto.getName());
                    studen.setPhone(studen.getPhone());
                    studentRepository.save(studen);
                    return ResponseEntity.ok().body("update susccessfull");// devolvemos un mensaje de que ha ido bien
                }
                return ResponseEntity.internalServerError().body("Not find school in the database");//si no encontramos en la bbdd devolvemos 500
            }
            return ResponseEntity.internalServerError().body("Not find student in the database");//si no encontramos en la bbdd devolvemos 500
        } catch (Exception e) {
            log.error("Error delete student. Error: {}", e.getMessage(),e); // controlamos que error ha salido
            return ResponseEntity.internalServerError().body("Error delete student"); //si hay cualquier tipo de excepcion devolvemos 500
        }
    }
}
