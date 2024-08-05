package com.kacper.musicapp.auth;

public record AuthRequestDTO(
    String email,
    String password,
    String role,
    String name,
    String lastName,
    String gender,
    Integer age
) {
}
