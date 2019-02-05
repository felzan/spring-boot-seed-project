CREATE TABLE token
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    token text NOT NULL,
    expires_at datetime DEFAULT current_timestamp,
    created_at datetime DEFAULT current_timestamp,
    updated_at datetime DEFAULT current_timestamp
);
