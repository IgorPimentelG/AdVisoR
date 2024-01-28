package com.api.advisor.infra.mappers;

import com.api.advisor.domain.Address;
import com.api.advisor.infra.dtos.AddressPayloadDTO;
import com.api.advisor.infra.dtos.AddressResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	Address toEntity(AddressPayloadDTO source);
	AddressResponseDTO toResponse(Address source);
	List<AddressResponseDTO> toResponse(List<Address> source);

	@Mapping(target = "id", ignore = true)
	void update(AddressPayloadDTO source, @MappingTarget Address target);
}
