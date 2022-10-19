package com.exercise.demo.service.impl;

import com.exercise.demo.config.Constants;
import com.exercise.demo.exception.IncorrectAnswerException;
import com.exercise.demo.exception.InvalidQuestionIdException;
import com.exercise.demo.model.AnswerRequest;
import com.exercise.demo.model.AnswerResponse;
import com.exercise.demo.model.QuestionRequest;
import com.exercise.demo.model.QuestionResponse;
import com.exercise.demo.service.QuestionsCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static com.exercise.demo.config.Constants.CORRECT_ANSWER;

@ExtendWith(MockitoExtension.class)
class QueAnsServiceImplTest {
    public static final String QUESTION_ID = "SOME_RANDOM_ID";
    @Mock
    private QuestionsCache questionsCache;
    @InjectMocks
    private QueAnsServiceImpl queAnsService;

    @Test
    void generateQuestion() {
        QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.setQuestionId(QUESTION_ID);

        QuestionResponse questionResponse = queAnsService.generateQuestion(questionRequest);

        Assertions.assertEquals(QUESTION_ID, questionResponse.getQuestionId());
        Assertions.assertEquals(Constants.ADDITION_QUESTION, questionResponse.getQuestion());
        Assertions.assertEquals(2, questionResponse.getNums().size());
    }

    @Test
    void verifyAnswer() {
        QuestionResponse mockQuestionResponse = QuestionResponse
                .builder()
                .questionId(QUESTION_ID)
                .question(Constants.ADDITION_QUESTION)
                .nums(Arrays.asList(2, 3))
                .build();

        Mockito.when(questionsCache.getQuestionById(QUESTION_ID))
                .thenReturn(mockQuestionResponse);

        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setAnswer(5);
        answerRequest.setNums(Arrays.asList(2, 3));
        answerRequest.setOriginalQuestion(Constants.ADDITION_QUESTION);
        answerRequest.setQuestionId(QUESTION_ID);

        AnswerResponse answerResponse = queAnsService.verifyAnswer(answerRequest);

        Assertions.assertEquals(CORRECT_ANSWER, answerResponse.getMessage());
    }

    @Test
    void verifyAnswerWithIncorrectQuestion() {
        QuestionResponse mockQuestionResponse = QuestionResponse
                .builder()
                .questionId(QUESTION_ID)
                .question("Multiply the numbers")
                .nums(Arrays.asList(2, 3))
                .build();

        Mockito.when(questionsCache.getQuestionById(QUESTION_ID))
                .thenReturn(mockQuestionResponse);

        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setAnswer(5);
        answerRequest.setNums(Arrays.asList(2, 3));
        answerRequest.setOriginalQuestion(Constants.ADDITION_QUESTION);
        answerRequest.setQuestionId(QUESTION_ID);

        Assertions.assertThrows(InvalidQuestionIdException.class, () -> queAnsService.verifyAnswer(answerRequest));
    }

    @Test
    void verifyAnswerWithIncorrectCalculation() {
        QuestionResponse mockQuestionResponse = QuestionResponse
                .builder()
                .questionId(QUESTION_ID)
                .question(Constants.ADDITION_QUESTION)
                .nums(Arrays.asList(2, 3))
                .build();

        Mockito.when(questionsCache.getQuestionById(QUESTION_ID))
                .thenReturn(mockQuestionResponse);

        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setAnswer(6);
        answerRequest.setNums(Arrays.asList(2, 3));
        answerRequest.setOriginalQuestion(Constants.ADDITION_QUESTION);
        answerRequest.setQuestionId(QUESTION_ID);

        Assertions.assertThrows(IncorrectAnswerException.class, () -> queAnsService.verifyAnswer(answerRequest));
    }
}