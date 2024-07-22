package com.kacper.musicapp.intervalQuestion;

import com.kacper.musicapp.exception.ResourceNotFoundException;
import com.kacper.musicapp.interval.Interval;
import com.kacper.musicapp.interval.IntervalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IntervalQuestionService
{
    private final IntervalQuestionRepository intervalQuestionRepository;
    private final IntervalQuestionMapper intervalQuestionMapper;
    private final IntervalRepository intervalRepository;

    public IntervalQuestionService(
            IntervalQuestionRepository intervalQuestionRepository,
            IntervalQuestionMapper intervalQuestionMapper,
            IntervalRepository intervalRepository) {
        this.intervalQuestionRepository = intervalQuestionRepository;
        this.intervalQuestionMapper = intervalQuestionMapper;
        this.intervalRepository = intervalRepository;
    }

    public List<IntervalQuestionResponseDTO> findAllIntervalQuestions() {
        return intervalQuestionRepository.findAll()
                .stream()
                .map(intervalQuestionMapper)
                .collect(Collectors.toList());
    }

    public ResponseEntity<IntervalQuestion> addIntervalQuestion(IntervalQuestionRequestDTO intervalQuestionRequestDTO) {
        Integer intervalId = intervalQuestionRequestDTO.intervalId();
        Interval interval = intervalRepository.findById(intervalId)
                .orElseThrow(() -> new ResourceNotFoundException("Interval not found"));

        IntervalQuestion intervalQuestion = IntervalQuestion.builder()
                .interval(interval)
                .difficulty(intervalQuestionRequestDTO.difficulty())
                .option1(interval.getIntervalName())
                .option2(intervalQuestionRequestDTO.option2())
                .option3(intervalQuestionRequestDTO.option3())
                .option4(intervalQuestionRequestDTO.option4())
                .build();

        return new ResponseEntity<>(intervalQuestionRepository.save(intervalQuestion), HttpStatus.CREATED);
    }

    public IntervalQuestion getIntervalQuestionById(Integer id) {
        return intervalQuestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interval question not found"));
    }

    public Boolean checkAnswer(AnswerRequest answerRequest) {
        Integer questionId = answerRequest.questionId();
        Optional<IntervalQuestion> intervalQuestion = intervalQuestionRepository.findById(questionId);

        if (intervalQuestion.isEmpty()) {
            throw new ResourceNotFoundException("Interval question not found");
        }

        String correctAnswer = intervalQuestion.get().getInterval().getIntervalName();

        return correctAnswer.equals(answerRequest.answer());
    }
}
