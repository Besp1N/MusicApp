package com.kacper.musicapp.intervalQuiz;

import com.kacper.musicapp.intervalQuestion.IntervalQuestionResponseDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record IntervalQuizResponseDTO(
    String name,
    String difficulty,
    List<IntervalQuestionResponseDTO> questions
) {
}
