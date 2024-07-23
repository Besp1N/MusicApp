package com.kacper.musicapp.intervalQuestion;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/intervalQuestion")
public class IntervalQuestionController
{
    private final IntervalQuestionService intervalQuestionService;

    public IntervalQuestionController(IntervalQuestionService intervalQuestionService) {
        this.intervalQuestionService = intervalQuestionService;
    }

    @GetMapping("/")
    public List<IntervalQuestionResponseDTO> getAllIntervalQuestion() {
        return intervalQuestionService.findAllIntervalQuestions();
    }

    @PostMapping("/")
    public ResponseEntity<IntervalQuestion> addIntervalQuestion(
           @Valid
           @RequestBody IntervalQuestionRequestDTO intervalQuestionRequestDTO,
              @RequestParam(required = false) Integer quizId
    ) {
        return intervalQuestionService.addIntervalQuestion(intervalQuestionRequestDTO, Optional.ofNullable(quizId));
    }

    @PostMapping("/checkAnswer")
    public Boolean checkAnswer(@RequestBody AnswerRequest answerRequest) {
        return intervalQuestionService.checkAnswer(answerRequest);

    }
}
