CREATE TABLE users(
    id VARCHAR(36) PRIMARY KEY,
    username VARCHAR(60) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE products(
    code INT NOT NULL PRIMARY KEY,
    description VARCHAR(60) NOT NULL,
    bar_code VARCHAR(14) NOT NULL,
    gross_weight FLOAT NOT NULL,
    net_weight FLOAT NOT NULL
);

CREATE TABLE customers(
    code INT NOT NULL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    nickname VARCHAR(100) NOT NULL,
    document VARCHAR(14) NOT NULL,
    address VARCHAR(200) NOT NULL
);