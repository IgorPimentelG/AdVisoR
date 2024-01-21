CREATE TABLE IF NOT EXISTS tb_clients_categories (
    client_id UUID NOT NULL,
    category_id UUID NOT NULL,

    CONSTRAINT tb_clients_categories_pk PRIMARY KEY (client_id, category_id),
    CONSTRAINT tb_client_fk FOREIGN KEY (client_id) REFERENCES tb_clients (id),
    CONSTRAINT tb_category_fk FOREIGN KEY (category_id) REFERENCES tb_categories (id)
);