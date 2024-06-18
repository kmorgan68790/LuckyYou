DROP SCHEMA IF EXISTS production CASCADE;

CREATE SCHEMA IF NOT EXISTS production;

SET search_path TO production;

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

CREATE TABLE zodiac (
	zodiac_id SERIAL primary key,
	zodiac_name VARCHAR (50),
	start_dates DATE,
	end_dates DATE,
	zodiac_description TEXT
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
	constraint fk_users_zodiac
    		foreign key (zodiac_id)
            references zodiac(zodiac_id)
);

CREATE TABLE concord_group (
	concord_group_id SERIAL primary key,
	concord_group_number INTEGER,
	concord_group_description TEXT
);

CREATE TABLE concord_days (
	concord_days_id SERIAL primary key,
	day_type VARCHAR (50),
	day_number INTEGER,
	concord_day_number INTEGER,
	concord_group_id INTEGER,
	constraint fk_concord_days_concord_group
		foreign key (concord_group_id)
		references concord_group (concord_group_id)
);

CREATE TABLE user_concord_group (
	user_concord_group_id SERIAL primary key,
	concord_group_id INTEGER,
	user_id INTEGER UNIQUE,
	constraint fk_user_concord_group_concord_group
		foreign key (concord_group_id)
		references concord_group (concord_group_id),
	constraint fk_user_concord_group_user
		foreign key (user_id)
		references users (user_id)	
); 

CREATE TABLE numerology (
	numerology_id SERIAL primary key,
	life_path_number INTEGER,
	life_path_description TEXT,
	birthday_number INTEGER,
	birthday_description TEXT,
	expression_number INTEGER,
	expression_description TEXT,
	personality_number INTEGER,
	personality_description TEXT,
	soul_urge_number INTEGER,
	soul_urge_description TEXT,
	lucky_number_month INTEGER,
	lucky_number_day INTEGER,
	lucky_number_year INTEGER,
	user_id INTEGER,
	concord_group_id INTEGER,
	constraint fk_numerology_users
    		foreign key (user_id)
            references users (user_id),
	constraint fk_numerology_concord_group
    		foreign key (concord_group_id)
            references concord_group (concord_group_id)
	);

--CREATE TABLE categories_numerology (
--	categories_numerology_id SERIAL primary key,
--	category_id INTEGER,
--	numerology_id INTEGER,
--	constraint fk_categories_numerology_categories
--    		foreign key (category_id)
--            references categories (category_id),
--	constraint fk_categories_numerology_id
--    		foreign key (numerology_id)
--            references numerology (numerology_id)
--);

INSERT INTO categories (category_name) VALUES
    ('Love'),
    ('Health'),
    ('Money'),
    ('Career'),
    ('Spirituality');

INSERT INTO zodiac (zodiac_name, start_dates, end_dates, zodiac_description) VALUES
    ('Aries', '2023-03-21', '2023-04-19', 'The first sign of the zodiac'),
    ('Taurus', '2023-04-20', '2023-05-20', 'The second sign of the zodiac');

INSERT INTO users (user_name, user_password, email, first_name, middle_name, last_name, dob, zodiac_id) VALUES
    ('user', 'password', 'test@test.com', 'First', 'Middle', 'Last', '1990-03-23', 1),
    ('user2', 'password', 'test2@test.com', 'First2', 'Middle2', 'Last2', '1991-04-26', 2),

INSERT INTO concord_group (concord_group_number, concord_group_description) VALUES
    (1, 'Group 1 Description'),
    (2, 'Group 2 Description'),
	(3, 'Group 2 Description');

