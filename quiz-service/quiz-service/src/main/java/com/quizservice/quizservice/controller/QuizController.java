package com.quizservice.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizservice.quizservice.model.QuestionWrapper;
import com.quizservice.quizservice.model.QuizDTO;
import com.quizservice.quizservice.model.Response;
import com.quizservice.quizservice.repo.QuizRepo;
import com.quizservice.quizservice.service.QuizService;



@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @Autowired
    QuizRepo quizRepo;

    // public QuizController(QuizService quizService, QuizRepo quizRepo) {
    //     this.quizService = quizService;
    //     this.quizRepo = quizRepo;
    // }

    
    // http://localhost:8000/quiz/create?cat=java&no=3&title=jquiz
    @PostMapping("/create")
    public ResponseEntity<String> cerateQuiz(@RequestBody QuizDTO quizdto){
        return quizService.createQuiz(quizdto.getCategory(),quizdto.getNumber(),quizdto.getTitle());
    }

    @GetMapping("/getquiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getAllQuiz(@PathVariable("id") Integer id) throws Exception{
        return quizService.getAllQuiz(id);
    }


    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submit(@PathVariable("id") Integer id, @RequestBody List<Response> response){
        return quizService.calculateResult(id,response);
    }
}
