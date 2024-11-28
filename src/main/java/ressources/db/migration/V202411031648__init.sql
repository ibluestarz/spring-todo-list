CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        uuid UUID DEFAULT gen_random_uuid() NOT NULL UNIQUE,
                        created_at TIMESTAMPTZ DEFAULT NOW(),
                        email VARCHAR NOT NULL UNIQUE,
                        encoded_password VARCHAR NOT NULL
);

CREATE TABLE "todolist" (
                            id SERIAL PRIMARY KEY,
                            uuid UUID DEFAULT gen_random_uuid() NOT NULL UNIQUE,
                            title VARCHAR NOT NULL,
                            description TEXT,
                            author_id INT NOT NULL REFERENCES "user"(id),
                            created_date TIMESTAMPTZ DEFAULT NOW(),
                            updated_date TIMESTAMPTZ DEFAULT NOW()
);
