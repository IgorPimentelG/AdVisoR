package com.api.advisor.infra.mappers;

import com.api.advisor.domain.Category;
import com.api.advisor.infra.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	Category toEntity(CategoryPayloadDTO source);
	CategoryResponseDTO toResponse(Category source);
	List<CategoryResponseDTO> toResponse(List<Category> source);

	@Mapping(target = "id", ignore = true)
	void update(CategoryPayloadDTO source, Category target);

}
