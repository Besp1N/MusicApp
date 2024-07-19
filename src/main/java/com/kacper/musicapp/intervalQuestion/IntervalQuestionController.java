package com.kacper.musicapp.intervalQuestion;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestBody IntervalQuestionRequestDTO intervalQuestionRequestDTO
    ) {
        return intervalQuestionService.addIntervalQuestion(intervalQuestionRequestDTO);
    }

    @PostMapping("/checkAnswer")
    public Boolean checkAnswer(@RequestBody AnswerRequest answerRequest) {
        return intervalQuestionService.checkAnswer(answerRequest);

    }
}
