package com.backend.chatop4.service;

import org.springframework.stereotype.Service;

import com.backend.chatop4.model.Message;
import com.backend.chatop4.repository.MessageRepository;

import lombok.Data;

@Data
@Service
public class MessageService {

  private final MessageRepository messageRepository;

  public Message create(Message message) {
    return messageRepository.save(message);
  }
}
