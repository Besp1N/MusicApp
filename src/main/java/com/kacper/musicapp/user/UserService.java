package com.kacper.musicapp.user;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;

    public UserService(
            UserRepository userRepository,
            UserResponseMapper userResponseMapper
    )
    {
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        User user = User.builder()
                .email(userRequestDTO.email())
                .password(userRequestDTO.password())
                .role(userRequestDTO.role())
                .name(userRequestDTO.name())
                .lastName(userRequestDTO.lastName())
                .gender(userRequestDTO.gender())
                .age(userRequestDTO.age())
                .activationCode(generateActivationCode())
                .build();

        return userResponseMapper.apply(userRepository.save(user));
    }

    private Integer generateActivationCode() {
        SecureRandom random = new SecureRandom();
        return 1000 + random.nextInt(9000);

    }
}
