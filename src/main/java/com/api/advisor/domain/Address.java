package com.api.advisor.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Address implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String city;
	private String street;
	private String state;
	private String neighborhood;
	private Integer number;

	@Builder
	public Address(String city, String street, String state, String neighborhood, Integer number) {
		this.city = city;
		this.street = street;
		this.state = state;
		this.neighborhood = neighborhood;
		this.number = number;
	}
}
