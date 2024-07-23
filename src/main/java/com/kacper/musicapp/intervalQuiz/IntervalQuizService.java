package com.kacper.musicapp.intervalQuiz;

import com.kacper.musicapp.intervalQuestion.IntervalQuestionService;
import org.springframework.stereotype.Service;


@Service
public class IntervalQuizService
{
    private final IntervalQuizRepository intervalQuizRepository;
    private final IntervalQuestionService intervalQuestionService;

    public IntervalQuizService(
            IntervalQuizRepository intervalQuizRepository,
            IntervalQuestionService intervalQuestionService
    ) {
        this.intervalQuizRepository = intervalQuizRepository;
        this.intervalQuestionService = intervalQuestionService;
    }

    public IntervalQuiz addEmptyIntervalQuiz() {
        return null;
    }

}
