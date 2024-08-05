package com.kacper.musicapp.intervalQuestion;

import com.kacper.musicapp.interval.Interval;
import lombok.Builder;

@Builder
public record AnswerResponse(
        Interval interval,
        Boolean isCorrect
) {
}
