package com.exercise.demo.service;

import com.exercise.demo.model.QuestionResponse;

import java.util.UUID;

public interface QuestionsCache {
    void cacheQuestion(String id, QuestionResponse questionResponse);

    QuestionResponse getQuestionById(String id);

    void removeQuestion(String id);
}
