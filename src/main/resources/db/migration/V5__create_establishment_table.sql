CREATE TABLE IF NOT EXISTS tb_establishments (
    id UUID NOT NULL,
    corporate_name VARCHAR(255) NOT NULL,
    cnpj CHAR(18) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    address_id UUID NOT NULL,

    CONSTRAINT tb_establishments_pk PRIMARY KEY (id),
    CONSTRAINT tb_establishment_address_fk FOREIGN KEY (address_id) REFERENCES tb_establishments (id)
);

CREATE INDEX idx_cnpj ON tb_establishments (cnpj);