
CREATE OR REPLACE PROCEDURE test.set_known_good_state()
-- SET search_path TO test	
LANGUAGE plpgsql
AS $$
BEGIN
	-- Delete existing data from tables (if needed)
	TRUNCATE TABLE concord_days RESTART IDENTITY CASCADE;
    TRUNCATE TABLE concord_birthday RESTART IDENTITY CASCADE;
    TRUNCATE TABLE concord_days RESTART IDENTITY CASCADE;
	TRUNCATE TABLE user_numerology_mapping RESTART IDENTITY CASCADE;
	TRUNCATE TABLE users RESTART IDENTITY CASCADE;
	TRUNCATE TABLE numerology_description RESTART IDENTITY CASCADE;
	TRUNCATE TABLE concord_group RESTART IDENTITY CASCADE;
	TRUNCATE TABLE zodiac RESTART IDENTITY CASCADE;

INSERT INTO zodiac (zodiac_name, start_dates, end_dates, zodiac_description) VALUES
    ('Aries', '2024-03-21', '2024-04-19', 'Description for Aries'),
    ('Taurus', '2024-04-20', '2024-05-20', 'Description for Taurus'),
    ('Gemini', '2024-05-21', '2024-06-20', 'Description for Gemini'),
    ('Cancer', '2024-06-21', '2024-07-22', 'Description for Cancer'),
    ('Leo', '2024-07-23', '2024-08-22', 'Description for Leo'),
    ('Virgo', '2024-08-23', '2024-09-22', 'Description for Virgo'),
    ('Libra', '2024-09-23', '2024-10-22', 'Description for Libra'),
    ('Scorpio', '2024-10-23', '2024-11-21', 'Description for Scorpio'),
    ('Sagittarius', '2024-11-22', '2024-12-21', 'Description for Sagittarius'),
    ('Capricorn', '2024-12-22', '2024-01-19', 'Description for Capricorn'),
    ('Aquarius', '2024-01-20', '2024-02-18', 'Description for Aquarius'),
    ('Pisces', '2024-02-19', '2024-03-20', 'Description for Pisces');

INSERT INTO concord_group (concord_group_number, concord_group_description) VALUES
    (1, 'Group 1 Description'),
    (2, 'Group 2 Description'),
	(3, 'Group 2 Description');

INSERT INTO numerology_description (numerology_type, numerology_description, numerology_number) VALUES
    ('Life Path', 'Life Path Description', 1),
    ('Life Path', 'Life Path Description', 2),
    ('Life Path', 'Life Path Description', 3),
    ('Life Path', 'Life Path Description', 4),
    ('Life Path', 'Life Path Description', 5),
    ('Life Path', 'Life Path Description', 6),
    ('Life Path', 'Life Path Description', 7),
    ('Life Path', 'Life Path Description', 8),
    ('Life Path', 'Life Path Description', 9),
    ('Life Path', 'Life Path Description', 11),
    ('Life Path', 'Life Path Description', 22),
    ('Life Path', 'Life Path Description', 33),

    ('Birthday', 'Birthday Description', 1),
    ('Birthday', 'Birthday Description', 2),
    ('Birthday', 'Birthday Description', 3),
    ('Birthday', 'Birthday Description', 4),
    ('Birthday', 'Birthday Description', 5),
    ('Birthday', 'Birthday Description', 6),
    ('Birthday', 'Birthday Description', 7),
    ('Birthday', 'Birthday Description', 8),
    ('Birthday', 'Birthday Description', 9),
    ('Birthday', 'Birthday Description', 10),
    ('Birthday', 'Birthday Description', 11),
    ('Birthday', 'Birthday Description', 12),
    ('Birthday', 'Birthday Description', 13),
    ('Birthday', 'Birthday Description', 14),
    ('Birthday', 'Birthday Description', 15),
    ('Birthday', 'Birthday Description', 16),
    ('Birthday', 'Birthday Description', 17),
    ('Birthday', 'Birthday Description', 18),
    ('Birthday', 'Birthday Description', 19),
    ('Birthday', 'Birthday Description', 20),
    ('Birthday', 'Birthday Description', 21),
    ('Birthday', 'Birthday Description', 22),
    ('Birthday', 'Birthday Description', 23),
    ('Birthday', 'Birthday Description', 24),
    ('Birthday', 'Birthday Description', 25),
    ('Birthday', 'Birthday Description', 26),
    ('Birthday', 'Birthday Description', 27),
    ('Birthday', 'Birthday Description', 28),
    ('Birthday', 'Birthday Description', 29),
    ('Birthday', 'Birthday Description', 30),
    ('Birthday', 'Birthday Description', 31),

    ('Expression', 'Expression Description', 1),
    ('Expression', 'Expression Description', 2),
    ('Expression', 'Expression Description', 3),
    ('Expression', 'Expression Description', 4),
    ('Expression', 'Expression Description', 5),
    ('Expression', 'Expression Description', 6),
    ('Expression', 'Expression Description', 7),
    ('Expression', 'Expression Description', 8),
    ('Expression', 'Expression Description', 9),
    ('Expression', 'Expression Description', 11),
    ('Expression', 'Expression Description', 22),
    ('Expression', 'Expression Description', 33),

    ('Personality', 'Personality Description', 1),
    ('Personality', 'Personality Description', 2),
    ('Personality', 'Personality Description', 3),
    ('Personality', 'Personality Description', 4),
    ('Personality', 'Personality Description', 5),
    ('Personality', 'Personality Description', 6),
    ('Personality', 'Personality Description', 7),
    ('Personality', 'Personality Description', 8),
    ('Personality', 'Personality Description', 9),
    ('Personality', 'Personality Description', 11),
    ('Personality', 'Personality Description', 22),
    ('Personality', 'Personality Description', 33),

    ('Soul Urge', 'Soul Urge Description', 1),
    ('Soul Urge', 'Soul Urge Description', 2),
    ('Soul Urge', 'Soul Urge Description', 3),
    ('Soul Urge', 'Soul Urge Description', 4),
    ('Soul Urge', 'Soul Urge Description', 5),
    ('Soul Urge', 'Soul Urge Description', 6),
    ('Soul Urge', 'Soul Urge Description', 7),
    ('Soul Urge', 'Soul Urge Description', 8),
    ('Soul Urge', 'Soul Urge Description', 9),
    ('Soul Urge', 'Soul Urge Description', 11),
    ('Soul Urge', 'Soul Urge Description', 22),
    ('Soul Urge', 'Soul Urge Description', 33),

    ('Lucky Number', 'Lucky Number Description', 1),
    ('Lucky Number', 'Lucky Number Description', 2),
    ('Lucky Number', 'Lucky Number Description', 3),
    ('Lucky Number', 'Lucky Number Description', 4),
    ('Lucky Number', 'Lucky Number Description', 5),
    ('Lucky Number', 'Lucky Number Description', 6),
    ('Lucky Number', 'Lucky Number Description', 7),
    ('Lucky Number', 'Lucky Number Description', 8),
    ('Lucky Number', 'Lucky Number Description', 9),
    ('Lucky Number', 'Lucky Number Description', 11),
    ('Lucky Number', 'Lucky Number Description', 22),
    ('Lucky Number', 'Lucky Number Description', 33),

    ('Lucky Year', 'Lucky Year Description', 1),
    ('Lucky Year', 'Lucky Year Description', 2),
    ('Lucky Year', 'Lucky Year Description', 3),
    ('Lucky Year', 'Lucky Year Description', 4),
    ('Lucky Year', 'Lucky Year Description', 5),
    ('Lucky Year', 'Lucky Year Description', 6),
    ('Lucky Year', 'Lucky Year Description', 7),
    ('Lucky Year', 'Lucky Year Description', 8),
    ('Lucky Year', 'Lucky Year Description', 9),
    ('Lucky Year', 'Lucky Year Description', 11),
    ('Lucky Year', 'Lucky Year Description', 22),
    ('Lucky Year', 'Lucky Year Description', 33);

