package com.api.advisor.infra.dtos;

import java.time.LocalDateTime;

public record CategoryResponseDTO(
  String id,
  String name,
  String description,
  LocalDateTime createdAt,
  LocalDateTime updatedAt
) {}
