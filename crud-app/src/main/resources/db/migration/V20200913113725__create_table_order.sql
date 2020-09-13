CREATE TABLE IF NOT EXISTS "common"."order"
(
    id        VARCHAR PRIMARY KEY,
    client_id VARCHAR NOT NULL UNIQUE,
    cost      NUMERIC(17, 2) DEFAULT 0,
    version   INTEGER NOT NULL
);
ALTER TABLE "common"."order"
    ADD CONSTRAINT "client_id_fkey"
        FOREIGN KEY ("client_id")
            REFERENCES "common"."client" ("id") MATCH SIMPLE
            ON DELETE CASCADE;
