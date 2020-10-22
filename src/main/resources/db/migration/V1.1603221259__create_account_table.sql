CREATE TABLE IF NOT EXISTS accounts
(
    id         BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    enabled    BOOLEAN DEFAULT TRUE,
    user_uid   VARCHAR(255) NOT NULL,
    amount     NUMERIC(10,2)
);