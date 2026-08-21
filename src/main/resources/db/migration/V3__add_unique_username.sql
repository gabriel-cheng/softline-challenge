DELETE FROM users;

ALTER TABLE users
    ADD CONSTRAINT uq_users_username UNIQUE (username);
