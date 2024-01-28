package com.api.advisor.services;

import com.api.advisor.infra.dtos.*;
import com.api.advisor.infra.errors.NotFoundException;
import com.api.advisor.infra.mappers.AddressMapper;
import com.api.advisor.infra.repositories.AddressRepository;
import com.api.advisor.infra.services.AddressService;
import com.api.advisor.mocks.AddressMock;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddressServiceTests {

	@InjectMocks
	AddressService service;

	@Mock
	AddressRepository repository;

	@Mock
	AddressMapper mapper;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Should create an address")
	void testCreateAddress() {
		var addressEntity = AddressMock.createEntity();
		var addressPayload = AddressMock.createAddressPayload();
		var addressResponse = AddressMock.createAddressResponse();

		when(mapper.toEntity(addressPayload)).thenReturn(addressEntity);
		when(mapper.toResponse(addressEntity)).thenReturn(addressResponse);
		when(repository.save(any())).thenReturn(addressEntity);

		var result = service.create(addressPayload);

		verify(repository, times(1)).save(any());
		assertNotNull(result);
		assertEquals(result.id(), addressEntity.getId().toString());
		assertEquals(result.city(), addressEntity.getCity());
		assertEquals(result.state(), addressEntity.getState());
		assertEquals(result.street(), addressEntity.getStreet());
		assertEquals(result.neighborhood(), addressEntity.getNeighborhood());
		assertEquals(result.zipcode(), addressEntity.getZipcode());
	}

	@Test
	@DisplayName("Should update an address")
	void testUpdateAddress() {
		var address = AddressMock.createEntity();
		var addressPayload = new AddressPayloadDTO(
		  "updated-city",
		  "updated-street",
		  "updated-state",
		  "updated-neighborhood",
		  "updated-number",
		  "updated-zipcode"
		);
		var addressResponse = new AddressResponseDTO(
	        address.getId().toString(),
		  "updated-city",
		  "updated-state",
		  "updated-street",
		  "updated-neighborhood",
		  "updated-number",
		  "updated-zipcode"
		);

		when(repository.findById(any())).thenReturn(Optional.of(address));
		when(mapper.toResponse(address)).thenReturn(addressResponse);

		var result = service.update(address.getId().toString(), addressPayload);

		verify(repository, times(1)).save(any());
		assertNotNull(result);
		assertEquals(addressResponse.city(), addressPayload.city());
		assertEquals(addressResponse.street(), addressPayload.street());
		assertEquals(addressResponse.state(), addressPayload.state());
		assertEquals(addressResponse.neighborhood(), addressPayload.neighborhood());
		assertEquals(addressResponse.zipcode(), addressPayload.zipcode());
		assertEquals(addressResponse.number(), addressPayload.number());
	}

	@Test
	@DisplayName("Should throw NotFoundException when update address does not exist")
	void testUpdateAddressNotFound() {
		Exception exception = assertThrows(NotFoundException.class, () -> {
			service.update(UUID.randomUUID().toString(), AddressMock.createAddressPayload());
		});

		String expectedMessage = "Address not found.";
		String resultMessage = exception.getMessage();

		verify(repository, times(1)).findById(any());
		assertEquals(expectedMessage, resultMessage);
	}

	@Test
	@DisplayName("Should find an address by id")
	void testFindByIdAddress() {
		var addressEntity = AddressMock.createEntity();
		var addressResponse = AddressMock.createAddressResponse();

		when(mapper.toResponse(addressEntity)).thenReturn(addressResponse);
		when(repository.findById(any())).thenReturn(Optional.of(addressEntity));

		var result = service.findById(UUID.randomUUID().toString());

		verify(repository, times(1)).findById(any());
		assertNotNull(result);
		assertEquals(result.id(), addressEntity.getId().toString());
		assertEquals(result.city(), addressEntity.getCity());
		assertEquals(result.state(), addressEntity.getState());
		assertEquals(result.street(), addressEntity.getStreet());
		assertEquals(result.neighborhood(), addressEntity.getNeighborhood());
		assertEquals(result.zipcode(), addressEntity.getZipcode());
	}

	@Test
	@DisplayName("Should throw NotFoundException when find an address does not exist")
	void testFindByIdAddressNotFound() {
		Exception exception = assertThrows(NotFoundException.class, () -> {
			service.findById(UUID.randomUUID().toString());
		});

		String expectedMessage = "Address not found.";
		String resultMessage = exception.getMessage();

		verify(repository, times(1)).findById(any());
		assertEquals(expectedMessage, resultMessage);
	}

	@Test
	@DisplayName("Should list all addresses")
	void testFindAllAddresses() {
		var addressList = AddressMock.createAddressList();
		var addressResponseList = AddressMock.createAddressResponseList();

		when(repository.findAll()).thenReturn(addressList);
		when(mapper.toResponse(addressList)).thenReturn(addressResponseList);

		var result = service.findAll();

		verify(repository, times(1)).findAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals(addressList.get(0).getId().toString(), result.get(0).id());
	}
}
