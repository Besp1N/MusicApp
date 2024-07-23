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
    public IntervalQuiz addIntervalQuiz(@RequestBody IntervalQuizRequestDAO intervalQuizRequestDAO) {
        return intervalQuizService.addIntervalQuiz(intervalQuizRequestDAO);
    }

}
