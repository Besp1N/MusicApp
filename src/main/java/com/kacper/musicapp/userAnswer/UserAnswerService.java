package com.kacper.musicapp.userAnswer;

import org.springframework.stereotype.Service;

@Service
public class UserAnswerService
{
    private final UserAnswerRepository userAnswerRepository;

    public UserAnswerService(UserAnswerRepository userAnswerRepository) {
        this.userAnswerRepository = userAnswerRepository;
    }

    public UserAnswer saveAnswer(UserAnswer userAnswer) {
        return null;
    }
}
