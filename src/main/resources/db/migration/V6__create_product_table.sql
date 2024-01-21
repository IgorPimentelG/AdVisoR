CREATE TABLE IF NOT EXISTS tb_products (
    id UUID NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    price DECIMAL NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    category_id UUID NOT NULL,
    establishment_id UUID NOT NULL,

    CONSTRAINT tb_products_pk PRIMARY KEY (id),
    CONSTRAINT tb_product_category_fk FOREIGN KEY (category_id) REFERENCES tb_categories (id),
    CONSTRAINT tb_product_establishment_fk FOREIGN KEY (establishment_id) REFERENCES tb_establishments (id)
);