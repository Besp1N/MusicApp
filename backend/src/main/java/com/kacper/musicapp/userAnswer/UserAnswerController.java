package com.kacper.musicapp.userAnswer;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userAnswer")
public class UserAnswerController
{
    private final UserAnswerService userAnswerService;

    public UserAnswerController(
            UserAnswerService userAnswerService
    ) {
        this.userAnswerService = userAnswerService;

    }

    @PostMapping("/{questionId}")
    public UserAnswerResponseDTO checkAndSaveAnswer(
            @PathVariable Integer questionId,
            @RequestBody UserAnswerRequestDTO userAnswerRequestDTO
    ) {
        return userAnswerService.checkAndSaveAnswer(userAnswerRequestDTO, questionId);
    }
}
