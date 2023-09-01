package com.backend.chatop4.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.chatop4.model.Rental;
import com.backend.chatop4.model.User;
import com.backend.chatop4.repository.UserRepository;
import com.backend.chatop4.service.JwtService;
import com.backend.chatop4.service.RentalService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

  public final RentalService rentalService;
  private final JwtService jwtService;
  private final UserRepository userRepository;

  @GetMapping
  public ResponseEntity<Object> getAll() {
    return ResponseEntity.ok(rentalService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Rental> getOne(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(rentalService.getOne(id));
  }

  @PostMapping("")
  public ResponseEntity<Object> create(
      HttpServletRequest request,
      @RequestParam("name") String name,
      @RequestParam("surface") Double surface,
      @RequestParam("price") Double price,
      @RequestParam("picture") MultipartFile picture,
      @RequestParam("description") String description) throws IllegalStateException, IOException

  {
    String authorizationHeader = request.getHeader("Authorization");
    System.out.println("authorizationHeader: " + authorizationHeader);
    String token = authorizationHeader.substring(7);
    System.out.println("token: " + token);

    String email = jwtService.getUsernameFromToken(token);
    User userRepos = userRepository.findByEmail(email).orElse(null);
    Integer userId = userRepos.getId();
    String pictureUrl = rentalService.uploadPicture(picture);
    System.out.println("pictureUrl: " + pictureUrl);
    if (pictureUrl == null) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    Rental rental = new Rental();
    rental.setName(name);
    rental.setSurface(surface);
    rental.setPrice(price);
    rental.setDescription(description);
    rental.setPicture(pictureUrl);
    rentalService.create(rental, userId);
    return ResponseEntity.ok(Map.of("message", "Rental created !"));
  }
}
