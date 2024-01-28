package com.api.advisor.services;

import com.api.advisor.infra.dtos.*;
import com.api.advisor.infra.dtos.CategoryResponseDTO;
import com.api.advisor.infra.errors.NotFoundException;
import com.api.advisor.infra.mappers.CategoryMapper;
import com.api.advisor.infra.repositories.CategoryRepository;
import com.api.advisor.infra.services.CategoryService;
import com.api.advisor.mocks.CategoryMock;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceTests {

	@InjectMocks
	CategoryService service;

	@Mock
	CategoryRepository repository;

	@Mock
	CategoryMapper mapper;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Should create a category")
	void testCreateCategory() {
		var categoryEntity = CategoryMock.createEntity();
		var categoryPayload = CategoryMock.createCategoryPayload();
		var categoryResponse = CategoryMock.createCategoryResponse();

		when(mapper.toEntity(categoryPayload)).thenReturn(categoryEntity);
		when(mapper.toResponse(categoryEntity)).thenReturn(categoryResponse);
		when(repository.save(any())).thenReturn(categoryEntity);

		var result = service.create(categoryPayload);

		verify(repository, times(1)).save(any());
		assertNotNull(result);
		assertEquals(result.id(), categoryEntity.getId().toString());
		assertEquals(result.name(), categoryEntity.getName());
		assertEquals(result.description(), categoryEntity.getDescription());
		assertEquals(result.createdAt(), categoryEntity.getCreatedAt());
		assertEquals(result.updatedAt(), categoryEntity.getUpdatedAt());
	}

	@Test
	@DisplayName("Should update a category")
	void testUpdateCategory() {
		var category = CategoryMock.createEntity();
		var categoryPayload = new CategoryPayloadDTO(
		  "updated-name",
		  "updated-description"
		);
		var categoryResponse = new CategoryResponseDTO(
		  category.getId().toString(),
		  "updated-name",
		  "updated-description",
		  category.getCreatedAt(),
		  category.getUpdatedAt()
		);

		when(repository.findById(any())).thenReturn(Optional.of(category));
		when(mapper.toResponse(category)).thenReturn(categoryResponse);

		var result = service.update(category.getId().toString(), categoryPayload);

		verify(repository, times(1)).save(any());
		assertNotNull(result);
		assertEquals(result.id(), categoryResponse.id());
		assertEquals(result.name(), categoryResponse.name());
		assertEquals(result.description(), categoryResponse.description());
		assertEquals(result.createdAt(), categoryResponse.createdAt());
		assertEquals(result.updatedAt(), categoryResponse.updatedAt());
	}

	@Test
	@DisplayName("Should throw NotFoundException when update a category does not exist")
	void testUpdateCategoryNotFound() {
		Exception exception = assertThrows(NotFoundException.class, () -> {
			service.update(UUID.randomUUID().toString(), CategoryMock.createCategoryPayload());
		});

		String expectedMessage = "Category not found.";
		String resultMessage = exception.getMessage();

		verify(repository, times(0)).save(any());
		verify(repository, times(1)).findById(any());
		assertEquals(expectedMessage, resultMessage);
	}

	@Test
	@DisplayName("Should find a category by id")
	void testFindByIdCategory() {
		var categoryEntity = CategoryMock.createEntity();
		var categoryResponse = CategoryMock.createCategoryResponse();

		when(mapper.toResponse(categoryEntity)).thenReturn(categoryResponse);
		when(repository.findById(any())).thenReturn(Optional.of(categoryEntity));

		var result = service.findById(UUID.randomUUID().toString());

		verify(repository, times(1)).findById(any());
		assertNotNull(result);
		assertEquals(result.id(), categoryEntity.getId().toString());
		assertEquals(result.name(), categoryEntity.getName());
		assertEquals(result.description(), categoryEntity.getDescription());
		assertEquals(result.createdAt(), categoryEntity.getCreatedAt());
		assertEquals(result.updatedAt(), categoryEntity.getUpdatedAt());
	}

	@Test
	@DisplayName("Should throw NotFoundException when find a category does not exist")
	void testFindByIdCategoryNotFound() {
		Exception exception = assertThrows(NotFoundException.class, () -> {
			service.findById(UUID.randomUUID().toString());
		});

		String expectedMessage = "Category not found.";
		String resultMessage = exception.getMessage();

		verify(repository, times(1)).findById(any());
		assertEquals(expectedMessage, resultMessage);
	}

	@Test
	@DisplayName("Should list all categories")
	void testFindAllCategory() {
		var categoryList = CategoryMock.createCategoryList();
		var categoryResponseList = CategoryMock.createCategoryResponseList();

		when(repository.findAll()).thenReturn(categoryList);
		when(mapper.toResponse(categoryList)).thenReturn(categoryResponseList);

		var result = service.findAll();

		verify(repository, times(1)).findAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals(categoryList.get(0).getId().toString(), result.get(0).id());
	}

	@Test
	@DisplayName("Should delete a category")
	void testDeleteCategory() {
		var category = CategoryMock.createEntity();

		when(repository.findById(any())).thenReturn(Optional.of(category));

		service.delete(UUID.randomUUID().toString());

		verify(repository, times(1)).findById(any());
		verify(repository, times(1)).delete(any());
	}

	@Test
	@DisplayName("Should throw NotFoundException when delete a category does not exist")
	void testDeleteCategoryNotFound() {
		Exception exception = assertThrows(NotFoundException.class, () -> {
			service.delete(UUID.randomUUID().toString());
		});

		String expectedMessage = "Category not found.";
		String resultMessage = exception.getMessage();

		verify(repository, times(1)).findById(any());
		assertEquals(expectedMessage, resultMessage);
	}
}
