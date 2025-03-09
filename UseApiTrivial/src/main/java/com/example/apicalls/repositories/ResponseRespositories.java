package com.example.apicalls.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.apicalls.entity.Questions;
import com.example.apicalls.entity.Response;
import java.util.List;
import java.util.Optional;


public interface ResponseRespositories extends CrudRepository<Response,Long>{
    
    List<Response> findByQuestion(Questions question);
    Optional<Response> findByQuestionAndUuid(Questions question,String uuid);
}
