package com.kacper.musicapp.intervalQuestion;

import com.kacper.musicapp.utils.Debug;
import com.kacper.musicapp.utils.UserUtils;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/intervalQuestion")
public class IntervalQuestionController
{
    private final IntervalQuestionService intervalQuestionService;

    public IntervalQuestionController(IntervalQuestionService intervalQuestionService) {
        this.intervalQuestionService = intervalQuestionService;
    }

    @GetMapping("/")
    public List<IntervalQuestionResponseDTO> getAllIntervalQuestion() {
        return intervalQuestionService.findAllIntervalQuestions();
    }

    @GetMapping("test")
    public String test() {
        Debug.dd(UserUtils.getUser());
        return "test";
    }

    @PostMapping("/checkAnswer")
    public Boolean checkAnswer(@RequestBody AnswerRequest answerRequest) {
        return intervalQuestionService.checkAnswer(answerRequest);

    }
}
