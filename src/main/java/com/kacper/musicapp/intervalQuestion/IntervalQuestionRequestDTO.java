package com.kacper.musicapp.intervalQuestion;

public record IntervalQuestionRequestDTO(
        Integer intervalId,
        String difficulty,
        String option1,
        String option2,
        String option3,
        String option4
) {
}
