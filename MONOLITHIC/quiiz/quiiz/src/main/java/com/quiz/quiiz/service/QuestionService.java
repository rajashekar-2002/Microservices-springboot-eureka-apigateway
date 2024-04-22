package com.quiz.quiiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quiiz.model.Question;
import com.quiz.quiiz.repo.QuestionRepo;


@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;



    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
        return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
    }

    public List<Question> getByCategory(String cat) {
        return questionRepo.findAllByCategory(cat);
    }
}
