package com.backend.chatop4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.backend.chatop4.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UserRepository userRepository;

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> { // This is a lambda expression that override loadUserByUsername
      return userRepository.findByEmail(username)
          .orElseThrow(() -> new UsernameNotFoundException("user not found !"));
    };
  }

}
