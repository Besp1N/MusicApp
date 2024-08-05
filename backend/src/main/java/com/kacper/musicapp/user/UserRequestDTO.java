package com.kacper.musicapp.user;

public record UserRequestDTO(
        String email,
        String password,
        String role,
        String name,
        String lastName,
        String gender,
        Integer age
) {
}
