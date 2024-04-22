package com.quiz.quiiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quiiz.model.Question;
import com.quiz.quiiz.model.QuestionWrapper;
import com.quiz.quiiz.model.Quiz;
import com.quiz.quiiz.model.Response;
import com.quiz.quiiz.repo.QuestionRepo;
import com.quiz.quiiz.repo.QuizRepo;

@Service
public class QuizService {

    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private QuizRepo quizRepo;



    // public QuizService(QuestionRepo questionRepo, QuizRepo quizRepo) {
    //     this.questionRepo = questionRepo;
    //     this.quizRepo = quizRepo;
    // }

	public ResponseEntity<String> createQuiz(String cat, int number, String title) {

        List<Question> list=questionRepo.findRandomQuestionByCategory(cat,number);
        // List<Question> list=questionRepo.findAll();

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        if(list.size()>number){
            list.subList(number, list.size()).clear();
        }
        quiz.setQuestion(list);
        quizRepo.save(quiz);

    

        return new ResponseEntity<>("cerated",HttpStatus.CREATED);

	}



    public ResponseEntity<List<QuestionWrapper>> getAllQuiz(int id) throws Exception {

        Optional<Quiz> quiz=quizRepo.findById(id);
        //check optional with ispresent
        //first get the object
        try{
            List<Question> questions=quiz.get().getQuestion();
            List<QuestionWrapper> wrapper=new ArrayList<>();

            for(Question i : questions){
                wrapper.add(new QuestionWrapper(i.getId(),i.getQuetsionTitle(),i.getOption1(),i.getOtpion2(),i.getOtpion3(),i.getOtpion4()));
            }
            return new ResponseEntity<>(wrapper,HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("null object found",e);
        }



    }



    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
        Quiz quiz=quizRepo.findById(id).get();
        List<Question> questions = quiz.getQuestion();
        int correct=0;
        int iterate=0;
        for(Response i : response){
            if(i.getAnswer().equals(questions.get(iterate).getRightAnswer())){
                correct++;
            }
            iterate++;

        }
        return new ResponseEntity<>(correct,HttpStatus.OK);
    }
    
}
