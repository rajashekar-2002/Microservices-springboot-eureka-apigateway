package com.service.questionservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.questionservice.model.Question;
import com.service.questionservice.model.QuestionWrapper;
import com.service.questionservice.model.Response;
import com.service.questionservice.repo.QuestionRepo;




@Service
public class QuestionService {

    @Autowired
    Environment environment;

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


    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String cat, Integer number) {
        List<Integer> list=questionRepo.findRandomQuestionByCategory(cat,number);
        list.subList(number,list.size()).clear();
        return new ResponseEntity<>(list,HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> id) {
        System.out.println("////////// CHECK PORT FOR LOAD BALANCER ///////////");
        System.out.println(environment.getProperty("local.server.port"));
        List<QuestionWrapper> list=new ArrayList<>();
        List<Question> questions=new ArrayList<>();

        for(Integer i: id){
            questions.add(questionRepo.findById(i).get());
        }
        for(Question i:questions){
            list.add(new QuestionWrapper(i.getId(),i.getQuetsionTitle(),i.getOption1(),i.getOtpion2(),i.getOtpion3(),i.getOtpion4()));
        }

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> response) {
        int count=0;
        for(Response i : response){
            Question question = questionRepo.findById(i.getId()).get();
            if(i.getAnswer().equals(question.getRightAnswer())){
                count++;
            }
        }
        return new ResponseEntity<>(count,HttpStatus.OK);
    }



}
