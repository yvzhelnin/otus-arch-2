CREATE SCHEMA IF NOT EXISTS "warehouse";

CREATE TABLE IF NOT EXISTS "warehouse"."brand"
(
    code BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS "warehouse"."model"
(
    article              VARCHAR(50) PRIMARY KEY,
    brand_code           BIGINT       NOT NULL,
    name                 VARCHAR(255) NOT NULL,
    is_loaded_to_catalog BOOLEAN      NOT NULL DEFAULT false,
    FOREIGN KEY (brand_code) REFERENCES "warehouse"."brand" ("code")
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE INDEX model_name_idx ON warehouse.model USING hash (name);
CREATE INDEX model_is_loaded_to_catalog_idx ON warehouse.model USING hash (is_loaded_to_catalog);

CREATE TABLE IF NOT EXISTS "warehouse"."equipment"
(
    inventory_number BIGSERIAL PRIMARY KEY,
    model_article    VARCHAR(50) PRIMARY KEY,
    equipment_status VARCHAR(50) NOT NULL DEFAULT 'AVAILABLE',
    FOREIGN KEY (model_article) REFERENCES "warehouse"."model" ("article")
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE INDEX equipment_status_idx ON warehouse.equipment USING hash (equipment_status);

CREATE TABLE IF NOT EXISTS "warehouse"."booking_log"
(
    id BIGSERIAL PRIMARY KEY,
    customer_phone_number VARCHAR(30) NOT NULL ,
    equip_inventory_number BIGINT NOT NULL,
    FOREIGN KEY (equip_inventory_number) REFERENCES "warehouse"."equipment" ("inventory_number")
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);
CREATE INDEX booking_log_customer_phone_number_idx ON warehouse.booking_log USING hash (customer_phone_number);
