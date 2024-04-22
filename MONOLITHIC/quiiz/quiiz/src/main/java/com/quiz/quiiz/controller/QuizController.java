package com.quiz.quiiz.controller;

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

import com.quiz.quiiz.model.QuestionWrapper;
import com.quiz.quiiz.model.Response;
import com.quiz.quiiz.repo.QuizRepo;
import com.quiz.quiiz.service.QuizService;

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
    public ResponseEntity<String> cerateQuiz(@RequestParam("cat") String cat,
                                            @RequestParam("no") int number,
                                            @RequestParam("title") String title){
        return quizService.createQuiz(cat,number,title);
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
