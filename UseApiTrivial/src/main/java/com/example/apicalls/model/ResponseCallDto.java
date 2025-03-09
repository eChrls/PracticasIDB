package com.example.apicalls.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class ResponseCallDto {
    @JsonAlias("response_code")
    private Integer response;
    private List<Results> results;

    @Data
    public static class Results {
        private String type;
        private String difficulty;
        private String category;
        private String question;
        @JsonAlias("correct_answer")
        private String correct;
        @JsonAlias("incorrect_answers")
        private List<String> incorrectAnswers;	

    }
}
