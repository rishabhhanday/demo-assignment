package com.exercise.demo.service;

import com.exercise.demo.model.AnswerRequest;
import com.exercise.demo.model.AnswerResponse;
import com.exercise.demo.model.QuestionRequest;
import com.exercise.demo.model.QuestionResponse;

public interface QueAnsService {
    QuestionResponse generateQuestion(QuestionRequest questionRequest);

    AnswerResponse verifyAnswer(AnswerRequest answerRequest);
}
