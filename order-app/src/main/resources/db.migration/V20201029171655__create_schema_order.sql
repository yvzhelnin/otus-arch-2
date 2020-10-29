CREATE SCHEMA IF NOT EXISTS "order";

CREATE TABLE IF NOT EXISTS "order"."customer_data"
(
    id           VARCHAR(50) PRIMARY KEY,
    first_name   VARCHAR(50)  NOT NULL,
    last_name    VARCHAR(50)  NOT NULL,
    email        VARCHAR(50)  NOT NULL,
    address      VARCHAR(255) NOT NULL,
    phone        VARCHAR(20)  NOT NULL,
    deliver_from timestamptz  NOT NULL,
    deliver_till timestamptz  NOT NULL
);

CREATE TABLE IF NOT EXISTS "order"."order"
(
    id               BIGSERIAL PRIMARY KEY,
    client_id        VARCHAR(100)   NOT NULL,
    customer_data_id VARCHAR(50)    NOT NULL,
    status           VARCHAR(50)    NOT NULL,
    cost             NUMERIC(17, 2) NOT NULL,
    version          INTEGER        NOT NULL,
    FOREIGN KEY (customer_data_id) REFERENCES "order"."customer_data" ("id") ON DELETE CASCADE
);

