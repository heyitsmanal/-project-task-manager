INSERT INTO users (email, password)
VALUES
    ('user1@emsi.ma', '$2a$10$kq3G5dYvQeTgB2tq0Q6m3eF4x3fS8U5X8h8QGfUQ1kQJ8Gf7o5qzC'),
    ('user2@emsi.ma', '$2a$10$kq3G5dYvQeTgB2tq0Q6m3eF4x3fS8U5X8h8QGfUQ1kQJ8Gf7o5qzC')
    ON CONFLICT (email) DO NOTHING;