INSERT INTO users (user_name, user_password, email, first_name, middle_name, last_name, dob, zodiac_id, concord_group_id) VALUES
    ('user', 'password', 'test@test.com', 'First', 'Middle', 'Last', '1992-08-11',5, 2),
    ('user2', 'password', 'test2@test.com', 'Second', '', 'Last', '1991-03-23',1,1);

INSERT INTO user_numerology_mapping (user_id, numerology_type, numerology_description_id) VALUES
--    22
    (1, 'Life Path', 11),
--    11
    (1, 'Birthday', 23),
--    9
    (1, 'Expression', 52),
--    3
    (1, 'Personality', 58),
--    6
    (1, 'Soul Urge', 73),
--  22 or 4
    (1, 'Lucky Number', 90),
--  9 same as destiny/exp. number
    (1, 'Lucky Year', 100),

--    1
    (2, 'Life Path', 1),
--    23
    (2, 'Birthday', 35),
--    4
    (2, 'Expression', 47),
--    1
    (2, 'Personality', 56),
--    3
    (2, 'Soul Urge', 70),
--   2
    (2, 'Lucky Number', 81),
--  4 same as destiny/exp. number
    (2, 'Lucky Year', 95);


INSERT INTO concord_birthday (concord_birthday_number, concord_group_id) VALUES
   	(23,1),

    (11,2),

    (3,3),
    (6,3);

INSERT INTO concord_days (day_type, concord_birthday_number, concord_day_number, concord_group_id) VALUES
-- day 23
    ('Mental', 23, 1, 1),
    ('Mental',23, 25, 1),
    ('Mental',23, 28, 1),
    ('Physical',23,5, 1),
    ('Physical',23, 7, 1),
    ('Physical',23, 10, 1),
    ('Spiritual',23, 14, 1),
    ('Spiritual',23, 16, 1),
    ('Spiritual',23, 19, 1),
    ('Spiritual',23, 23, 1),

-- day 11
    ('Mental', 11, 2, 2),
    ('Mental',11,4, 2),
    ('Mental',11,31, 2),
    ('Physical',11, 8, 2),
    ('Physical', 11,11, 2),
    ('Physical',11, 13, 2),
    ('Physical',11, 17, 2),
    ('Spiritual',11, 20, 2),
    ('Spiritual',11, 22, 2),
    ('Spiritual',11, 26, 2),
    ('Spiritual',11, 29, 2),

-- day 3
    ('Mental', 3, 15, 3),
    ('Mental', 3,  18, 3),
    ('Mental', 3,  21, 3),
    ('Mental', 3,  14, 3),
    ('Physical', 3,  27, 3),
    ('Physical', 3,  30, 3),
    ('Spiritual', 3, 3, 3),
    ('Spiritual', 3, 6, 3),
    ('Spiritual', 3, 9, 3),
    ('Spiritual', 3, 12, 3),
-- day 6
    ('Mental', 6, 21, 3),
    ('Mental', 6, 24, 3),
    ('Mental', 6, 27, 3),
    ('Mental', 6, 30, 3),
    ('Physical',6,  3, 3),
    ('Physical', 6, 6, 3),
    ('Spiritual',6, 9, 3),
    ('Spiritual',6, 12, 3),
    ('Spiritual', 6,15, 3),
    ('Spiritual', 6,18, 3);

-- Commit the transaction
-- COMMIT;
END;
$$;