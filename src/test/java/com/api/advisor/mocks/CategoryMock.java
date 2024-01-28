package com.api.advisor.mocks;

import com.api.advisor.domain.Category;
import com.api.advisor.infra.dtos.CategoryPayloadDTO;
import com.api.advisor.infra.dtos.CategoryResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class CategoryMock {

	private static final UUID id = UUID.randomUUID();
	private static final LocalDateTime createdAt = LocalDateTime.now();
	private static final LocalDateTime updatedAt = LocalDateTime.now();

	public static Category createEntity() {
		var category = Category.builder()
		  .name("any-name")
		  .description("any-description")
		  .build();
		category.setId(id);
		category.setCreatedAt(createdAt);
		category.setUpdatedAt(updatedAt);
		return category;
	}

	public static CategoryPayloadDTO createCategoryPayload() {
		return new CategoryPayloadDTO("any-name", "any-description");
	}

	public static CategoryResponseDTO createCategoryResponse() {
		return new CategoryResponseDTO(id.toString(), "any-name", "any-description", createdAt, updatedAt);
	}

	public static List<Category> createCategoryList() {
		return List.of(createEntity());
	}

	public static List<CategoryResponseDTO> createCategoryResponseList() {
		return List.of(createCategoryResponse());
	}
}
