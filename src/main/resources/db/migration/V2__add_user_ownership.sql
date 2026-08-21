DELETE FROM products;
DELETE FROM customers;

ALTER TABLE products
    ADD user_id VARCHAR(36) NULL;

ALTER TABLE customers
    ADD user_id VARCHAR(36) NULL;

ALTER TABLE products
    ALTER COLUMN user_id VARCHAR(36) NOT NULL;

ALTER TABLE customers
    ALTER COLUMN user_id VARCHAR(36) NOT NULL;

ALTER TABLE products
    ADD CONSTRAINT fk_products_user FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE customers
    ADD CONSTRAINT fk_customers_user FOREIGN KEY (user_id) REFERENCES users(id);
