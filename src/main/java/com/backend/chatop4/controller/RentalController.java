package com.backend.chatop4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop4.model.Rental;
import com.backend.chatop4.request.RentalRequest;
import com.backend.chatop4.service.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentals/")
@RequiredArgsConstructor
public class RentalController {

  public final RentalService rentalService;

  @GetMapping
  public ResponseEntity<Object> getAll() {
    return ResponseEntity.ok(rentalService.getAll());
  }

  @GetMapping("{id}")
  public ResponseEntity<Rental> getOne(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(rentalService.getOne(id));
  }

  // @PostMapping
  // public ResponseEntity<Object> create(@RequestBody RentalRequest request) {
  // System.out.println("request: " + request);
  // return ResponseEntity.ok(
  // rentalService.create(
  // new Rental(
  // request.getName(),
  // request.getPrice(),
  // request.getSurface(),
  // request.getDescription())));

  // }
}
