package com.exercise.demo.exception;

public class InvalidQuestionIdException extends RuntimeException {
    public InvalidQuestionIdException(String message) {
        super(message);
    }
}
