package com.kacper.musicapp.intervalQuiz;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intervalQuiz")
public class IntervalQuizController
{
    private final IntervalQuizService intervalQuizService;

    public IntervalQuizController(
            IntervalQuizService intervalQuizService
    ) {
        this.intervalQuizService = intervalQuizService;

    }

    @PostMapping("/")
    public IntervalQuiz addEmptyIntervalQuiz(@RequestBody IntervalQuizRequestDTO intervalQuizRequestDTO) {
        return intervalQuizService.addEmptyIntervalQuiz(intervalQuizRequestDTO);
    }

    @GetMapping("/")
    public List<IntervalQuizShowResponseDTO> getIntervalQuizzes() {
        return intervalQuizService.getIntervalQuizzes();
    }

    @GetMapping("/{quizId}")
    public IntervalQuizResponseDTO getIntervalQuizById(
            @PathVariable Integer quizId
    ) {
        return intervalQuizService.getIntervalQuizById(quizId);

    }

    @PatchMapping("/{quizId}/questions")
    public IntervalQuiz addQuestions(
            @PathVariable Integer quizId,
            @RequestBody IntervalQuizAddQuestionsRequestDAO intervalQuizAddQuestionsRequestDAO
    ) {
        return intervalQuizService.addQuestions(quizId, intervalQuizAddQuestionsRequestDAO);
    }




}
