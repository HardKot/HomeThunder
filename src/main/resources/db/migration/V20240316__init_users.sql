CREATE TABLE app_users
(
    id            UUID PRIMARY KEY      default gen_random_uuid(),

    firstname     VARCHAR(255) NOT NULL,
    lastname      VARCHAR(255) NOT NULL,
    patronymic    VARCHAR(255),

    avatar        VARCHAR(255),

    gender        VARCHAR(255) NOT NULL,
    birthday      DATE,

    phone         VARCHAR(255),

    password      VARCHAR(255),

    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at    TIMESTAMP,
    banned_before TIMESTAMP
);


CREATE TABLE app_roles
(
    id         UUID PRIMARY KEY      default gen_random_uuid(),
    name       VARCHAR(255) NOT NULL,
    image      VARCHAR(255),

    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP
);


CREATE TABLE user_rules
(
    id          SERIAL PRIMARY KEY,
    user_id     UUID NOT NULL REFERENCES app_users (id),
    rule        VARCHAR(255),
    activate_to TIMESTAMP
);

CREATE TABLE role_rules
(
    role_id UUID NOT NULL REFERENCES app_roles (id),
    rule    VARCHAR(255)
);

CREATE TABLE user_roles
(
    user_id     UUID NOT NULL REFERENCES app_users (id) UNIQUE,
    role_id     UUID NOT NULL REFERENCES app_roles (id),
    activate_to TIMESTAMP
);


CREATE TABLE user_email
(
    user_id    UUID         NOT NULL REFERENCES app_users (id) UNIQUE,
    email      VARCHAR(255) NOT NULL,
    isActivate BOOLEAN
);
