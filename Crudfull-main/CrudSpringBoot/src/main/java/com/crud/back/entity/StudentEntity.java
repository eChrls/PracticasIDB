package com.crud.back.entity;

import java.sql.Date;

import com.crud.back.enums.Course;
import com.crud.back.model.StudentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // para que se generen los getters, setters, toString ...
@Entity // para que spring boot sepa que es una entidad de la base de datos
@AllArgsConstructor // genera un constructor con todos los parametros
@NoArgsConstructor // genera un constructor sin parametros
@Table(name = "student")// para que sepa que nombre es la tabla
public class StudentEntity {
    
    @Id//para saber que es la clave primeria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)//para que se genere automaticamente es decir de forma auto incrementable
    private Long id;

    @Column(nullable = false) //para que no pueda ser nulo
    private String name;
    @Column(nullable = false) //para que no pueda ser nulo
    private String email;
    private String phone;
    private String address;
    @Column(nullable = false) //para que no pueda ser nulo
    private String city;
    @Column(nullable = false) //para que no pueda ser nulo
    private Integer age;
    @Column(nullable = false) //para que no pueda ser nulo
    private Course course;
    @Column(nullable = false) //para que no pueda ser nulo
    private Date dateInit;

    @ManyToOne // definimos el tipo de relacion que tiene con la tabla de colegio
    @JoinColumn(name = "school_user_id")  // definimos el nombre de la columna que se va a relacionar
    private SchoolUserEntity schoolUser;

    public StudentEntity(StudentDto student, SchoolUserEntity school){
        this.name = student.getName();
        this.email = student.getEmail();
        this.phone = student.getPhone();
        this.address = student.getAddress();
        this.city = student.getCity();
        this.age = student.getAge();
        this.course = student.getCourse();
        this.dateInit = student.getDateInit();
        this.schoolUser = school;
    }
}
