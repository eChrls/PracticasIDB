package com.example.apicalls.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    private String question;
    private List<ResponseDTO> responses;

    @Data
    @AllArgsConstructor
    public static class ResponseDTO{
        private Long id;
        private String response;
    }
}
