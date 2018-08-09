--users and roles

INSERT INTO users (username, password, enabled) VALUES ('carlos', '$2a$10$PYeso//N1xRnxpkJxWybeeb8kIAFCZfV32X1V7ODOtfvR7UPFTBqe', 1);
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$KICcfggp0bEPcJXp1vDYUerxvbLwhYkug8q5JdV9lzT6mvyjBUaUO', 1);

INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_ADMIN');