package com.exercise.demo.exception;

import com.exercise.demo.model.QuestionResponse;
import lombok.Getter;

@Getter
public class IncorrectAnswerException extends RuntimeException {
    private final QuestionResponse questionResponse;

    public IncorrectAnswerException(String message, QuestionResponse cachedQue) {
        super(message);
        this.questionResponse = cachedQue;
    }
}
