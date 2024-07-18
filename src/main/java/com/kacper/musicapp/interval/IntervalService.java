package com.kacper.musicapp.interval;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervalService
{
    private final IntervalRepository intervalRepository;

    public IntervalService(IntervalRepository intervalRepository) {
        this.intervalRepository = intervalRepository;
    }

    public ResponseEntity<List<Interval>> getAllIntervals() {
        return new ResponseEntity<>(intervalRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Interval> addInterval(Interval request) {
        Interval interval = Interval.builder()
                .intervalName(request.getIntervalName())
                .firstNote(request.getFirstNote())
                .secondNote(request.getSecondNote())
                .difficulty(request.getDifficulty())
                .build();

        Interval intervalDB = intervalRepository.save(interval);
        return new ResponseEntity<>(intervalDB, HttpStatus.CREATED);
    }
}
