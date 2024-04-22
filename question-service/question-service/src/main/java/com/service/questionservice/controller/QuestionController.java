package com.service.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.questionservice.model.Question;
import com.service.questionservice.model.QuestionWrapper;
import com.service.questionservice.model.Response;
import com.service.questionservice.repo.QuestionRepo;
import com.service.questionservice.service.QuestionService;



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

    //QUIZ WILL ASK QUESTION SERVICE TO GIVE QUESTIONS
    //QUIZ HAS ITS OWN DATA BUT NOT QUESTIONS WE NEED TO ASK FOR QUESTION SERVICE [GET QUESTIONS BY ID]
    //CALCULATE SCORES BECAZ QUESTION SERVICE HAS CORRECT ANSWERS


    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam("cat") String cat, @RequestParam("number") Integer number) {
        return questionService.generateQuestionsForQuiz(cat, number);
    }


    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> id) {
        return questionService.getQuestionsById(id);
    }

    @PostMapping("getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> response) {
        return questionService.getScore(response);
    }
}
