package com.api.advisor.domain;

import com.api.advisor.domain.types.OrderPreference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Client implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "born_date")
	private LocalDate bornDate;

	private BigDecimal budget;
	private String email;
	private String CPF;

	@Column(name = "order_preference")
	private OrderPreference orderPreference;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToMany
	@JoinTable(
	  name = "client_category",
	  joinColumns = @JoinColumn(name = "client_id"),
	  inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private List<Category> categories;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Builder
	public Client(
	  String fullName,
	  LocalDate bornDate,
	  String email,
	  String CPF,
	  BigDecimal budget,
	  OrderPreference orderPreference,
	  Address address
	) {
		this.fullName = fullName;
		this.bornDate = bornDate;
		this.email = email;
		this.CPF = CPF;
		this.budget = budget;
		this.orderPreference = orderPreference;
		this.address = address;
	}

	@PrePersist
	private void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public void addCategory(Category category) {
		if (categories.isEmpty()) {
			this.categories = new ArrayList<>();
		}
		this.addCategory(category);
	}
}
