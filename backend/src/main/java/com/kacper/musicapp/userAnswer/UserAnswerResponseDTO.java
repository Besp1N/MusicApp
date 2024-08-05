package com.kacper.musicapp.userAnswer;

import lombok.Builder;

@Builder
public record UserAnswerResponseDTO(
        String givenAnswer,
        boolean isCorrect
) {
}
