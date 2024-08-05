package com.kacper.musicapp.intervalQuiz;

import lombok.Builder;

@Builder
public record IntervalQuizShowResponseDTO(
        Integer id,
        String name,
        String difficulty
) {
}
