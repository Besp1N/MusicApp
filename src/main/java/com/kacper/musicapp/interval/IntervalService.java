package com.kacper.musicapp.interval;

import com.kacper.musicapp.exception.DatabaseSaveException;
import com.kacper.musicapp.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntervalService
{
    private final IntervalRepository intervalRepository;
    private final IntervalMapper intervalMapper;

    public IntervalService(
            IntervalRepository intervalRepository,
            IntervalMapper intervalMapper
    ) {
        this.intervalRepository = intervalRepository;
        this.intervalMapper = intervalMapper;
    }

    public List<IntervalResponseDTO> getAllIntervals() {
        return intervalRepository.findAll()
                .stream()
                .map(intervalMapper)
                .collect(Collectors.toList());
    }

    public ResponseEntity<Interval> addInterval(IntervalRequestDTO request) {
        Interval interval = Interval.builder()
                .intervalName(request.intervalName())
                .firstNote(request.firstNote())
                .secondNote(request.secondNote())
                .difficulty(request.difficulty())
                .filePath(request.filePath())
                .build();
        try {
            intervalRepository.save(interval);
        } catch (Exception e) {
            throw new DatabaseSaveException("Field to save");
        }

        return new ResponseEntity<>(interval, HttpStatus.CREATED);
    }

    public IntervalResponseDTO getIntervalById(Integer id) {
        return intervalRepository.findById(id)
                .map(intervalMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Interval not found"));
    }

    public ResponseEntity<Interval> deleteInterval(Integer id) {
        Interval interval = intervalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interval not found"));

        intervalRepository.delete(interval);

        return new ResponseEntity<>(interval, HttpStatus.OK);
    }
}
