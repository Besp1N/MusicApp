package com.kacper.musicapp.intervalQuiz;

import com.kacper.musicapp.intervalQuestion.IntervalQuestionRequestDTO;
import jakarta.validation.Valid;

import java.util.Set;

public record IntervalQuizAddQuestionsRequestDAO(
        @Valid
        Set<IntervalQuestionRequestDTO> questions
) {
}
