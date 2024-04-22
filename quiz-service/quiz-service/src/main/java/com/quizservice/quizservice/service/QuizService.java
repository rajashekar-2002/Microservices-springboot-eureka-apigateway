package com.quizservice.quizservice.service;

import java.util.ArrayList;
import java.util.List;

import com.quizservice.quizservice.Feign.QuizFeign;
import com.quizservice.quizservice.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizservice.quizservice.model.QuestionWrapper;
import com.quizservice.quizservice.model.Response;
import com.quizservice.quizservice.repo.QuizRepo;



@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizFeign quizFeign;



	public ResponseEntity<String> createQuiz(String cat, int number, String title) {

        //WE DONT NEED TO STORE QUESTION IN QUIZ JUST GET ID
        //CALL generateQuestions /generate url FROM QUESTIONS
        //ONCE QUESTION SERVICE IS REGISTERED TO EUREKA WE CAN ACCESS USING FEIGN
        List<Integer> list=quizFeign.generateQuestions(cat,number).getBody();

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionId(list);
        quizRepo.save(quiz);


    

        return new ResponseEntity<>("cerated",HttpStatus.CREATED);

	}



    public ResponseEntity<List<QuestionWrapper>> getAllQuiz(int id) throws Exception {

            Quiz quiz=quizRepo.findById(id).get();
            List<Integer> ids=quiz.getQuestionId();
            ResponseEntity<List<QuestionWrapper>> questions_list = quizFeign.getQuestionsFromId(ids);

            return questions_list;
        }



    



    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {

        ResponseEntity<Integer> correct=quizFeign.getScore(response);

        return correct;
    }
    
}
