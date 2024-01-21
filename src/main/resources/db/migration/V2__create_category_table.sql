CREATE TABLE IF NOT EXISTS tb_categories (
    id UUID NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT tb_categories_pk PRIMARY KEY (id)
);

CREATE INDEX idx_name ON tb_categories (name);