package com.api.advisor.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_establishments")
public class Establishment implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "corporate_name")
	private String corporateName;

	private String cnpj;

	@Singular
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "establishment")
	private List<Product> products;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@JoinColumn(name = "created_at")
	private LocalDateTime createdAt;

	@JoinColumn(name = "updated_at")
	private LocalDateTime updatedAt;

	@Builder
	public Establishment(String corporateName, String cnpj, Address address) {
		this.corporateName = corporateName;
		this.cnpj = cnpj;
		this.address = address;
	}

	@PrePersist
	private void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public void addProduct(Product product) {
		if (products.isEmpty()) {
			this.products = new ArrayList<>();
		}
		this.products.add(product);
	}
}
