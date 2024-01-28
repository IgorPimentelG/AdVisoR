package com.api.advisor.infra.services;

import com.api.advisor.domain.Category;
import com.api.advisor.infra.dtos.*;
import com.api.advisor.infra.errors.NotFoundException;
import com.api.advisor.infra.mappers.CategoryMapper;
import com.api.advisor.infra.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository repository;
	private final CategoryMapper mapper;

	public CategoryResponseDTO create(CategoryPayloadDTO payload) {
		var category = mapper.toEntity(payload);
		repository.save(category);
		return mapper.toResponse(category);
	}

	public CategoryResponseDTO update(String id, CategoryPayloadDTO payload) {
		var category = find(id);
		mapper.update(payload, category);
		repository.save(category);
		return mapper.toResponse(category);
	}

	public CategoryResponseDTO findById(String id) {
		var category = find(id);
		return mapper.toResponse(category);
	}

	public List<CategoryResponseDTO> findAll() {
		var categories = repository.findAll();
		return mapper.toResponse(categories);
	}

	public void delete(String id) {
		var category = find(id);
		repository.delete(category);
	}

	private Category find(String id) {
		return repository.findById(UUID.fromString(id))
		  .orElseThrow(() -> new NotFoundException("Category not found."));
	}
}
