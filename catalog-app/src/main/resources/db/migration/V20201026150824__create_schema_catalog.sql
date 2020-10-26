CREATE SCHEMA IF NOT EXISTS "catalog";

CREATE TABLE IF NOT EXISTS "catalog"."position"
(
    article            VARCHAR(50) PRIMARY KEY,
    brand_name         VARCHAR(100)   NOT NULL,
    name               VARCHAR(255)   NOT NULL,
    pledge             NUMERIC(17, 2) NOT NULL,
    daily_cost         NUMERIC(17, 2) NOT NULL,
    equipment_category VARCHAR(50)    NOT NULL,
    season             VARCHAR(50)    NOT NULL
);
CREATE INDEX position_name_idx ON catalog.position USING hash (name);
CREATE INDEX position_brand_name_idx ON catalog.position USING hash (brand_name);
CREATE INDEX position_equipment_category_idx ON catalog.position USING hash (equipment_category);
CREATE INDEX position_season_idx ON catalog.position USING hash (season);
