CREATE DATABASE vendor_management;

CREATE TABLE users
(
    username     VARCHAR(100) NOT NULL,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL,
    password     VARCHAR(100) NOT NULL,
    PRIMARY KEY (username),
    UNIQUE (email)
);

ALTER TABLE users ADD COLUMN role VARCHAR(50) NOT NULL DEFAULT 'USER';

SELECT * FROM users;

CREATE TABLE vendors
(
    id uuid PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    business_type VARCHAR(255) NOT NULL
);

SELECT * FROM vendors;
