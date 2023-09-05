package com.backend.chatop4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop4.request.LoginRequest;
import com.backend.chatop4.request.RegisterRequest;
import com.backend.chatop4.response.AuthenticationResponse;
import com.backend.chatop4.service.AuthenticationService;
import com.backend.chatop4.service.JwtService;
import com.backend.chatop4.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  public final AuthenticationService authenticationService;
  private final JwtService jwtService;
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authenticationService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authenticationService.login(request));
  }

  // @GetMapping("/me")
  // public ResponseEntity<User> getUser(HttpServletRequest request) {

  // final String authHeader = request.getHeader("Authorization");

  // try {
  // if (authHeader == null || !authHeader.startsWith("Bearer ")) {
  // throw new Exception("Exception message");
  // }
  // String jwt = authHeader.substring(7);
  // String userEmail = jwtService.extractUserEmail(jwt);
  // return ResponseEntity.ok(userService.getUserByEmail(userEmail));
  // } catch (Exception e) {
  // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  // }

  // }

}
