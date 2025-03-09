package com.crud.back.model;

import java.sql.Date;

import com.crud.back.enums.Course;

import lombok.Data;

@Data
public class StudentDto {

    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private Integer age;
    private Course course;
    private Date dateInit;
    
}
