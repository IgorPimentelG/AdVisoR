CREATE TABLE IF NOT EXISTS tb_clients (
    id UUID NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf CHAR(14) NOT NULL UNIQUE,
    born_date TIMESTAMP NOT NULL CHECK (born_date < CURRENT_TIMESTAMP),
    budget DECIMAL NOT NULL,
    order_preference VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    address_id UUID NOT NULL,

    CONSTRAINT tb_clients_pk PRIMARY KEY (id),
    CONSTRAINT tb_client_address_fk FOREIGN KEY (address_id) REFERENCES tb_addresses (id)
);

CREATE INDEX idx_cpf ON tb_clients (cpf);