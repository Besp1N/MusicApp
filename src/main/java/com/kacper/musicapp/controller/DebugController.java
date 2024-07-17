package com.kacper.musicapp.controller;

import com.kacper.musicapp.model.IntervalQuestions;
import com.kacper.musicapp.model.Intervals;
import com.kacper.musicapp.repository.IntervalQuestionsRepository;
import com.kacper.musicapp.repository.IntervalsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/debug")
public class DebugController
{
    private final IntervalsRepository intervalsRepository;
    private final IntervalQuestionsRepository intervalQuestionsRepository;

    public DebugController(
            IntervalsRepository intervalsRepository,
            IntervalQuestionsRepository intervalQuestionsRepository1
    ) {
        this.intervalsRepository = intervalsRepository;
        this.intervalQuestionsRepository = intervalQuestionsRepository1;
    }


    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @PostMapping("/addInterval")
    public ResponseEntity<Intervals> addInterval(@RequestBody Intervals request) {
        Intervals interval = new Intervals();
        interval.setIntervalName(request.getIntervalName());
        interval.setFirstNote(request.getFirstNote());
        interval.setSecondNote(request.getSecondNote());

        return ResponseEntity.ok(intervalsRepository.save(interval));
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<IntervalQuestions> addQuestion(@RequestBody IntervalQuestions request) {
        IntervalQuestions question = new IntervalQuestions();
        Optional<Intervals> interval = intervalsRepository.findById(request.getInterval().getId());

        if (interval.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        question.setInterval(interval.get());
        question.setAns1("test1");
        question.setAns2("test2");
        question.setAns3("test3");
        question.setAns4(interval.get().getIntervalName());

        return ResponseEntity.ok(intervalQuestionsRepository.save(question));
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<Iterable<IntervalQuestions>> getAllQuestions() {
        return ResponseEntity.ok(intervalQuestionsRepository.findAll());
    }

    @GetMapping("/getRandomQuestion")
    public ResponseEntity<IntervalQuestions> getRandomQuestion() {
        List<IntervalQuestions> questions = intervalQuestionsRepository.findAll();
        int randomIndex = (int) (Math.random() * questions.size());
        return ResponseEntity.ok(questions.get(randomIndex));
    }

}
