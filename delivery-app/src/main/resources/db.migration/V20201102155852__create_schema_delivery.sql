CREATE SCHEMA IF NOT EXISTS "delivery";

CREATE TABLE IF NOT EXISTS "delivery"."customer_data"
(
    id        BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(50)  NOT NULL,
    address   VARCHAR(255) NOT NULL,
    phone     VARCHAR(20)  NOT NULL
);

CREATE TABLE IF NOT EXISTS "delivery"."courier"
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    phone      VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS "delivery"."delivery_info"
(
    id               BIGSERIAL PRIMARY KEY,
    customer_data_id BIGINT      NOT NULL,
    delivery_type    VARCHAR(20) NOT NULL,
    delivery_status  VARCHAR(20) NOT NULL,
    cost             NUMERIC(17, 2),
    deliver_from     timestamptz NOT NULL,
    deliver_till     timestamptz NOT NULL,
    return_date      date        NOT NULL,
    courier_id       BIGINT,
    FOREIGN KEY (customer_data_id) REFERENCES "delivery"."customer_data" ("id") ON DELETE CASCADE,
    FOREIGN KEY (courier_id) REFERENCES "delivery"."courier" ("id")
);
CREATE INDEX IF NOT EXISTS delivery_terms_idx ON delivery.delivery_info USING btree (deliver_from, deliver_till);
CREATE INDEX IF NOT EXISTS delivery_return_idx ON delivery.delivery_info USING btree (return_date);
CREATE INDEX IF NOT EXISTS delivery_status_idx ON delivery.delivery_info USING hash (delivery_status);