INSERT INTO concord_days (day_type, day_number, concord_day_number, concord_group_id) VALUES
	('mental', 1, 10, 1),
	('mental', 1, 14, 1),
	('mental', 1, 16, 1),
	('mental', 1, 19, 1),
	('physical', 1, 23, 1),
	('physical', 1, 25, 1),
	('physical', 1, 28, 1),
	('spiritual', 1, 1),
	('spiritual', 5, 1),
	('spiritual', 7, 1),

	('mental', 5, 16, 1),
    ('mental', 5, 19, 1),
    ('mental', 5, 23, 1),
    ('mental', 5, 25, 1),
    ('physical',5, 1, 1),
    ('physical', 5, 5, 1),
    ('physical', 5, 28, 1),
    ('spiritual', 5, 7, 1),
    ('spiritual', 5, 10, 1),
    ('spiritual', 5, 14, 1),

    ('mental', 7, 23, 1),
    ('mental', 7, 25, 1),
    ('mental', 7, 28, 1),
    ('physical',7, 1, 1),
    ('physical', 7, 5, 1),
    ('physical', 7, 7, 1),
    ('spiritual', 7, 10, 1),
    ('spiritual', 7, 14, 1),
    ('spiritual', 7, 16, 1),
    ('spiritual', 7, 19, 1),

    ('mental', 10, 1, 1),
    ('mental', 10, 5, 1),
    ('mental', 10, 28, 1),
    ('physical',10, 7, 1),
    ('physical',10, 10, 1),
    ('physical', 10, 14, 1),
    ('spiritual', 10, 16, 1),
    ('spiritual', 10, 19, 1),
    ('spiritual', 10, 23, 1),
    ('spiritual', 10, 25, 1),

    ('mental', 14, 1, 1),
    ('mental', 14, 5, 1),
    ('mental', 14, 7, 1),
    ('physical',14, 10, 1),
    ('physical',14, 14, 1),
    ('physical', 14, 16, 1),
    ('physical', 14, 19, 1),
    ('spiritual', 14, 23, 1),
    ('spiritual', 14, 25, 1),
    ('spiritual', 14, 28, 1),

    ('mental', 16, 14, 1),
    ('mental', 16, 16, 1),
    ('mental', 16, 19, 1),
    ('physical',16, 23, 1),
    ('physical',16, 1, 1),
    ('physical', 16, 5, 1),
    ('physical', 16, 28, 1),
    ('spiritual', 16, 5, 1),
    ('spiritual', 16, 7, 1),
    ('spiritual', 16, 10, 1),

    ('mental', 19, 19, 1),
    ('mental', 19, 23, 1),
    ('mental', 19, 25, 1),
    ('mental',19, 28, 1),
    ('physical',19, 1, 1),
    ('physical', 19, 5, 1),
    ('physical', 19, 28, 1),
    ('spiritual', 19, 7, 1),
    ('spiritual', 19, 10, 1),
    ('spiritual', 19, 14, 1),
    ('spiritual', 19, 16, 1),

    ('mental', 23, 1, 1),
    ('mental', 23, 25, 1),
    ('mental', 23, 28, 1),
    ('physical',23, 5, 1),
    ('physical', 23, 7, 1),
    ('physical', 23, 10, 1),
    ('spiritual', 23, 14, 1),
    ('spiritual', 23, 16, 1),
    ('spiritual', 23, 19, 1),
    ('spiritual', 23, 23, 1),

    ('mental', 25, 1, 1),
    ('mental', 25, 5, 1),
    ('physical',25, 7, 1),
    ('physical', 25, 10, 1),
    ('physical', 25, 14, 1),
    ('physical', 25, 16, 1),
    ('spiritual', 25, 19, 1),
    ('spiritual', 25, 23, 1),
    ('spiritual', 25, 25, 1),
    ('spiritual', 25, 28, 1),

    ('mental', 28, 5, 1),
    ('mental', 28, 7, 1),
    ('mental', 28, 10, 1),
    ('physical',28, 14, 1),
    ('physical', 28, 16, 1),
    ('physical', 28, 19, 1),
    ('physical', 28, 23, 1),
    ('spiritual', 28, 1, 1),
    ('spiritual', 28, 25, 1),
    ('spiritual', 28, 28, 1),

    ('mental', 2, 13, 2),
    ('mental', 2, 17, 2),
    ('mental', 2, 20, 2),
    ('mental',2, 22, 2),
    ('physical', 2, 26, 2),
    ('physical', 2, 29, 2),
    ('physical', 2, 31, 2),
    ('spiritual', 2, 2, 2),
    ('spiritual', 2, 4, 2),
    ('spiritual', 2, 8, 2),
    ('spiritual', 2, 11, 2),

    ('mental', 4, 20, 2),
    ('mental', 4, 22, 2),
    ('mental', 4, 26, 2),
    ('mental',4, 29, 2),
    ('physical', 4, 2, 2),
    ('physical', 4, 4, 2),
    ('physical', 4, 31, 2),
    ('spiritual', 4, 8, 2),
    ('spiritual', 4, 11, 2),
    ('spiritual', 4, 13, 2),
    ('spiritual', 4, 17, 2),

    ('mental', 8, 26, 2),
    ('mental',8, 29, 2),
    ('mental',8, 31, 2),
    ('physical', 8, 2, 2),
    ('physical', 8, 4, 2),
    ('physical', 8, 8, 2),
    ('physical', 8, 11, 2),
    ('spiritual', 8, 13, 2),
    ('spiritual', 8, 17, 2),
    ('spiritual', 8, 20, 2),
    ('spiritual', 8, 22, 2),

    ('mental', 11, 2, 2),
    ('mental',11, 4, 2),
    ('mental',11, 31, 2),
    ('physical', 11, 8, 2),
    ('physical', 11, 11, 2),
    ('physical', 11, 13, 2),
    ('physical', 11, 17, 2),
    ('spiritual', 11, 20, 2),
    ('spiritual', 11, 22, 2),
    ('spiritual', 11, 26, 2),
    ('spiritual', 11, 29, 2),

    ('mental', 13, 2, 2),
    ('mental',13, 4, 2),
    ('mental',13, 8, 2),
    ('mental',13, 11, 2),
    ('physical', 13, 13, 2),
    ('physical', 13, 17, 2),
    ('physical', 13, 20, 2),
    ('physical', 13, 22, 2),
    ('spiritual', 13, 20, 2),
    ('spiritual', 13, 26, 2),
    ('spiritual', 13, 29, 2),
    ('spiritual', 13, 31, 2),

    ('mental', 17, 17, 2),
    ('mental',17, 20, 2),
    ('mental',17, 22, 2),
    ('mental',17, 26, 2),
    ('physical', 17, 2, 2),
    ('physical', 17, 29, 2),
    ('physical', 17, 31, 2),
    ('spiritual', 17, 4, 2),
    ('spiritual', 17, 8, 2),
    ('spiritual', 17, 11, 2),
    ('spiritual', 17, 13, 2),

    ('mental', 20, 22, 2),
    ('mental',20, 26, 2),
    ('mental',20, 29, 2),
    ('mental',20, 31, 2),
    ('physical', 20, 2, 2),
    ('physical', 20, 4, 2),
    ('physical', 20, 8, 2),
    ('spiritual', 20, 11, 2),
    ('spiritual', 20, 13, 2),
    ('spiritual', 20, 17, 2),
    ('spiritual', 20, 20, 2),

    ('mental', 22, 2, 2),
    ('mental',22, 29, 2),
    ('mental',22, 31, 2),
    ('physical', 22, 4, 2),
    ('physical', 22, 8, 2),
    ('physical', 22, 11, 2),
    ('physical', 22, 13, 2),
    ('spiritual', 22, 17, 2),
    ('spiritual', 22, 20, 2),
    ('spiritual', 22, 22, 2),
    ('spiritual', 22, 26, 2),

    ('mental', 26, 2, 2),
    ('mental',26, 4, 2),
    ('mental',26, 8, 2),
    ('physical', 26, 11, 2),
    ('physical', 26, 13, 2),
    ('physical', 26, 17, 2),
    ('physical', 26, 20, 2),
    ('spiritual', 26, 22, 2),
    ('spiritual', 26, 26, 2),
    ('spiritual', 26, 29, 2),
    ('spiritual', 26, 31, 2),

    ('mental',29, 4, 2),
    ('mental',29, 8, 2),
    ('mental', 29, 11, 2),
    ('mental', 29, 13, 2),
    ('physical', 29, 17, 2),
    ('physical', 29, 20, 2),
    ('physical', 29, 22, 2),
    ('physical', 29, 26, 2),
    ('spiritual', 29, 2, 2),
    ('spiritual', 29, 29, 2),
    ('spiritual', 29, 31, 2),

    ('mental',31, 8, 2),
    ('mental', 31, 11, 2),
    ('mental', 31, 13, 2),
    ('mental', 31, 17, 2),
    ('physical', 31, 20, 2),
    ('physical', 31, 22, 2),
    ('physical', 31, 26, 2),
    ('physical', 31, 31, 2),
    ('spiritual', 31, 2, 2),
    ('spiritual', 31, 4, 2),
    ('spiritual', 31, 31, 2),

    ('mental',3, 15, 3),
    ('mental', 3, 18, 3),
    ('mental', 3, 21, 3),
    ('mental', 3, 14, 3),
    ('physical', 3, 27, 3),
    ('physical', 3, 30, 3),
    ('spiritual', 3, 3, 3),
    ('spiritual', 3, 6, 3),
    ('spiritual', 3, 9, 3),
    ('spiritual', 3, 12, 3),

    ('mental',6, 21, 3),
    ('mental', 6, 24, 3),
    ('mental', 6, 27, 3),
    ('mental', 6, 30, 3),
    ('physical', 6, 3, 3),
    ('physical', 6, 6, 3),
    ('spiritual', 6, 9, 3),
    ('spiritual', 6, 12, 3),
    ('spiritual', 6, 15, 3),
    ('spiritual', 6, 18, 3),

    ('mental',9, 27, 3),
    ('mental', 9, 30, 3),
    ('physical', 9, 3, 3),
    ('physical', 9, 6, 3),
    ('physical', 9, 9, 3),
    ('physical', 9, 12, 3),
    ('spiritual', 9, 15, 3),
    ('spiritual', 9, 18, 3),
    ('spiritual', 9, 21, 3),
    ('spiritual', 9, 24, 3),

    ('mental',12, 3, 3),
    ('mental', 12, 6, 3),
    ('physical', 12, 9, 3),
    ('physical', 12, 12, 3),
    ('physical', 12, 15, 3),
    ('physical', 12, 18, 3),
    ('spiritual', 12, 21, 3),
    ('spiritual', 12, 24, 3),
    ('spiritual', 12, 27, 3),
    ('spiritual', 12, 30, 3),

    ('mental',3, 15, 3),
    ('mental', 3, 18, 3),
    ('mental', 3, 21, 3),
    ('mental', 3, 14, 3),
    ('physical', 3, 27, 3),
    ('physical', 3, 30, 3),
    ('spiritual', 3, 3, 3),
    ('spiritual', 3, 6, 3),
    ('spiritual', 3, 9, 3),
    ('spiritual', 3, 12, 3),

    ('mental',15, 3, 3),
    ('mental', 15, 6, 3),
    ('mental', 15, 9, 3),
    ('mental', 15, 12, 3),
    ('physical', 15, 15, 3),
    ('physical', 15, 18, 3),
    ('physical', 15, 21, 3),
    ('physical', 15, 24, 3),
    ('spiritual', 15, 27, 3),
    ('spiritual', 15, 30, 3),

    ('mental',18, 18, 3),
    ('mental', 18, 21, 3),
    ('mental', 18, 24, 3),
    ('mental', 18, 27, 3),
    ('physical', 18, 3, 3),
    ('physical', 18, 30, 3),
    ('spiritual', 18, 6, 3),
    ('spiritual', 18, 9, 3),
    ('spiritual', 18, 12, 3),
    ('spiritual', 18, 15, 3),

    ('mental',21, 24, 3),
    ('mental', 21, 27, 3),
    ('mental', 21, 30, 3),
    ('physical', 21, 3, 3),
    ('physical', 21, 6, 3),
    ('physical', 21, 9, 3),
    ('spiritual', 21, 12, 3),
    ('spiritual', 21, 15, 3),
    ('spiritual', 21, 18, 3),
    ('spiritual', 21, 21, 3),

    ('mental',24, 3, 3),
    ('mental', 24, 30, 3),
    ('physical', 24, 6, 3),
    ('physical', 24, 9, 3),
    ('physical', 24, 12, 3),
    ('physical', 24, 15, 3),
    ('spiritual', 24, 18, 3),
    ('spiritual', 24, 21, 3),
    ('spiritual', 24, 24, 3),
    ('spiritual', 24, 27, 3),

    ('mental',27, 3, 3),
    ('mental', 27, 6, 3),
    ('mental', 27, 9, 3),
    ('physical', 27, 12, 3),
    ('physical', 27, 15, 3),
    ('physical', 27, 18, 3),
    ('physical', 27, 21, 3),
    ('spiritual', 27, 24, 3),
    ('spiritual', 27, 27, 3),
    ('spiritual', 27, 30, 3),

    ('mental',30, 6, 3),
    ('mental', 30, 9, 3),
    ('mental', 30, 12, 3),
    ('mental', 30, 15, 3),
    ('physical', 30, 18, 3),
    ('physical', 30, 21, 3),
    ('physical', 30, 24, 3),
    ('physical', 30, 27, 3),
    ('spiritual', 30, 3, 3),
    ('spiritual', 30, 30, 3),

INSERT INTO user_concord_group (concord_group_id, user_id) VALUES
	(1,1),
	(2,2);

INSERT INTO numerology (life_path_number, life_path_description, birthday_number, birthday_description, expression_number, 
	expression_description, personality_number, personality_description, soul_urge_number, soul_urge_description, lucky_number_month, lucky_number_day, 
	lucky_number_year, concord_group_id, user_id) VALUES
    (9, 'Path to greatness', 23, 'Birthday number 23', 9, 'Expression number 3', 9, 'Personality number 9', 6, 'Soul urge number 6', 3, 5, 1, 1, 1),
    (5, 'Master path', 26, 'Birthday number 26', 9, 'Expression number 9', 3, 'Personality number 3', 6, 'Soul urge number 6', 4, 8, 2, 2, 2);

--INSERT INTO user_categories (category_id, numerology_id) VALUES
--    (1, 1),
--    (2, 2);
