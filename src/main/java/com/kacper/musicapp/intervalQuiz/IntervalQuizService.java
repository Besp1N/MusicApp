package com.kacper.musicapp.intervalQuiz;

import com.kacper.musicapp.exception.ResourceNotFoundException;
import com.kacper.musicapp.intervalQuestion.IntervalQuestion;
import com.kacper.musicapp.intervalQuestion.IntervalQuestionRequestDTO;
import com.kacper.musicapp.intervalQuestion.IntervalQuestionService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class IntervalQuizService
{
    private final IntervalQuizRepository intervalQuizRepository;
    private final IntervalQuestionService intervalQuestionService;
    private final IntervalQuizShowMapper intervalQuizShowMapper;

    public IntervalQuizService(
            IntervalQuizRepository intervalQuizRepository,
            IntervalQuestionService intervalQuestionService,
            IntervalQuizShowMapper intervalQuizShowMapper
    ) {
        this.intervalQuizRepository = intervalQuizRepository;
        this.intervalQuestionService = intervalQuestionService;
        this.intervalQuizShowMapper = intervalQuizShowMapper;
    }

    public IntervalQuiz addEmptyIntervalQuiz(IntervalQuizRequestDAO intervalQuizRequestDAO) {
        IntervalQuiz intervalQuiz = IntervalQuiz.builder()
                .name(intervalQuizRequestDAO.name())
                .difficulty(intervalQuizRequestDAO.difficulty())
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
}
