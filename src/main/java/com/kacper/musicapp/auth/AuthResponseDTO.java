package com.kacper.musicapp.auth;

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
