package com.kacper.musicapp.interval;

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
    public ResponseEntity<List<Interval>> getAllIntervals() {
        return ResponseEntity.ok(intervalService.getAllIntervals().getBody());
    }

    @PostMapping("/")
    public ResponseEntity<Interval> addInterval(@RequestBody Interval request) {
        return ResponseEntity.ok(intervalService.addInterval(request).getBody());
    }
}
