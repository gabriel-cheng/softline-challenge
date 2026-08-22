ALTER TABLE products
ADD CONSTRAINT UQ_products_code UNIQUE (code),
    CONSTRAINT UQ_products_barCode UNIQUE (bar_code);

ALTER TABLE customers
ADD CONSTRAINT UQ_customers_code UNIQUE (code),
    CONSTRAINT UQ_customers_document UNIQUE (document);