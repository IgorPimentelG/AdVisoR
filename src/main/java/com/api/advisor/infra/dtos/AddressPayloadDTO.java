package com.api.advisor.infra.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AddressPayloadDTO(
  @NotEmpty(message = "City is required.")
  @Length(max = 100, message = "City must have a max of 100 characters.")
  String city,

  @NotEmpty(message = "Street is required.")
  @Length(max = 100, message = "Street must have a max of 100 characters.")
  String street,

  @NotEmpty(message = "State is required.")
  @Pattern(
	regexp = "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$",
    message = "State must be in the format ##."
  )
  String state,

  @NotEmpty(message = "Neighborhood is required.")
  @Length(max = 100, message = "Neighborhood must have a max of 100 characters.")
  String neighborhood,

  @NotEmpty(message = "Number is required.")
  @Length(max = 15, message = "Number must have a max of 15 characters.")
  String number,

  @NotEmpty(message = "Zipcode is required.")
  @Pattern(
    regexp = "^\\d{5}-\\d{3}$",
    message = "Zipcode must be in the format #####-##."
  )
  String zipcode
) {}
