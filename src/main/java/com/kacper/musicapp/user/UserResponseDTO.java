package com.kacper.musicapp.user;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        String email,
        String role,
        String name,
        String lastName,
        String gender,
        Integer age
) {
}
