package com.kacper.musicapp.intervalQuiz;

import jakarta.validation.constraints.NotBlank;

public record IntervalQuizRequestDTO(
        @NotBlank(message = "Name can not be blank")
        String name,

        @NotBlank(message = "Difficulty can not be blank")
        String difficulty
) {
}
