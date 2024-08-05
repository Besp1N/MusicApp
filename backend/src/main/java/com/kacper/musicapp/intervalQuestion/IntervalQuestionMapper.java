package com.kacper.musicapp.intervalQuestion;

import com.kacper.musicapp.interval.IntervalMapper;
import com.kacper.musicapp.userAnswer.UserAnswer;
import com.kacper.musicapp.utils.UserUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class IntervalQuestionMapper implements Function<IntervalQuestion, IntervalQuestionResponseDTO>
{
    private final IntervalMapper intervalMapper;

    public IntervalQuestionMapper(IntervalMapper intervalMapper) {
        this.intervalMapper = intervalMapper;
    }

    @Override
    public IntervalQuestionResponseDTO apply(IntervalQuestion intervalQuestion) {
        UserDetails userDetails = UserUtils.getUser();
        Optional<UserAnswer> userAnswer = intervalQuestion.getUserAnswers().stream()
                .filter(answer -> answer.getUser().getUsername().equals(userDetails.getUsername()))
                .findFirst();

        return new IntervalQuestionResponseDTO(
                intervalQuestion.getId(),
                intervalMapper.apply(intervalQuestion.getInterval()),
                userAnswer,
                intervalQuestion.getDifficulty(),
                intervalQuestion.getOption1(),
                intervalQuestion.getOption2(),
                intervalQuestion.getOption3(),
                intervalQuestion.getOption4()
        );
    }
}
