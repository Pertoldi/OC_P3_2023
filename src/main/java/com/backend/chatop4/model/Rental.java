package com.backend.chatop4.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rentals")
@Getter
@Setter
@NoArgsConstructor
public class Rental {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private Integer price;
  private Integer surface;
  private String picture;
  private String description;
  private Integer owner_id;
  private Date created_at;
  private Date updated_at;
}
