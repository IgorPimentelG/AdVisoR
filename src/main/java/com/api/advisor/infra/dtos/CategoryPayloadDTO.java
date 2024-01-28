package com.api.advisor.infra.dtos;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record CategoryPayloadDTO(
  @NotEmpty(message = "Name is required.")
  @Length(max = 100, message = "Name must have a max of 100 characters.")
  String name,

  @NotEmpty(message = "Description is required.")
  @Length(max = 255, message = "Description must have a max of 255 characters.")
  String description
) {}
