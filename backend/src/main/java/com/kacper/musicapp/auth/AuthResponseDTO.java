package com.kacper.musicapp.auth;

import lombok.Builder;

@Builder
public record AuthResponseDTO(
    String token,
    String email,
    String name,
    String lastName,
    String role,
    String gender,
    Integer age
) {
}
