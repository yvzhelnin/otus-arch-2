CREATE TABLE IF NOT EXISTS "common"."account"
(
    id        VARCHAR PRIMARY KEY,
    client_id VARCHAR NOT NULL UNIQUE,
    balance   NUMERIC(17, 2) DEFAULT 0
);
ALTER TABLE "common"."account"
    ADD CONSTRAINT "client_id_fkey"
        FOREIGN KEY ("client_id")
            REFERENCES "common"."customerData" ("id") MATCH SIMPLE
            ON DELETE CASCADE;
