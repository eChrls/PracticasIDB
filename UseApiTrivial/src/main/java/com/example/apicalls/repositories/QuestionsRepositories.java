package com.example.apicalls.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.apicalls.entity.Questions;

public interface QuestionsRepositories extends CrudRepository<Questions,Long>{
    
    @Query(value = "SELECT id FROM question where resolved=0",nativeQuery = true)
    List<Long> getunresolvedQuestions();

    Optional<Questions> findByIdAndResolvedFalse(Long id);
}
