package com.kacper.musicapp.intervalQuestion;

import com.kacper.musicapp.exception.ResourceNotFoundException;
import com.kacper.musicapp.interval.Interval;
import com.kacper.musicapp.interval.IntervalRepository;
import com.kacper.musicapp.interval.IntervalService;
import com.kacper.musicapp.intervalQuiz.IntervalQuiz;
import com.kacper.musicapp.intervalQuiz.IntervalQuizRepository;
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
    private final IntervalService intervalService;
    private final IntervalQuizRepository intervalQuizRepository;

    public IntervalQuestionService(
            IntervalQuestionRepository intervalQuestionRepository,
            IntervalQuestionMapper intervalQuestionMapper,
            IntervalRepository intervalRepository,
            IntervalService intervalService,
            IntervalQuizRepository intervalQuizRepository
    ) {
        this.intervalQuestionRepository = intervalQuestionRepository;
        this.intervalQuestionMapper = intervalQuestionMapper;
        this.intervalRepository = intervalRepository;
        this.intervalService = intervalService;
        this.intervalQuizRepository = intervalQuizRepository;
    }

    public List<IntervalQuestionResponseDTO> findAllIntervalQuestions() {
        return intervalQuestionRepository.findAll()
                .stream()
                .map(intervalQuestionMapper)
                .collect(Collectors.toList());
    }

    public ResponseEntity<IntervalQuestion> addIntervalQuestion(
            IntervalQuestionRequestDTO intervalQuestionRequestDTO,
            Optional<Integer> quizId
    ) {
        Interval interval = intervalService.addInterval(intervalQuestionRequestDTO.interval()).getBody();

        IntervalQuiz quiz = null;
        if (quizId.isPresent()) {
            quiz = intervalQuizRepository.findById(quizId.get())
                    .orElseThrow(() -> new ResourceNotFoundException("Interval quiz not found with id: " + quizId.get()));
        }

        IntervalQuestion intervalQuestion = IntervalQuestion.builder()
                .interval(interval)
                .quiz(quiz)
                .difficulty(intervalQuestionRequestDTO.difficulty())
                .option1(intervalQuestionRequestDTO.option1())
                .option2(intervalQuestionRequestDTO.option2())
                .option3(intervalQuestionRequestDTO.option3())
                .option4(intervalQuestionRequestDTO.option4())
                .build();

        IntervalQuestion savedQuestion = intervalQuestionRepository.save(intervalQuestion);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
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
