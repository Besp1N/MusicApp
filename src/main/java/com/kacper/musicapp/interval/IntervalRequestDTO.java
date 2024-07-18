package com.kacper.musicapp.interval;

public record IntervalRequestDTO(
        String intervalName,
        String firstNote,
        String secondNote,
        String difficulty
) {
}
