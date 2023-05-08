CREATE TABLE users (
                     id          VARCHAR(36)     NOT NULL  PRIMARY KEY,
                     name        VARCHAR(255)    NOT NULL,
                     email       VARCHAR(255)    NOT NULL,
                     birthdate   DATE            NOT NULL,
                     address     VARCHAR(255)    NOT NULL,
                     is_active   BOOLEAN 	      NOT NULL  DEFAULT TRUE,
                     created_at  TIMESTAMP(6)    NOT NULL,
                     updated_at  TIMESTAMP(6)    NOT NULL,
                     deleted_at  TIMESTAMP(6)
);