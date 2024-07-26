package com.kacper.musicapp.intervalQuiz;

import com.kacper.musicapp.intervalQuestion.IntervalQuestionRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record IntervalQuizRequestDAO(
        @NotBlank(message = "Name can not be blank")
        String name,

        @NotBlank(message = "Difficulty can not be blank")
        String difficulty
) {
}
