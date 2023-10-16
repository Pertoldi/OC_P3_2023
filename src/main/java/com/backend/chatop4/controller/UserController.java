package com.backend.chatop4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop4.dto.UserDto;
import com.backend.chatop4.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "User")
public class UserController {

  private final UserService userService;

  @Operation(summary = "Get user infos by id")
  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

}
