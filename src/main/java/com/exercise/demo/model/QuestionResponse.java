package com.exercise.demo.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionResponse {
    private String questionId;
    private String question;
    private List<Integer> nums;
}


