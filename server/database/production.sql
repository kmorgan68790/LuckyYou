DROP SCHEMA IF EXISTS production CASCADE;

CREATE SCHEMA IF NOT EXISTS production;

SET search_path TO production;

CREATE TABLE zodiac (
	zodiac_id SERIAL primary key,
	zodiac_name VARCHAR (50),
	start_dates DATE,
	end_dates DATE,
	zodiac_description TEXT
);

CREATE TABLE concord_group (
	concord_group_id SERIAL primary key,
	concord_group_number INTEGER,
	concord_group_description TEXT
    );

CREATE TABLE users (
	user_id SERIAL primary key,
	user_name VARCHAR (50) UNIQUE not null,
	user_password VARCHAR(250) not null,
	email VARCHAR(100) UNIQUE not null,
	first_name VARCHAR(255) not null,
	middle_name VARCHAR(255),
	last_name VARCHAR(255) not null,
	dob DATE not null,
	zodiac_id INTEGER,
	concord_group_id INTEGER,
    numerology_id INTEGER,
	constraint fk_users_zodiac
    		foreign key (zodiac_id)
            references zodiac(zodiac_id),
    constraint fk_users_concord_group
        	foreign key (concord_group_id)
            references concord_group (concord_group_id),
    constraint fk_users_numerology_id
        		foreign key (numerology_id)
                references numerology (numerology_id),
);

CREATE TABLE concord_birthday (
    concord_birthday_number_id SERIAL primary key,
    concord_birthday_number INTEGER,
    concord_group_id INTEGER,
    constraint fk_concord_birthday_concord_group
        foreign key (concord_group_id)
        references concord_group (concord_group_id)
);

CREATE TABLE concord_days (
	concord_days_id SERIAL primary key,
	day_type VARCHAR (50),
	concord_day_number INTEGER,
	concord_group_id INTEGER,
	constraint fk_concord_days_concord_group
	foreign key (concord_group_id)
		references concord_group (concord_group_id)
);

CREATE TABLE numerology_description (
	numerology_description_id SERIAL primary key,
	numerology_type INTEGER,
	numerology_description TEXT
	numerology_number INTEGER
);

CREATE TABLE numerology (
	numerology_id SERIAL primary key,
	life_path_number INTEGER,
	birthday_number INTEGER,
	expression_number INTEGER,
	personality_number INTEGER,
	soul_urge_number INTEGER,
	lucky_number_month INTEGER,
	lucky_number_day INTEGER,
	lucky_number_year INTEGER
	numerology_description_id INTEGER,
	constraint fk_numerology_numerology_description
    		foreign key (numerology_description_id)
            references numerology_description (numerology_description_id)
	);

	CREATE TABLE categories (
    	category_id SERIAL primary key,
    	category_name varchar(50)
    );

    CREATE TABLE user_categories (
    	user_category_id SERIAL primary key,
    	category_id INTEGER,
    	user_id INTEGER,
    	numerology_id INTEGER,
    	zodiac_id INTEGER,
    	concord_group_id INTEGER,
    	constraint fk_user_categories_user
        		foreign key (user_id)
                references users (user_id)
        constraint fk_user_categories_categories
        		foreign key (category_id)
                references categories (category_id)
        constraint fk_user_categories_numerology_id
            		foreign key (numerology_id)
                    references numerology (numerology_id)
        constraint fk_user_categories_zodiac
            		foreign key (zodiac_id)
                    references zodiac (zodiac_id)
        constraint fk_user_categories_concord_group
                		foreign key (concord_group_id)
                        references concord_group (concord_group_id)
    );

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

    ('Lucky Month', 'Lucky Month Description', 1),
    ('Lucky Month', 'Lucky Month Description', 2),
    ('Lucky Month', 'Lucky Month Description', 3),
    ('Lucky Month', 'Lucky Month Description', 4),
    ('Lucky Month', 'Lucky Month Description', 5),
    ('Lucky Month', 'Lucky Month Description', 6),
    ('Lucky Month', 'Lucky Month Description', 7),
    ('Lucky Month', 'Lucky Month Description', 8),
    ('Lucky Month', 'Lucky Month Description', 9),
    ('Lucky Month', 'Lucky Month Description', 11),
    ('Lucky Month', 'Lucky Month Description', 22),
    ('Lucky Month', 'Lucky Month Description', 33),

    ('Lucky Day', 'Lucky Day Description', 1),
    ('Lucky Day', 'Lucky Day Description', 2),
    ('Lucky Day', 'Lucky Day Description', 3),
    ('Lucky Day', 'Lucky Day Description', 4),
    ('Lucky Day', 'Lucky Day Description', 5),
    ('Lucky Day', 'Lucky Day Description', 6),
    ('Lucky Day', 'Lucky Day Description', 7),
    ('Lucky Day', 'Lucky Day Description', 8),
    ('Lucky Day', 'Lucky Day Description', 9),
    ('Lucky Day', 'Lucky Day Description', 11),
    ('Lucky Day', 'Lucky Day Description', 22),
    ('Lucky Day', 'Lucky Day Description', 33),

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
    ('user2', 'password', 'test2@test.com', 'First2', 'Middle2', 'Last2', '1991-03-23', 1, 1);

