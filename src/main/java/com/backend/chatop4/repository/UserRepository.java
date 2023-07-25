package com.backend.chatop4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.chatop4.model.User;

public interface UserRepository extends JpaRepository<User, Integer> { // JpaRepository <class model , ID> to use
                                                                       // save, findOneById etc ... (ORM)

  Optional<User> findByEmail(String email); // Because email is unique we can named it like we want
}
