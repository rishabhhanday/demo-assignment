package com.exercise.demo.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AnswerRequest {
    @NotEmpty
    private String originalQuestion;
    @NotNull
    private String questionId;
    @NotNull
    private List<Integer> nums;
    @NotEmpty
    private Integer answer;
}
