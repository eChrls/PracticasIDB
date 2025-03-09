package com.example.apicalls.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apicalls.service.TrivialService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping; 



@Slf4j
@RestController
@RequestMapping("/trivial")
@RequiredArgsConstructor
public class TrivialController {
    
    private final TrivialService trivialService;

    @GetMapping("/insert_questions")
    public ResponseEntity<String> getMethodName(@RequestParam Integer amount) {
        return trivialService.addQuestion(amount);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<String> getquestion(@PathVariable Long id) {
        return trivialService.getQuestion(id);
    }

    @PostMapping("/resolver-question/{id}")
    public ResponseEntity<String> postMethodName(@RequestParam(name = "response") String uuid,@PathVariable(name = "id") Long idQuestion) {
        
        return trivialService.tryResponse(idQuestion, uuid);
    }
    
    
}
