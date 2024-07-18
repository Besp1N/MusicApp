package com.kacper.musicapp.interval;

public record IntervalResponseDTO(
        Integer id,
        String firstNote,
        String secondNote,
        String difficulty
) {
}
