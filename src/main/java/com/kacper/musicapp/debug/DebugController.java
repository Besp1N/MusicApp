package com.kacper.musicapp.debug;

import com.kacper.musicapp.intervalQuestion.IntervalQuestion;
import com.kacper.musicapp.interval.Interval;
import com.kacper.musicapp.intervalQuestion.IntervalQuestionRepository;
import com.kacper.musicapp.interval.IntervalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/debug")
public class DebugController
{
    private final IntervalRepository intervalRepository;
    private final IntervalQuestionRepository intervalQuestionRepository;

    public DebugController(
            IntervalRepository intervalRepository,
            IntervalQuestionRepository intervalQuestionRepository1
    ) {
        this.intervalRepository = intervalRepository;
        this.intervalQuestionRepository = intervalQuestionRepository1;
    }


    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @PostMapping("/addInterval")
    public ResponseEntity<Interval> addInterval(@RequestBody Interval request) {
        Interval interval = new Interval();
        interval.setIntervalName(request.getIntervalName());
        interval.setFirstNote(request.getFirstNote());
        interval.setSecondNote(request.getSecondNote());

        return ResponseEntity.ok(intervalRepository.save(interval));
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<IntervalQuestion> addQuestion(@RequestBody IntervalQuestion request) {
        IntervalQuestion question = new IntervalQuestion();
        Optional<Interval> interval = intervalRepository.findById(request.getInterval().getId());

        if (interval.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        question.setInterval(interval.get());
        question.setAns1("test1");
        question.setAns2("test2");
        question.setAns3("test3");
        question.setAns4(interval.get().getIntervalName());

        return ResponseEntity.ok(intervalQuestionRepository.save(question));
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<Iterable<IntervalQuestion>> getAllQuestions() {
        return ResponseEntity.ok(intervalQuestionRepository.findAll());
    }

    @GetMapping("/getRandomQuestion")
    public ResponseEntity<IntervalQuestion> getRandomQuestion() {
        List<IntervalQuestion> questions = intervalQuestionRepository.findAll();
        int randomIndex = (int) (Math.random() * questions.size());
        return ResponseEntity.ok(questions.get(randomIndex));
    }

}
