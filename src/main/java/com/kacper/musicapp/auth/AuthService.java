package com.kacper.musicapp.auth;

import com.kacper.musicapp.jwt.JWTService;
import com.kacper.musicapp.user.User;
import com.kacper.musicapp.user.UserRepository;
import com.kacper.musicapp.utils.Debug;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AuthService
{
    private final UserRepository userRepository;
    private final AuthResponseMapper authResponseMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthService(UserRepository userRepository, AuthResponseMapper authResponseMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.authResponseMapper = authResponseMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponseDTO register(AuthRequestDTO authRequestDTO) {
        User user = User.builder()
                .email(authRequestDTO.email())
                .password(passwordEncoder.encode(authRequestDTO.password()))
                .role(authRequestDTO.role())
                .name(authRequestDTO.name())
                .lastName(authRequestDTO.lastName())
                .gender(authRequestDTO.gender())
                .age(authRequestDTO.age())
                .activationCode(generateActivationCode())
                .build();

        User savedUser = userRepository.save(user);
        return authResponseMapper.apply(savedUser);
    }

    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.email(), authRequestDTO.password()));
        User user = userRepository.findByEmail(authRequestDTO.email()).orElseThrow();
        String token = jwtService.generateToken(user);

        return AuthResponseDTO.builder()
                .email(user.getEmail())
                .token(token)
                .build();
    }

    private Integer generateActivationCode() {
        SecureRandom random = new SecureRandom();
        return 1000 + random.nextInt(9000);
    }
}
