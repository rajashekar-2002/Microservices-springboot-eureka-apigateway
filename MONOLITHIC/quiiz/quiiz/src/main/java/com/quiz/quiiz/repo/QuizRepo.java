package com.quiz.quiiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.quiiz.model.Quiz;

public interface QuizRepo extends JpaRepository<Quiz,Integer>{



}
