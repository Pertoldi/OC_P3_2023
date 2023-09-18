package com.backend.chatop4.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop4.model.Message;
import com.backend.chatop4.service.MessageService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Messages")
public class MessageController {

  private final MessageService messageService;

  @PostMapping()
  public ResponseEntity<Map<String, String>> create(@RequestBody Message message) {
    Message messageSaved = messageService.create(message);
    if (messageSaved != null) {
      return ResponseEntity.ok(Map.of("message", "Message send with success"));
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

  }

}
