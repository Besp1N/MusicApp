package com.kacper.musicapp.intervalQuiz;

import com.kacper.musicapp.exception.ResourceNotFoundException;
import com.kacper.musicapp.intervalQuestion.IntervalQuestion;
import com.kacper.musicapp.intervalQuestion.IntervalQuestionMapper;
import com.kacper.musicapp.intervalQuestion.IntervalQuestionService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class IntervalQuizService
{
    private final IntervalQuizRepository intervalQuizRepository;
    private final IntervalQuestionService intervalQuestionService;
    private final IntervalQuizShowMapper intervalQuizShowMapper;
    private final IntervalQuestionMapper intervalQuestionMapper;

    public IntervalQuizService(
            IntervalQuizRepository intervalQuizRepository,
            IntervalQuestionService intervalQuestionService,
            IntervalQuizShowMapper intervalQuizShowMapper,
            IntervalQuestionMapper intervalQuestionMapper
    ) {
        this.intervalQuizRepository = intervalQuizRepository;
        this.intervalQuestionService = intervalQuestionService;
        this.intervalQuizShowMapper = intervalQuizShowMapper;
        this.intervalQuestionMapper = intervalQuestionMapper;
    }

    public IntervalQuiz addEmptyIntervalQuiz(IntervalQuizRequestDTO intervalQuizRequestDTO) {
        IntervalQuiz intervalQuiz = IntervalQuiz.builder()
                .name(intervalQuizRequestDTO.name())
                .difficulty(intervalQuizRequestDTO.difficulty())
                .questions(new HashSet<>())
                .build();

        return intervalQuizRepository.save(intervalQuiz);
    }


    public IntervalQuiz addQuestions(
            Integer quizId,
            IntervalQuizAddQuestionsRequestDAO intervalQuizAddQuestionsRequestDAO
    ) {
        IntervalQuiz quiz = intervalQuizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

            intervalQuizAddQuestionsRequestDAO.questions().forEach(questionDTO -> {
                IntervalQuestion addedQuestion = intervalQuestionService.addIntervalQuestion(questionDTO, quizId);
                quiz.addQuestion(addedQuestion);
            });

            return intervalQuizRepository.save(quiz);
    }

    public List<IntervalQuizShowResponseDTO> getIntervalQuizzes() {
        return intervalQuizRepository.findAll()
                .stream()
                .map(intervalQuizShowMapper)
                .collect(Collectors.toList());
    }

    public IntervalQuizResponseDTO getIntervalQuizById(Integer quizId) {
        IntervalQuiz quiz = intervalQuizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        return IntervalQuizResponseDTO.builder()
                .name(quiz.getName())
                .difficulty(quiz.getDifficulty())
                .questions(quiz.getQuestions()
                        .stream()
                        .map(intervalQuestionMapper)
                        .collect(Collectors.toList()))
                .build();
    }
}
