package com.quizservice.quizservice.Feign;

import com.quizservice.quizservice.model.QuestionWrapper;
import com.quizservice.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizFeign {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam("cat") String cat, @RequestParam("number") Integer number);

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> id);

    @PostMapping("question/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);
}
