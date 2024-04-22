package com.quiz.quiiz.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quiz.quiiz.model.Question;

public interface QuestionRepo extends JpaRepository<Question,Integer>{

    List<Question> findAllByCategory(String cat);

    // use native query or entity manager to write query
    // use ?1 or =:cat
    //getting error for LIMIT=?2
    @Query(value = "SELECT * FROM question q Where q.category= ?1 ORDER BY RAND() LIMIT 2",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(@Param("cat") String cat,@Param("number") int number);
    
}
