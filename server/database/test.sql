CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE test.categories (
	category_id SERIAL primary key,
	category_name varchar(50) 
);

CREATE TABLE test.user_categories (
	user_category_id SERIAL primary key,
	category_id INTEGER,
	constraint fk_category
    		foreign key (category_id)
            references test.categories (category_id)
);

CREATE TABLE test.zodiac (
	zodiac_id SERIAL primary key,
	zodiac_name VARCHAR (50),
	start_date DATE,
	end_date DATE,
	zodiac_description TEXT
);


CREATE TABLE test.users (
	user_id SERIAL primary key,
	user_name VARCHAR (50) unique not null,
	user_password VARCHAR(250) not null,
	email VARCHAR(100) unique not null,
	first_name VARCHAR(255) not null,
	middle_name VARCHAR(255),
	last_name VARCHAR(255) not null,
	dob DATE not null,
	zodiac_id INTEGER,
	constraint fk_zodiac
    		foreign key (zodiac_id)
            references test.zodiac(zodiac_id)
);

CREATE TABLE test.numerology (
	numerology_id SERIAL primary key,
	concord_group INTEGER,
	concord_group_description TEXT,
	lucky_day_number INTEGER,
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
	category_id INTEGER,
	user_id INTEGER,
	constraint fk_numerology_category
    		foreign key (category_id)
            references test.categories (category_id),
	constraint fk_numerology_users
    		foreign key (user_id)
            references test.users (user_id)
	);