package com.quizservice.quizservice.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;


    @ElementCollection
    private List<Integer> questionId;

    //do not use many to many
    //NOW THERE IS NO CONNECTION BETWEEN QUESTION AND QUIZ TABLE
}
