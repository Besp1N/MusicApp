package com.kacper.musicapp.interval;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interval")
public class IntervalController
{
    private final IntervalService intervalService;

    public IntervalController(IntervalService intervalService) {
        this.intervalService = intervalService;
    }

    @GetMapping("/")
    public List<IntervalResponseDTO> getAllIntervals() {
        return intervalService.getAllIntervals();
    }

    @PostMapping("/")
    public ResponseEntity<Interval> addInterval(
            @Valid
            @RequestBody IntervalRequestDTO request
    ) {
        return intervalService.addInterval(request);
    }

    @GetMapping("/{id}")
    public IntervalResponseDTO getIntervalById(@PathVariable Integer id) {
        return intervalService.getIntervalById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Interval> deleteInterval(@PathVariable Integer id) {
        return intervalService.deleteInterval(id);
    }
}
