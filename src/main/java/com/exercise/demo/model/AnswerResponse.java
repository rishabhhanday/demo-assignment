package com.exercise.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerResponse {
    private String message;
}

