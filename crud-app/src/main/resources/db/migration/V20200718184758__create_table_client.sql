CREATE TABLE IF NOT EXISTS "common"."customerData"
(
    id         VARCHAR PRIMARY KEY,
    username   VARCHAR NOT NULL,
    first_name VARCHAR NOT NULL,
    last_name  VARCHAR NOT NULL,
    email      VARCHAR,
    phone      VARCHAR
);
