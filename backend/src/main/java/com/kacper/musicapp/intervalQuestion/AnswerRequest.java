package com.kacper.musicapp.intervalQuestion;

import lombok.Builder;

@Builder
public record AnswerRequest(
        Integer questionId,
        String answer
) {
}
