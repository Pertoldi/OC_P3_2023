package com.backend.chatop4.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalUpdateDto {
  private String name;
  private Double surface;
  private Double price;
  private String description;
  private Date updated_at;
}