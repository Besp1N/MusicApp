package com.kacper.musicapp.interval;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class IntervalMapper implements Function<Interval, IntervalResponseDTO>
{
    @Override
    public IntervalResponseDTO apply(Interval interval) {
        return new IntervalResponseDTO(
                interval.getId(),
                interval.getFirstNote(),
                interval.getSecondNote(),
                interval.getDifficulty()
        );
    }
}
