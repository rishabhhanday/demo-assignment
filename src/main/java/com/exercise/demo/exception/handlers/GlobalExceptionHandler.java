package com.exercise.demo.exception.handlers;

import com.exercise.demo.exception.IncorrectAnswerException;
import com.exercise.demo.exception.InvalidQuestionIdException;
import com.exercise.demo.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IncorrectAnswerException.class})
    public ResponseEntity<ErrorResponse> handleInvalidAnswerException(IncorrectAnswerException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Sorry the answer is incorrect for the ques - " + ex.getQuestionResponse().getQuestion() + " " + ex.getQuestionResponse().getNums())
                .build();

        return ResponseEntity
                .status(400)
                .body(errorResponse);
    }

    @ExceptionHandler({InvalidQuestionIdException.class})
    public ResponseEntity<ErrorResponse> handleInvalidQuestionIdException(InvalidQuestionIdException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Question was not generated for this client.")
                .build();

        return ResponseEntity
                .status(400)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Something went wrong. " + ex.getMessage())
                .build();

        return ResponseEntity
                .status(500)
                .body(errorResponse);
    }
}
