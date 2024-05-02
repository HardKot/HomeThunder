CREATE TABLE jwt
(
    id          UUID PRIMARY KEY   default gen_random_uuid(),
    user_id     UUID      NOT NULL REFERENCES app_users (id),
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    device_name VARCHAR
);
