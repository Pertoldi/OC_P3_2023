package com.backend.chatop4.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Integer id;

  private String name;

  private String email;

  private Date created_at;

  private Date updated_at;

}
