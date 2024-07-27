package com.kacper.musicapp.intervalQuiz;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class IntervalQuizShowMapper implements Function<IntervalQuiz, IntervalQuizShowResponseDTO>
{
    @Override
    public IntervalQuizShowResponseDTO apply(IntervalQuiz intervalQuiz) {
        return new IntervalQuizShowResponseDTO(
                intervalQuiz.getId(),
                intervalQuiz.getName(),
                intervalQuiz.getDifficulty()
        );
    }
}
