package com.kacper.musicapp.intervalQuestion;

import com.kacper.musicapp.interval.IntervalRequestDTO;

public record IntervalQuestionRequestDTO(
        IntervalRequestDTO interval,
        String difficulty,
        String option1,
        String option2,
        String option3,
        String option4
) {
}
