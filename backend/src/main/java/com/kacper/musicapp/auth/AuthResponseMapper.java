package com.kacper.musicapp.auth;

import com.kacper.musicapp.user.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AuthResponseMapper implements Function<User, AuthResponseDTO>
{

    @Override
    public AuthResponseDTO apply(User user) {
        return AuthResponseDTO.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .gender(user.getGender())
                .age(user.getAge())
                .build();
    }
}
