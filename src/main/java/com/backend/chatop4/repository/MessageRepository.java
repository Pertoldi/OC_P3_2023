package com.backend.chatop4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.chatop4.model.Message;

public interface MessageRepository
    extends JpaRepository<Message, Integer> {

}
