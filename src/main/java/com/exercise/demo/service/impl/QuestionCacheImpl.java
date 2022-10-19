package com.exercise.demo.service.impl;

import com.exercise.demo.exception.InvalidQuestionIdException;
import com.exercise.demo.model.QuestionResponse;
import com.exercise.demo.service.QuestionsCache;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class QuestionCacheImpl implements QuestionsCache {
    private final Map<String, QuestionResponse> cache = new HashMap<>();

    @Override
    public void cacheQuestion(String id, QuestionResponse questionResponse) {
        cache.put(id, questionResponse);
    }

    @Override
    public QuestionResponse getQuestionById(String id) {
        if (!cache.containsKey(id)) {
            throw new InvalidQuestionIdException("No such question present for given ID " + id);
        }

        return cache.get(id);
    }

    @Override
    public void removeQuestion(String id) {
        cache.remove(id);
    }
}