INSERT INTO numerology (life_path_number, birthday_number, expression_number, personality_number, soul_urge_number,
lucky_number_month, lucky_number_day, lucky_number_year, user_id) VALUES
    (22,  23,  9,  9,  6,  3, 5, 1, 1),
    (22,  23,  9,  9,  6,  3, 5, 1, 2);

INSERT INTO concord_birthday (concord_birthday_number, concord_group_id) VALUES
    (1,1),
    (5,1),
    (7,1),
    (10,1),
    (14,1),
    (16,1),
    (19,1),
    (23,1),
    (25,1),
    (28,1),

    (2,2),
    (4,2),
    (8,2),
    (11,2),
    (13,2),
    (17,2),
    (20,2),
    (22,2),
    (26,2),
    (29,2),
    (31,2),

    (3,3),
    (6,3),
    (9,3),
    (12,3),
    (15,3),
    (18,3),
    (21,3),
    (24,3),
    (27,3),
    (30,3);

INSERT INTO concord_days (day_type, concord_day_number, concord_group_id) VALUES
--	day 1
	('mental', 10, 1),
	('mental', 14, 1),
	('mental', 16, 1),
	('mental', 19, 1),
	('physical', 23, 1),
	('physical', 25, 1),
	('physical', 28, 1),
	('spiritual', 1, 1),
	('spiritual', 5, 1),
	('spiritual', 7, 1),
-- day 5
	('mental', 16, 1),
    ('mental', 19, 1),
    ('mental', 23, 1),
    ('mental', 25, 1),
    ('physical',5, 1, 1),
    ('physical', 5, 5, 1),
    ('physical', 5, 28, 1),
    ('spiritual', 5, 7, 1),
    ('spiritual', 5, 10, 1),
    ('spiritual', 5, 14, 1),
-- day 7
    ('mental', 23, 1),
    ('mental', 25, 1),
    ('mental', 28, 1),
    ('physical', 1, 1),
    ('physical', 5, 1),
    ('physical', 7, 1),
    ('spiritual', 10, 1),
    ('spiritual', 14, 1),
    ('spiritual', 16, 1),
    ('spiritual', 19, 1),
-- day 10
    ('mental', 1, 1),
    ('mental', 5, 1),
    ('mental', 28, 1),
    ('physical', 7, 1),
    ('physical', 10, 1),
    ('physical', 14, 1),
    ('spiritual', 16, 1),
    ('spiritual', 19, 1),
    ('spiritual', 23, 1),
    ('spiritual', 25, 1),
-- day 14
    ('mental',  1, 1),
    ('mental',  5, 1),
    ('mental',  7, 1),
    ('physical', 10, 1),
    ('physical', 14, 1),
    ('physical', 16, 1),
    ('physical', 19, 1),
    ('spiritual', 23, 1),
    ('spiritual', 25, 1),
    ('spiritual', 28, 1),
-- day 16
    ('mental', 14, 1),
    ('mental', 16, 1),
    ('mental', 19, 1),
    ('physical',23, 1),
    ('physical',1, 1),
    ('physical', 5, 1),
    ('physical', 28, 1),
    ('spiritual', 5, 1),
    ('spiritual', 7, 1),
    ('spiritual', 10, 1),
-- day 19
    ('mental', 19, 1),
    ('mental', 23, 1),
    ('mental', 25, 1),
    ('mental',28, 1),
    ('physical',1, 1),
    ('physical', 5, 1),
    ('physical', 28, 1),
    ('spiritual', 7, 1),
    ('spiritual', 10, 1),
    ('spiritual', 14, 1),
    ('spiritual', 16, 1),
-- day 23
    ('mental', 1, 1),
    ('mental', 25, 1),
    ('mental', 28, 1),
    ('physical',5, 1),
    ('physical', 7, 1),
    ('physical', 10, 1),
    ('spiritual', 14, 1),
    ('spiritual', 16, 1),
    ('spiritual', 19, 1),
    ('spiritual', 23, 1),
