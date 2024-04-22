package com.quizservice.quizservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizservice.quizservice.model.Quiz;



public interface QuizRepo extends JpaRepository<Quiz,Integer>{



}
