package com.kacper.musicapp.userAnswer;

import com.kacper.musicapp.intervalQuestion.IntervalQuestion;
import com.kacper.musicapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer>
{
    Optional<UserAnswer> findByUserAndQuestion(User user, IntervalQuestion question);
}
