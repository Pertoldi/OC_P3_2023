package com.backend.chatop4.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.chatop4.controller.AuthenticationResponse;
import com.backend.chatop4.controller.LoginRequest;
import com.backend.chatop4.controller.RegisterRequest;
import com.backend.chatop4.model.Role;
import com.backend.chatop4.model.User;
import com.backend.chatop4.repository.UserRepository;

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
        .role(Role.USER)
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
    // if we pass here that mean the authentification work
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

    var jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse
        .builder()
        .token(jwtToken)
        .build();
  }
}
