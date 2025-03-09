package com.sample.zelda.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
// sirve para ignorar algun atribuno y no incluirlo @JsonIgnoreProperties
public class JsonZelda {
private Boolean success;
private Integer count;
private List<DataZelda> data;

@Data
public static class DataZelda {
    private String name;
    private String description; 
    private String developer; 
    @JsonAlias("released_date")
    private String date;
    private String id;
}



}
