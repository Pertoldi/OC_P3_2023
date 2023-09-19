package com.backend.chatop4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.chatop4.service.ImageService;

import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pictures")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Images")
public class ImageController {

  private final ImageService imageService;

  @Operation(summary = "Get picture by name")
  @GetMapping("/{name}")
  public ResponseEntity<Object> getPictureByName(@PathVariable String name) throws IOException, java.io.IOException {
    return imageService.getPictureByName(name);
  }
}