-- day 25
    ('mental', 1, 1),
    ('mental', 5, 1),
    ('physical',7, 1),
    ('physical', 10, 1),
    ('physical', 14, 1),
    ('physical', 16, 1),
    ('spiritual', 19, 1),
    ('spiritual', 23, 1),
    ('spiritual', 25, 1),
    ('spiritual', 28, 1),
-- day 28
    ('mental', 5, 1),
    ('mental', 7, 1),
    ('mental', 10, 1),
    ('physical' 14, 1),
    ('physical', 16, 1),
    ('physical', 19, 1),
    ('physical', 23, 1),
    ('spiritual', 1, 1),
    ('spiritual', 25, 1),
    ('spiritual', 28, 1),
-- day 2
    ('mental', 13, 2),
    ('mental', 17, 2),
    ('mental', 20, 2),
    ('mental', 22, 2),
    ('physical', 26, 2),
    ('physical', 29, 2),
    ('physical', 31, 2),
    ('spiritual', 2, 2),
    ('spiritual', 4, 2),
    ('spiritual', 8, 2),
    ('spiritual', 11, 2),
-- day 4
    ('mental', 20, 2),
    ('mental', 22, 2),
    ('mental', 26, 2),
    ('mental',29, 2),
    ('physical', 2, 2),
    ('physical', 4, 2),
    ('physical', 31, 2),
    ('spiritual', 8, 2),
    ('spiritual', 11, 2),
    ('spiritual', 13, 2),
    ('spiritual', 17, 2),
-- day 8
    ('mental', 26, 2),
    ('mental', 29, 2),
    ('mental', 31, 2),
    ('physical', 2, 2),
    ('physical', 4, 2),
    ('physical', 8, 2),
    ('physical', 11, 2),
    ('spiritual', 13, 2),
    ('spiritual', 17, 2),
    ('spiritual', 20, 2),
    ('spiritual', 22, 2),
-- day 11
    ('mental', 2, 2),
    ('mental',4, 2),
    ('mental',31, 2),
    ('physical', 8, 2),
    ('physical', 11, 2),
    ('physical', 13, 2),
    ('physical', 17, 2),
    ('spiritual', 20, 2),
    ('spiritual', 22, 2),
    ('spiritual', 26, 2),
    ('spiritual', 29, 2),
-- day 13
    ('mental', 2, 2),
    ('mental',4, 2),
    ('mental',8, 2),
    ('mental',11, 2),
    ('physical', 13, 2),
    ('physical', 17, 2),
    ('physical', 20, 2),
    ('physical', 22, 2),
    ('spiritual', 20, 2),
    ('spiritual', 26, 2),
    ('spiritual', 29, 2),
    ('spiritual', 31, 2),
-- day 17
    ('mental', 17, 2),
    ('mental', 20, 2),
    ('mental', 22, 2),
    ('mental', 26, 2),
    ('physical',  2, 2),
    ('physical',  29, 2),
    ('physical',  31, 2),
    ('spiritual',  4, 2),
    ('spiritual',  8, 2),
    ('spiritual',  11, 2),
    ('spiritual',  13, 2),
-- day 20
    ('mental', 22, 2),
    ('mental',26, 2),
    ('mental',29, 2),
    ('mental',31, 2),
    ('physical', 2, 2),
    ('physical', 4, 2),
    ('physical', 8, 2),
    ('spiritual', 11, 2),
    ('spiritual', 13, 2),
    ('spiritual', 17, 2),
    ('spiritual', 20, 2),
-- day 22
    ('mental', 2, 2),
    ('mental',29, 2),
    ('mental',31, 2),
    ('physical', 4, 2),
    ('physical', 8, 2),
    ('physical', 11, 2),
    ('physical', 13, 2),
    ('spiritual', 17, 2),
    ('spiritual', 20, 2),
    ('spiritual', 22, 2),
    ('spiritual', 26, 2),
-- day 26
    ('mental', 2, 2),
    ('mental',4, 2),
    ('mental',8, 2),
    ('physical', 11, 2),
    ('physical', 13, 2),
    ('physical', 17, 2),
    ('physical', 20, 2),
    ('spiritual', 22, 2),
    ('spiritual', 26, 2),
    ('spiritual', 29, 2),
    ('spiritual', 31, 2),
-- day 29
    ('mental', 4, 2),
    ('mental', 8, 2),
    ('mental',  11, 2),
    ('mental',  13, 2),
    ('physical',  17, 2),
    ('physical',  20, 2),
    ('physical',  22, 2),
    ('physical',  26, 2),
    ('spiritual',  2, 2),
    ('spiritual',  29, 2),
    ('spiritual', 31, 2),
