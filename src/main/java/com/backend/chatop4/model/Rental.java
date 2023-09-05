package com.backend.chatop4.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
  private Double price;
  private Double surface;
  private String picture;
  private String description;
  private Integer owner_id;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;

  @PrePersist
  public void prePersist() {
    created_at = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    updated_at = LocalDateTime.now();
  }
}
