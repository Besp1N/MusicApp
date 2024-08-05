package com.kacper.musicapp.auth;

public record ActivationRequest(
        String email,
        Integer activationCode
) {
}