-- day 31
    ('mental', 8, 2),
    ('mental',  11, 2),
    ('mental',  13, 2),
    ('mental',  17, 2),
    ('physical',  20, 2),
    ('physical',  22, 2),
    ('physical',  26, 2),
    ('physical',  31, 2),
    ('spiritual', 2, 2),
    ('spiritual', 4, 2),
    ('spiritual', 31, 2),
-- day 3
    ('mental', 15, 3),
    ('mental',  18, 3),
    ('mental',  21, 3),
    ('mental',  14, 3),
    ('physical',  27, 3),
    ('physical',  30, 3),
    ('spiritual', 3, 3),
    ('spiritual', 6, 3),
    ('spiritual', 9, 3),
    ('spiritual', 12, 3),
-- day 6
    ('mental', 21, 3),
    ('mental',  24, 3),
    ('mental',  27, 3),
    ('mental',  30, 3),
    ('physical',  3, 3),
    ('physical',  6, 3),
    ('spiritual', 9, 3),
    ('spiritual', 12, 3),
    ('spiritual', 15, 3),
    ('spiritual', 18, 3),
-- day 9
    ('mental',27, 3),
    ('mental', 30, 3),
    ('physical', 3, 3),
    ('physical', 6, 3),
    ('physical', 9, 3),
    ('physical', 12, 3),
    ('spiritual', 15, 3),
    ('spiritual', 18, 3),
    ('spiritual', 21, 3),
    ('spiritual', 24, 3),
-- day 12
    ('mental', 3, 3),
    ('mental',  6, 3),
    ('physical',  9, 3),
    ('physical',  12, 3),
    ('physical', 15, 3),
    ('physical', 18, 3),
    ('spiritual', 21, 3),
    ('spiritual', 24, 3),
    ('spiritual', 27, 3),
    ('spiritual', 30, 3),
-- day 3
    ('mental', 15, 3),
    ('mental',  18, 3),
    ('mental',  21, 3),
    ('mental',  14, 3),
    ('physical',  27, 3),
    ('physical',  30, 3),
    ('spiritual',  3, 3),
    ('spiritual', 6, 3),
    ('spiritual', 9, 3),
    ('spiritual', 12, 3),
-- day 15
    ('mental', 3, 3),
    ('mental',  6, 3),
    ('mental',  9, 3),
    ('mental',  12, 3),
    ('physical',  15, 3),
    ('physical', 18, 3),
    ('physical', 21, 3),
    ('physical', 24, 3),
    ('spiritual', 27, 3),
    ('spiritual', 30, 3),
-- day 18
    ('mental', 18, 3),
    ('mental', 21, 3),
    ('mental', 24, 3),
    ('mental', 27, 3),
    ('physical', 3, 3),
    ('physical', 30, 3),
    ('spiritual', 6, 3),
    ('spiritual', 9, 3),
    ('spiritual', 12, 3),
    ('spiritual', 15, 3),
-- day 21
    ('mental', 24, 3),
    ('mental',  27, 3),
    ('mental',  30, 3),
    ('physical',  3, 3),
    ('physical',  6, 3),
    ('physical',  9, 3),
    ('spiritual',  12, 3),
    ('spiritual',  15, 3),
    ('spiritual',  18, 3),
    ('spiritual',  21, 3),
-- day 24
    ('mental', 3, 3),
    ('mental',  30, 3),
    ('physical',  6, 3),
    ('physical',  9, 3),
    ('physical',  12, 3),
    ('physical',  15, 3),
    ('spiritual',  18, 3),
    ('spiritual',  21, 3),
    ('spiritual',  24, 3),
    ('spiritual', 27, 3),
-- day 27
    ('mental', 3, 3),
    ('mental',  6, 3),
    ('mental',  9, 3),
    ('physical',  12, 3),
    ('physical',  15, 3),
    ('physical',  18, 3),
    ('physical',  21, 3),
    ('spiritual',  24, 3),
    ('spiritual',  27, 3),
    ('spiritual', 30, 3),
-- day 30
    ('mental', 6, 3),
    ('mental', 9, 3),
    ('mental', 12, 3),
    ('mental', 15, 3),
    ('physical', 18, 3),
    ('physical', 21, 3),
    ('physical', 24, 3),
    ('physical', 27, 3),
    ('spiritual', 3, 3),
    ('spiritual', 30, 3);

INSERT INTO categories (category_name) VALUES
('Love'),
    ('Health'),
    ('Money'),
    ('Career'),
    ('Spirituality');

INSERT INTO user_categories (category_id, user_id, numerology_id, zodiac_id, concord_group_id) VALUES
    (1, 1, ),
    ();