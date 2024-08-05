package com.kacper.musicapp.intervalQuestion;

import com.kacper.musicapp.interval.IntervalRequestDTO;
import com.kacper.musicapp.intervalQuiz.IntervalQuiz;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record IntervalQuestionRequestDTO(
        @Valid
        IntervalRequestDTO interval,

        @NotBlank(message = "Difficulty cannot be blank")
        String difficulty,

        @NotBlank(message = "Option 1 cannot be blank")
        String option1,

        @NotBlank(message = "Option 2 cannot be blank")
        String option2,

        String option3,

        String option4
) {
}
