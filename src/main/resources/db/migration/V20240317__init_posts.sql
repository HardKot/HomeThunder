CREATE TABLE post_categories
(
    id          UUID PRIMARY KEY      default gen_random_uuid(),

    name        VARCHAR(255) NOT NULL,
    description TEXT,

    icon        VARCHAR(255),

    parent_id   UUID REFERENCES post_categories (id),

    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at  TIMESTAMP
);

CREATE TABLE posts
(
    id           UUID PRIMARY KEY      default gen_random_uuid(),

    title        VARCHAR(255) NOT NULL,
    content      TEXT         NOT NULL,

    author_id    UUID         NOT NULL REFERENCES app_users (id),
    category_id  UUID REFERENCES post_categories (id),

    preview_img  VARCHAR(255),
    preview_text TEXT,

    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at   TIMESTAMP
);

CREATE TABLE post_comments
(
    id         UUID PRIMARY KEY   default gen_random_uuid(),

    post_id    UUID      NOT NULL REFERENCES posts (id),
    author_id  UUID      NOT NULL REFERENCES app_users (id),

    content    TEXT      NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    answer_id  UUID REFERENCES post_comments (id),

    deleted_at TIMESTAMP
);

CREATE TABLE post_votes
(
    user_id UUID NOT NULL REFERENCES app_users (id),
    post_id UUID NOT NULL REFERENCES posts (id)
);

CREATE TABLE post_comments_votes
(
    user_id    UUID NOT NULL REFERENCES app_users (id),
    comment_id UUID NOT NULL REFERENCES post_comments (id)
);


CREATE TABLE post_content_pooling
(
    post_id   UUID         NOT NULL REFERENCES posts (id),
    title     VARCHAR(255) NOT NULL,
    text      TEXT,

    anonymous BOOLEAN,
    multiple  BOOLEAN,

    before    TIMESTAMP
);

CREATE TABLE post_content_pooling_variable
(
    id            SERIAL PRIMARY KEY,
    post_id       UUID         NOT NULL REFERENCES posts (id),
    value         VARCHAR(255) NOT NULL,
    anonymous_key VARCHAR(255)
);

CREATE TABLE post_content_pooling_variable_user
(
    user_id         UUID REFERENCES app_users (id),
    variable_id     INT REFERENCES post_content_pooling_variable (id),

    anonymous_value VARCHAR(255) NOT NULL
);
