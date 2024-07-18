package com.kacper.musicapp.interval;

import jakarta.validation.constraints.NotBlank;

public record IntervalRequestDTO(
        @NotBlank(message = "Interval name cannot be blank")
        String intervalName,

        @NotBlank(message = "First note cannot be blank")
        String firstNote,

        @NotBlank(message = "Second note cannot be blank")
        String secondNote,

        @NotBlank(message = "Difficulty cannot be blank")
        String difficulty
) {
}
