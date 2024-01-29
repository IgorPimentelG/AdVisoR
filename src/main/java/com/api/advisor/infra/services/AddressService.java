package com.api.advisor.infra.services;

import com.api.advisor.domain.Address;
import com.api.advisor.infra.dtos.*;
import com.api.advisor.infra.errors.NotFoundException;
import com.api.advisor.infra.mappers.AddressMapper;
import com.api.advisor.infra.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {

	private final AddressRepository repository;
	private final AddressMapper mapper;

	public AddressResponseDTO create(AddressPayloadDTO payload) {
		var address = mapper.toEntity(payload);
		repository.save(address);

		return mapper.toResponse(address);
	}

	public AddressResponseDTO update(String id, AddressPayloadDTO payload) {
		var address = find(id);
		mapper.update(payload, address);
		repository.save(address);

		return mapper.toResponse(address);
	}

	public AddressResponseDTO findById(String id) {
		return mapper.toResponse(find(id));
	}

	public List<AddressResponseDTO> findAll() {
		var address = repository.findAll();
		return mapper.toResponse(address);
	}

	public void delete(String id) {
		var address = find(id);
		repository.delete(address);
	}

	private Address find(String id) {
		return repository.findById(UUID.fromString(id))
		  .orElseThrow(() -> new NotFoundException("Address not found."));
	}
}
