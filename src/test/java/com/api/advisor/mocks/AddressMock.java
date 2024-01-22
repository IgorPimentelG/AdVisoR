package com.api.advisor.mocks;

import com.api.advisor.domain.Address;
import com.api.advisor.infra.dtos.AddressPayloadDTO;
import com.api.advisor.infra.dtos.AddressResponseDTO;

import java.util.List;
import java.util.UUID;

public abstract class AddressMock {

	private static final UUID id = UUID.randomUUID();

	public static Address createEntity() {
		var address = Address.builder()
		  .city("any-city")
		  .street("any-street")
		  .state("any-state")
		  .neighborhood("any-neighborhood")
		  .number("any-number")
		  .zipcode("any-zipcode")
		  .build();
		address.setId(id);
		return address;
	}

	public static AddressPayloadDTO createAddressPayload() {
		return new AddressPayloadDTO(
		  "any-city",
		  "any-street",
		  "any-state",
		  "any-neighborhood",
		  "any-number",
		  "any-zipcode"
		);
	}

	public static AddressResponseDTO createAddressResponse() {
		return new AddressResponseDTO(
		  id.toString(),
		  "any-city",
		  "any-state",
		  "any-street",
		  "any-neighborhood",
		  "any-number",
		  "any-zipcode"
		);
	}

	public static List<Address> createAddressList() {
		return List.of(createEntity());
	}

	public static List<AddressResponseDTO> createAddressResponseList() {
		return List.of(createAddressResponse());
	}
}
