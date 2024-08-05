package com.kacper.musicapp.auth;

import com.kacper.musicapp.exception.InvalidCredentialsException;
import com.kacper.musicapp.exception.ResourceNotFoundException;
import com.kacper.musicapp.exception.UserAlreadyExistException;
import com.kacper.musicapp.exception.UserNotEnabledException;
import com.kacper.musicapp.jwt.JWTService;
import com.kacper.musicapp.mail.MailService;
import com.kacper.musicapp.user.User;
import com.kacper.musicapp.user.UserRepository;
import com.kacper.musicapp.utils.Debug;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.security.SecureRandom;

@Service
public class AuthService
{
    private final UserRepository userRepository;
    private final AuthResponseMapper authResponseMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final MailService mailService;

    public AuthService(
            UserRepository userRepository,
            AuthResponseMapper authResponseMapper,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JWTService jwtService,
            MailService mailService
    ) {
        this.userRepository = userRepository;
        this.authResponseMapper = authResponseMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.mailService = mailService;
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

        try {
            User savedUser = userRepository.save(user);
//            mailService.sendActivationCode(savedUser.getEmail(), savedUser.getActivationCode());
            return authResponseMapper.apply(savedUser);
        } catch (Exception e) {
            throw new UserAlreadyExistException("User already exist");
        }
    }

    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        User user = userRepository.findByEmail(authRequestDTO.email()).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));

        if (!user.isEnabled()) {
            throw new UserNotEnabledException("Verify your email");
        }

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.email(), authRequestDTO.password()));
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return AuthResponseDTO.builder()
                .email(user.getEmail())
                .token(token)
                .build();
    }

    public String activate(ActivationRequest activationRequest) {
        User user = userRepository.findByEmail(activationRequest.email()).orElseThrow();

        if (user.getActivationCode().equals(activationRequest.activationCode())) {
            user.setEnabled(true);
            userRepository.save(user);
            return "User activated";
        }

        return "Invalid activation code";
    }

    private Integer generateActivationCode() {
        SecureRandom random = new SecureRandom();
        return 1000 + random.nextInt(9000);
    }


}
