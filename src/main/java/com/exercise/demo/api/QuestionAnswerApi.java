package com.exercise.demo.api;

import com.exercise.demo.model.AnswerRequest;
import com.exercise.demo.model.AnswerResponse;
import com.exercise.demo.model.QuestionRequest;
import com.exercise.demo.model.QuestionResponse;
import org.springframework.http.ResponseEntity;

public interface QuestionAnswerApi {
    ResponseEntity<QuestionResponse> generateQuestion(QuestionRequest questionRequest);
    ResponseEntity<AnswerResponse> verifyAnswer(AnswerRequest answerRequest);
}
