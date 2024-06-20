DROP PROCEDURE IF EXISTS test.set_known_good_state;
-- Create function to set known good state
CREATE OR REPLACE PROCEDURE test.set_known_good_state()
	-- SET search_path TO Procedures
LANGUAGE plpgsql
AS $$
BEGIN
	-- Delete existing data from tables (if needed)
	DELETE FROM test.concord_days;
    DELETE FROM test.concord_birthday;
    DELETE FROM test.user_numerology_mapping;
	DELETE FROM test.users;
	DELETE FROM test.numerology_description;
	DELETE FROM test.concord_group;
    DELETE FROM test.zodiac;

INSERT INTO test.zodiac (zodiac_name, start_dates, end_dates, zodiac_description) VALUES
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

INSERT INTO test.concord_group (concord_group_number, concord_group_description) VALUES
    (1, 'Group 1 Description'),
    (2, 'Group 2 Description'),
	(3, 'Group 2 Description');

INSERT INTO test.numerology_description (numerology_type, numerology_description, numerology_number) VALUES
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

INSERT INTO test.users (user_name, user_password, email, first_name, middle_name, last_name, dob) VALUES
    ('user', 'password', 'test@test.com', 'First', 'Middle', 'Last', '1992-08-11'),
    ('user2', 'password', 'test2@test.com', 'Second', '', 'Last', '1991-03-23');

INSERT INTO test.user_numerology_mapping (numerology_type) VALUES
--    22
    ( 'Life Path'),
--    11
    ( 'Birthday'),
--    9
    ( 'Expression'),
--    3
    ( 'Personality'),
--    6
    ( 'Soul Urge'),
--  22 or 4
    ( 'Lucky Number'),
--  9 same as destiny/exp. number
    ( 'Lucky Year'),

--    1
    ( 'Life Path'),
--    23
    ( 'Birthday'),
--    4
    ( 'Expression'),
--    1
    ( 'Personality'),
--    3
    ( 'Soul Urge'),
--   2
    ( 'Lucky Number'),
--  4 same as destiny/exp. number
    ( 'Lucky Year');

INSERT INTO test.concord_birthday (concord_birthday_number) VALUES
    (1),

    (11),

    (3),
    (6);

INSERT INTO test.concord_days (day_type, concord_day_number) VALUES
-- day 23
    ('mental', 1),
    ('mental', 25),
    ('mental', 28),
    ('physical',5),
    ('physical', 7),
    ('physical', 10),
    ('spiritual', 14),
    ('spiritual', 16),
    ('spiritual', 19),
    ('spiritual', 23),

-- day 11
    ('mental', 2),
    ('mental',4),
    ('mental',31),
    ('physical', 8),
    ('physical', 11),
    ('physical', 13),
    ('physical', 17),
    ('spiritual', 20),
    ('spiritual', 22),
    ('spiritual', 26),
    ('spiritual', 29),

-- day 3
    ('mental', 15),
    ('mental',  18),
    ('mental',  21),
    ('mental',  14),
    ('physical',  27),
    ('physical',  30),
    ('spiritual', 3),
    ('spiritual', 6),
    ('spiritual', 9),
    ('spiritual', 12),
-- day 6
    ('mental', 21),
    ('mental',  24),
    ('mental',  27),
    ('mental',  30),
    ('physical',  3),
    ('physical',  6),
    ('spiritual', 9),
    ('spiritual', 12),
    ('spiritual', 15),
    ('spiritual', 18);

-- Commit the transaction
-- COMMIT;
END;
$$;