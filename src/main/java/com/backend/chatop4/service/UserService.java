package com.backend.chatop4.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.backend.chatop4.dto.UserDto;
import com.backend.chatop4.model.User;
import com.backend.chatop4.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {

  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  public Integer getUserIdFromToken(String token) {
    String email = jwtService.getUsernameFromToken(token);
    User userRepos = userRepository.findByEmail(email).orElse(null);
    Integer userId = userRepos.getId();
    return userId;
  }

  public UserDto getUserByEmail(String email) {
    User user = userRepository.findByEmail(email).orElse(null);
    return modelMapper.map(user, UserDto.class);
  }

  public UserDto getUserById(Integer id) {
    User user = userRepository.findById(id).orElse(null);
    return modelMapper.map(user, UserDto.class);
  }
}
