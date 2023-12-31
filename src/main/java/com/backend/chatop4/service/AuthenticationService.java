package com.backend.chatop4.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.chatop4.model.User;
import com.backend.chatop4.repository.UserRepository;
import com.backend.chatop4.request.LoginRequest;
import com.backend.chatop4.request.RegisterRequest;
import com.backend.chatop4.response.AuthenticationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User
        .builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .build();

    userRepository.save(user);

    var jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse
        .builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse login(LoginRequest request) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

    var jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse
        .builder()
        .token(jwtToken)
        .build();
  }
}
