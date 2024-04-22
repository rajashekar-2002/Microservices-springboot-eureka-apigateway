package com.quiz.quiiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quiiz.model.Question;
import com.quiz.quiiz.repo.QuestionRepo;
import com.quiz.quiiz.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionRepo questionRepo;


    
    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{cat}")
    public List<Question> getByCategory(@PathVariable("cat") String cat){
        return questionService.getByCategory(cat);
    }


    @PostMapping("/add")
    public String addQuestion(@RequestBody Question object){
        questionRepo.save(object);
        return "success";
    }
}
