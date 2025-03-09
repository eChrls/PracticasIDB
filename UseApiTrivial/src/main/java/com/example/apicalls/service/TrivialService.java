package com.example.apicalls.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.apicalls.entity.Questions;
import com.example.apicalls.entity.Response;
import com.example.apicalls.model.QuestionDto.ResponseDTO;
import com.example.apicalls.model.QuestionDto;
import com.example.apicalls.model.ResponseCallDto;
import com.example.apicalls.model.ResponseCallDto.Results;
import com.example.apicalls.repositories.QuestionsRepositories;
import com.example.apicalls.repositories.ResponseRespositories;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrivialService {

    @Value("${url.trivials}")
    private String url;

    private final RestTemplate restTemplate;
    private final QuestionsRepositories questionsRepositories;
    private final ResponseRespositories responseRespositories;
    private final ObjectMapper objectMapper;

    public ResponseEntity<String> addQuestion(Integer amount) {

        try {
            List<Long> listQuestions = questionsRepositories.getunresolvedQuestions();
            if (listQuestions.isEmpty()) {
                ResponseEntity<ResponseCallDto> response = restTemplate.getForEntity(url + amount, ResponseCallDto.class);
                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    List<Results> results = response.getBody().getResults();
                    for (Results result : results) {
                        Questions questionEntity = new Questions(result.getQuestion());
                        questionsRepositories.save(questionEntity);
                        Response responseEntity = new Response(result.getCorrect(), true, questionEntity);
                        responseRespositories.save(responseEntity);
                        result.getIncorrectAnswers().forEach(incorrectAnswers -> {
                            Response responseBadEntity = new Response(incorrectAnswers, false, questionEntity);
                            responseRespositories.save(responseBadEntity);
                        });

                    }
                }
                log.info(response.getBody().toString());
                return ResponseEntity.ok().body("ok");
            } else {
                return ResponseEntity.ok().body("Todavia hay estas preguntas sin resolver " + listQuestions.toString()
                        + " porfavor resolverlas antes de añadir mas");
            }
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
        }
        return ResponseEntity.internalServerError().body("error");

    }

    public ResponseEntity<String> getQuestion(Long id) {
        Optional<Questions> question = questionsRepositories.findByIdAndResolvedFalse(id);
        if(question.isPresent()){
            Questions questionL = question.get();
            List<ResponseDTO> responseListDto = new ArrayList<>();
            responseRespositories.findByQuestion(questionL).forEach(responseL ->{
                ResponseDTO responseDto = new ResponseDTO(responseL.getId(), responseL.getResponseQuestion());
                responseListDto.add(responseDto);
            });
            Collections.shuffle(responseListDto); 
            QuestionDto questionResponse = new QuestionDto(questionL.getId(),questionL.getQuestion(), responseListDto);
            try {
                return ResponseEntity.ok().body(objectMapper.writeValueAsString(questionResponse));
            } catch (JsonProcessingException e) {
               log.error("error to jsonParse. Error : {}", e.getMessage(),e);
               return ResponseEntity.internalServerError().body("internal error");
            }
        }
        return ResponseEntity.internalServerError().body("not find in our dataBase or just resolved");
 
    }

    public ResponseEntity<String> tryResponse(Long idQuestion,String uuid){
        Optional<Questions> questionOpt = questionsRepositories.findById(idQuestion);
        if(questionOpt.isPresent()){
            Questions question = questionOpt.get();
            Optional<Response> responseOpt = responseRespositories.findByQuestionAndUuid(question, uuid);
            if(responseOpt.isPresent()){
                Response response = responseOpt.get();
                if(response.getCorrectAnswer()){
                    question.setResolved(true);
                    questionsRepositories.save(question);
                    return ResponseEntity.ok().body("la respuesta ha sido correcta");
                }else{
                    return ResponseEntity.ok().body("la respuesta ha sido incorrecta intentelo de nuevo");
                }
            }
            return ResponseEntity.internalServerError().body("not find in our dataBase"); 
        }
        return ResponseEntity.internalServerError().body("not find in our dataBase");
    }



}
