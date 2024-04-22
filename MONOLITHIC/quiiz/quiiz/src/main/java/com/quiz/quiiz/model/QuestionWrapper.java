package com.quiz.quiiz.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
    @Id
    // @SequenceGenerator(name="seq",sequenceName = "seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String quetsionTitle;
    private String option1;
    private String otpion2;
    private String otpion3;
    private String otpion4;
}
