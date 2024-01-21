CREATE TABLE IF NOT EXISTS tb_addresses (
    id UUID NOT NULL,
    city VARCHAR(100) NOT NULL,
    street VARCHAR(100) NULL,
    state CHAR(2) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    number VARCHAR(15) NOT NULL,
    zipcode CHAR(9) NOT NULL,

    CONSTRAINT tb_addresses_pk PRIMARY KEY (id)
);

CREATE INDEX idx_zipcode ON tb_addresses (zipcode);