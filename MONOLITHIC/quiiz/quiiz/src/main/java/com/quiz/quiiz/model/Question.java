package com.quiz.quiiz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    // @SequenceGenerator(name="seq",sequenceName = "seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String quetsionTitle;
    private String option1;
    private String otpion2;
    private String otpion3;
    private String otpion4;
    private String rightAnswer;
    private String level;
    private String category;
}
