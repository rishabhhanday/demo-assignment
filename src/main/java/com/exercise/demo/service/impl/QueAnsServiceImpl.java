package com.exercise.demo.service.impl;

import com.exercise.demo.exception.IncorrectAnswerException;
import com.exercise.demo.exception.InvalidQuestionIdException;
import com.exercise.demo.model.AnswerRequest;
import com.exercise.demo.model.AnswerResponse;
import com.exercise.demo.model.QuestionRequest;
import com.exercise.demo.model.QuestionResponse;
import com.exercise.demo.service.QueAnsService;
import com.exercise.demo.service.QuestionsCache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.exercise.demo.config.Constants.ADDITION_QUESTION;
import static com.exercise.demo.config.Constants.CORRECT_ANSWER;

@Service
@AllArgsConstructor
@Slf4j
public class QueAnsServiceImpl implements QueAnsService {
    private QuestionsCache questionsCache;

    @Override
    public QuestionResponse generateQuestion(QuestionRequest questionRequest) {
        Random random = new Random();

        QuestionResponse questionResponse = QuestionResponse
                .builder()
                .question(ADDITION_QUESTION)
                .nums(Arrays.asList(random.nextInt(100), random.nextInt(100)))
                .questionId(questionRequest.getQuestionId())
                .build();

        log.info("Caching new generated question id={}, nums to add={}",
                questionRequest.getQuestionId(),
                questionResponse.getNums());

        questionsCache.cacheQuestion(questionRequest.getQuestionId(), questionResponse);

        return questionResponse;
    }

    @Override
    public AnswerResponse verifyAnswer(AnswerRequest answerRequest) {
        QuestionResponse cachedQue = questionsCache.getQuestionById(answerRequest.getQuestionId());

        if (!cachedQue.getQuestion().equals(answerRequest.getOriginalQuestion()) || !cachedQue.getNums().equals(answerRequest.getNums())) {
            log.error("Question in request does not match with cached question.");
            throw new InvalidQuestionIdException("Question was not created for this client. Invalid questionId=" + answerRequest.getQuestionId());
        }

        if (getAnswer(answerRequest.getNums()) != answerRequest.getAnswer()) {
            log.error("Sum of nums {} is not equal to {}", answerRequest.getNums(), answerRequest.getAnswer());
            throw new IncorrectAnswerException("Answer did not match.", cachedQue);
        }

        log.info("Answer is correct for given questionId. Removing question from cache.");
        questionsCache.removeQuestion(answerRequest.getQuestionId());

        return AnswerResponse
                .builder()
                .message(CORRECT_ANSWER)
                .build();
    }

    private int getAnswer(List<Integer> numsToAdd) {
        int sum = 0;
        for (Integer num : numsToAdd) {
            sum = sum + num;
        }

        return sum;
    }
}
