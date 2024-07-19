package com.kacper.musicapp.intervalQuestion;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class IntervalQuestionMapper implements Function<IntervalQuestion, IntervalQuestionResponseDTO>
{

    @Override
    public IntervalQuestionResponseDTO apply(IntervalQuestion intervalQuestion) {
        return new IntervalQuestionResponseDTO(
                intervalQuestion.getId(),
                intervalQuestion.getInterval().getId(),
                intervalQuestion.getDifficulty(),
                intervalQuestion.getOption1(),
                intervalQuestion.getOption2(),
                intervalQuestion.getOption3(),
                intervalQuestion.getOption4()
        );
    }
}
