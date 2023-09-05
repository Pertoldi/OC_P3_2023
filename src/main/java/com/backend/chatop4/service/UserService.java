package com.backend.chatop4.service;

import org.springframework.stereotype.Service;

import com.backend.chatop4.model.User;
import com.backend.chatop4.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {

  private final JwtService jwtService;
  private final UserRepository userRepository;

  public Integer getUserIdFromToken(String token) {
    String email = jwtService.getUsernameFromToken(token);
    User userRepos = userRepository.findByEmail(email).orElse(null);
    Integer userId = userRepos.getId();
    return userId;
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
  }
}
