package com.backend.chatop4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop4.model.User;
import com.backend.chatop4.request.LoginRequest;
import com.backend.chatop4.request.RegisterRequest;
import com.backend.chatop4.response.AuthenticationResponse;
import com.backend.chatop4.service.AuthenticationService;
import com.backend.chatop4.service.JwtService;
import com.backend.chatop4.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
@RequiredArgsConstructor
public class AuthenticationController {

  public final AuthenticationService authenticationService;
  private final JwtService jwtService;
  private final UserService userService;

  @Operation(summary = "auth register")
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authenticationService.register(request));
  }

  @Operation(summary = "auth login")
  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authenticationService.login(request));
  }

  @Operation(security = @SecurityRequirement(name = "BearerAuth"), summary = "Get current user's information")
  @GetMapping("/me")
  public ResponseEntity<User> getUser(HttpServletRequest request) {

    final String authHeader = request.getHeader("Authorization");

    String jwt = authHeader.substring(7);
    String userEmail = jwtService.extractUserEmail(jwt);
    return ResponseEntity.ok(userService.getUserByEmail(userEmail));

  }

}
