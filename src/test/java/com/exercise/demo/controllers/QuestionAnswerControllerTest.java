package com.exercise.demo.controllers;

import com.exercise.demo.config.Constants;
import com.exercise.demo.model.AnswerRequest;
import com.exercise.demo.model.AnswerResponse;
import com.exercise.demo.model.QuestionRequest;
import com.exercise.demo.model.QuestionResponse;
import com.exercise.demo.service.QueAnsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class QuestionAnswerControllerTest {
    @Mock
    private QueAnsService queAnsService;
    @InjectMocks
    private QuestionAnswerController questionAnswerController;

    @Test
    void generateQuestion() {
        List<Integer> numsToAdd = Arrays.asList(2, 3);
        QuestionResponse mockQuestionResponse = QuestionResponse
                .builder()
                .questionId("SOME_RANDOM_ID")
                .question(Constants.ADDITION_QUESTION)
                .nums(numsToAdd)
                .build();

        QuestionRequest questionRequest = new QuestionRequest("SOME_RANDOM_ID");

        Mockito.when(queAnsService.generateQuestion(questionRequest))
                .thenReturn(mockQuestionResponse);

        ResponseEntity<QuestionResponse> response = questionAnswerController.generateQuestion(questionRequest);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(Constants.ADDITION_QUESTION, response.getBody().getQuestion());

        numsToAdd.forEach(num -> {
            Assertions.assertTrue(response.getBody().getNums().contains(num));
        });
    }

    @Test
    void verifyAnswer() {
        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setQuestionId("SOME_RANDOM_ID");
        answerRequest.setAnswer(5);
        answerRequest.setOriginalQuestion(Constants.ADDITION_QUESTION);
        answerRequest.setNums(Arrays.asList(2,3));

        Mockito
                .when(queAnsService.verifyAnswer(answerRequest))
                .thenReturn(AnswerResponse.builder().message(Constants.CORRECT_ANSWER).build());

        ResponseEntity<AnswerResponse> response = questionAnswerController.verifyAnswer(answerRequest);

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(Constants.CORRECT_ANSWER, response.getBody().getMessage());
    }
}