package com.exercise.demo.controllers;

import com.exercise.demo.api.QuestionAnswerApi;
import com.exercise.demo.model.AnswerRequest;
import com.exercise.demo.model.AnswerResponse;
import com.exercise.demo.model.QuestionRequest;
import com.exercise.demo.model.QuestionResponse;
import com.exercise.demo.service.QueAnsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QuestionAnswerController implements QuestionAnswerApi {
    private final QueAnsService queAnsService;

    @Override
    @GetMapping("/question")
    public ResponseEntity<QuestionResponse> generateQuestion(@RequestBody QuestionRequest questionRequest) {
        return ResponseEntity.ok(queAnsService.generateQuestion(questionRequest));
    }

    @Override
    @PostMapping("/answer")
    public ResponseEntity<AnswerResponse> verifyAnswer(@RequestBody AnswerRequest answerRequest) {
        return ResponseEntity.ok(queAnsService.verifyAnswer(answerRequest));
    }
}
