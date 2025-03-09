package com.example.apicalls.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Response {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String responseQuestion;
    private Boolean correctAnswer;
     private String uuid = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "id_question")
    private Questions question;

    public Response(String responseQuestion,Boolean correctAnswer, Questions question){
        this.responseQuestion = responseQuestion;
        this.correctAnswer = correctAnswer;
        this.question = question;
    }
}
