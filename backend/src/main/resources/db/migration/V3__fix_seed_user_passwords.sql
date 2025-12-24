-- Set password for user1@emsi.ma and user2@emsi.ma to: Password@123
-- BCrypt hash generated for Password@123
UPDATE users
SET password = '$2b$10$9ViqvLMFNZNKYvc/NucA6.PWYMVDtAfsUmwhNdOXiJvGqMST8AtDW'
WHERE email IN ('user1@emsi.ma', 'user2@emsi.ma');
