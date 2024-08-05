package com.kacper.musicapp.user;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserResponseMapper implements Function<User, UserResponseDTO>
{
    @Override
    public UserResponseDTO apply(User user) {
        return UserResponseDTO.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .age(user.getAge())
                .build();
    }
}
