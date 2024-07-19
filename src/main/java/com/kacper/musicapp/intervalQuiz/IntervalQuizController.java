package com.kacper.musicapp.intervalQuiz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/intervalQuiz")
public class IntervalQuizController
{
    @PostMapping("/")
    public IntervalQuiz addQuiz() {
        return null;
    }

}
