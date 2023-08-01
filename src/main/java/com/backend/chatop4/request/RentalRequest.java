package com.backend.chatop4.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequest {
  private String name;
  private Integer price;
  private Integer surface;
  private String description;
  private Byte[] picture;
}
