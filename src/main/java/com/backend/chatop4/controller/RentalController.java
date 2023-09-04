package com.backend.chatop4.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.chatop4.dto.RentalUpdateDto;
import com.backend.chatop4.model.Rental;
import com.backend.chatop4.service.RentalService;
import com.backend.chatop4.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

  public final RentalService rentalService;
  private final UserService userService;

  @GetMapping
  public ResponseEntity<Object> getAll() {
    return ResponseEntity.ok(rentalService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Rental> getOne(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(rentalService.getOne(id));
  }

  @PostMapping
  public ResponseEntity<Map<String, String>> create(
      HttpServletRequest request,
      @RequestParam("name") String name,
      @RequestParam("surface") Double surface,
      @RequestParam("price") Double price,
      @RequestParam("picture") MultipartFile picture,
      @RequestParam("description") String description) throws IllegalStateException, IOException

  {
    String pictureUrl = rentalService.uploadPicture(picture);
    if (pictureUrl == null) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    String authorizationHeader = request.getHeader("Authorization");
    String token = authorizationHeader.substring(7);
    Integer userId = userService.getUserIdFromToken(token);

    Rental rental = new Rental();
    rental.setName(name);
    rental.setSurface(surface);
    rental.setPrice(price);
    rental.setDescription(description);
    rental.setPicture(pictureUrl);
    rentalService.create(rental, userId);
    return ResponseEntity.ok(Map.of("message", "Rental created !"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, String>> update(HttpServletRequest request,
      @PathVariable Integer id,
      @ModelAttribute @Validated RentalUpdateDto rentalUpdateDto) {
    String authorizationHeader = request.getHeader("Authorization");
    String token = authorizationHeader.substring(7);
    Integer userId = userService.getUserIdFromToken(token);
    if (rentalService.update(id, rentalUpdateDto, userId)) {
      return ResponseEntity.ok(Map.of("message", "Rental updated !"));
    } else
      return ResponseEntity.notFound().build();
  }
}
