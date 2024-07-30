package com.kacper.musicapp.intervalQuestion;

import com.kacper.musicapp.interval.IntervalResponseDTO;
import com.kacper.musicapp.userAnswer.UserAnswer;

public record IntervalQuestionResponseDTO(
        Integer id,
        IntervalResponseDTO interval,
        UserAnswer userAnswer,
        String difficulty,
        String option1,
        String option2,
        String option3,
        String option4
)
{
}
