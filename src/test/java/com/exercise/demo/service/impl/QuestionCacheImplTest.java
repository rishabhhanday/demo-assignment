package com.exercise.demo.service.impl;

import com.exercise.demo.config.Constants;
import com.exercise.demo.exception.InvalidQuestionIdException;
import com.exercise.demo.model.QuestionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class QuestionCacheImplTest {
    private final QuestionCacheImpl questionCache = new QuestionCacheImpl();

    @Test
    void cacheQuestion() {
        questionCache.cacheQuestion("SOME_RANDOM_ID", createQuestionResponse());

        QuestionResponse questionResponse = questionCache.getQuestionById("SOME_RANDOM_ID");
        Assertions.assertEquals("SOME_RANDOM_ID", questionResponse.getQuestionId());
    }


    @Test
    void removeQuestion() {
        questionCache.cacheQuestion("SOME_RANDOM_ID", createQuestionResponse());

        QuestionResponse questionResponse = questionCache.getQuestionById("SOME_RANDOM_ID");
        Assertions.assertEquals("SOME_RANDOM_ID", questionResponse.getQuestionId());
        questionCache.removeQuestion("SOME_RANDOM_ID");

        Assertions.assertThrows(InvalidQuestionIdException.class, () -> questionCache.getQuestionById("SOME_RANDOM_ID"));
    }

    private QuestionResponse createQuestionResponse() {
        return QuestionResponse
                .builder()
                .questionId("SOME_RANDOM_ID")
                .question(Constants.ADDITION_QUESTION)
                .nums(Arrays.asList(2, 3))
                .build();
    }
}