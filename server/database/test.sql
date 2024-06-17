DROP SCHEMA IF EXISTS test CASCADE;

CREATE SCHEMA IF NOT EXISTS test;

SET search_path TO production;

CREATE TABLE categories (
	category_id SERIAL primary key,
	category_name varchar(50) 
);

CREATE TABLE user_categories (
	user_category_id SERIAL primary key,
	category_id INTEGER,
	constraint fk_user_categories_category
    		foreign key (category_id)
            references categories (category_id)
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
	user_name VARCHAR (50) unique not null,
	user_password VARCHAR(250) not null,
	email VARCHAR(100) unique not null,
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
	concord_group_id INTEGER,
	constraint fk_conord_days_concord_group
		foreign key (concord_group_id)
		references concord_group (concord_group_id)
);

CREATE TABLE user_concord_group (
	user_concord_group_id SERIAL primary key,
	concord_group_id INTEGER,
	user_id INTEGER,
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

-- Create function to set known good state
CREATE OR REPLACE FUNCTION set_known_good_state()
RETURNS VOID AS $$
BEGIN
-- Delete existing data from tables (if needed)
	DELETE FROM categories_numerology;
    DELETE FROM user_categories;
    DELETE FROM numerology;
	DELETE FROM user_concord_group;
	DELETE FROM users;
	DELETE FROM concord_days;
	DELETE FROM concord_group;
    DELETE FROM zodiac;
    DELETE FROM categories;

INSERT INTO categories (category_name) VALUES
    ('Love'),
    ('Health'),
    ('Money'),
    ('Career'),
    ('Life Purpose');

INSERT INTO zodiac (zodiac_name, start_date, end_date, zodiac_description) VALUES
    ('Aries', '2023-03-21', '2023-04-19', 'The first sign of the zodiac'),
    ('Taurus', '2023-04-20', '2023-05-20', 'The second sign of the zodiac');

INSERT INTO users (user_name, user_password, email, first_name, middle_name, last_name, dob, zodiac_id) VALUES
    ('user', 'password', 'test@test.com', 'First', 'Middle', 'Last', '1990-03-23', 1),
    ('user2', 'password', 'test2@test.com', 'First2', 'Middle2', 'Last2', '1991-04-26', 2);
	('user3', 'password', 'test3@test.com', 'First3', 'Middle3', 'Last3', '1991-04-26', 2);

INSERT INTO concord_group (concord_group_number, concord_group_description) VALUES
    (1, 'Group 1 Description'),
    (2, 'Group 2 Description'),
	(3, 'Group 2 Description');

INSERT INTO concord_days (day_type, day_number, concord_group_id) VALUES
	('spiritual', 14, 1),
	('spiritual', 16, 1),
	('spiritual', 19, 1),
	('spiritual', 23, 1),
	('mental', 1, 1),
	('mental', 25, 1),
	('mental', 28, 1),
	('physical', 5, 1),
	('physical', 7, 1),
	('physical', 10, 1),
	('spiritual', 22, 2),
	('spiritual', 26, 2),
	('spiritual', 29, 2),
	('spiritual', 31, 2),
	('mental', 2, 2),
	('mental', 4, 2),
	('mental', 8, 2),
	('physical', 11, 2),
	('physical', 13, 2),
	('physical', 17, 2),
	('physical', 20, 2);

INSERT INTO user_concord_group (concord_group_id, user_id) VALUES
	(1,1),
	(2,2);

INSERT INTO numerology (life_path_number, life_path_description, birthday_number, birthday_description, expression_number, 
	expression_description, personality_number, personality_description, soul_urge_number, soul_urge_description, lucky_number_month, lucky_number_day, 
	lucky_number_year, concord_group_id, user_id) VALUES
    (9, 'Path to greatness', 23, 'Birthday number 23', 9, 'Expression number 3', 9, 'Personality number 9', 6, 'Soul urge number 6', 3, 5, 1, 1, 1),
    (5, 'Master path', 26, 'Birthday number 26', 9, 'Expression number 9', 3, 'Personality number 3', 6, 'Soul urge number 6', 4, 8, 2, 2, 2);

INSERT INTO categories_numerology (category_id, numerology_id) VALUES
    (1, 1),
    (2, 2);

-- Commit the transaction
-- COMMIT;
END;
$$ LANGUAGE plpgsql;