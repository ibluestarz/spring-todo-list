CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    uuid UUID DEFAULT gen_random_uuid() NOT NULL UNIQUE,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    email VARCHAR NOT NULL,
    encoded_password VARCHAR NOT NULL,
    UNIQUE (email)
);


