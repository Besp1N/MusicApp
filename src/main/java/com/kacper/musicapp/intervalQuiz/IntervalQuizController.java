package com.kacper.musicapp.intervalQuiz;

import com.kacper.musicapp.intervalQuestion.IntervalQuestion;
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
    public IntervalQuiz addEmptyIntervalQuiz(@RequestBody IntervalQuizRequestDAO intervalQuizRequestDAO) {
        return intervalQuizService.addEmptyIntervalQuiz(intervalQuizRequestDAO);
    }

    @PatchMapping("/{quizId}/questions")
    public IntervalQuiz addQuestions(
            @PathVariable Integer quizId,
            @RequestBody IntervalQuizAddQuestionsRequestDAO intervalQuizAddQuestionsRequestDAO
    ) {
        return intervalQuizService.addQuestions(quizId, intervalQuizAddQuestionsRequestDAO);

    }


}
