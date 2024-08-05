package com.kacper.musicapp.userAnswer;

import com.kacper.musicapp.intervalQuestion.IntervalQuestion;
import com.kacper.musicapp.intervalQuestion.IntervalQuestionRepository;
import com.kacper.musicapp.user.User;
import com.kacper.musicapp.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAnswerService
{
    private final UserAnswerRepository userAnswerRepository;
    private final IntervalQuestionRepository intervalQuestionRepository;

    public UserAnswerService(
            UserAnswerRepository userAnswerRepository,
            IntervalQuestionRepository intervalQuestionRepository
    ) {
        this.userAnswerRepository = userAnswerRepository;
        this.intervalQuestionRepository = intervalQuestionRepository;
    }

    public UserAnswerResponseDTO checkAndSaveAnswer(
            UserAnswerRequestDTO userAnswerRequestDTO,
            Integer questionId
    ) {
        IntervalQuestion question = intervalQuestionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        User user = (User) UserUtils.getUser();

        Optional<UserAnswer> optionalUserAnswer = userAnswerRepository.findByUserAndQuestion(user, question);

        UserAnswer userAnswer;
        if (optionalUserAnswer.isPresent()) {
            userAnswer = optionalUserAnswer.get();
            userAnswer.setGivenAnswer(userAnswerRequestDTO.givenAnswer());
            userAnswer.setCorrect(userAnswerRequestDTO.givenAnswer().equals(question.getInterval().getIntervalName()));
        } else {
            userAnswer = UserAnswer.builder()
                    .user(user)
                    .question(question)
                    .givenAnswer(userAnswerRequestDTO.givenAnswer())
                    .isCorrect(userAnswerRequestDTO.givenAnswer().equals(question.getInterval().getIntervalName()))
                    .build();
        }

        userAnswerRepository.save(userAnswer);

        return UserAnswerResponseDTO.builder()
                .givenAnswer(userAnswer.getGivenAnswer())
                .isCorrect(userAnswer.isCorrect())
                .build();
    }
}
