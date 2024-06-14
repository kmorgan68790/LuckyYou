CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE categories (
	category_id SERIAL primary key,
	category_name varchar(50) 
);

CREATE TABLE user_categories (
	user_category_id SERIAL primary key,
	category_id INTEGER,
	constraint fk_category
    		foreign key (category_id)
            references categories (category_id)
);

CREATE TABLE zodiac (
	zodiac_id SERIAL primary key,
	zodiac_name VARCHAR (50),
	start_date DATE,
	end_date DATE,
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
	constraint fk_users_zodiac_id
    		foreign key (zodiac_id)
            references zodiac(zodiac_id)
);

CREATE TABLE concord_group (
	concord_group_id SERIAL primary key,
	concord_group_number INTEGER,
	concord_group_description TEXT,
	user_id INTEGER,
	constraint fk_concord_group_user_id
    		foreign key (user_id)
            references users (user_id)	

);

CREATE TABLE concord_days (
	concord_days_id SERIAL primary key,
	spiritual_day_number INTEGER,
	mental_day_number INTEGER,
	physical_day_number INTEGER,
	concord_group_id INTEGER,
	constraint fk_conord_days_concord_group_id
		foreign key (concord_group_id)
		references concord_group (concord_group_id)
);

CREATE TABLE numerology (
	numerology_id SERIAL primary key,
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
	user_id INTEGER,
	constraint fk_numerology_users_id
    		foreign key (user_id)
            references users (user_id)	
	);

CREATE TABLE categories_numerology (
	categories_numerology_id SERIAL primary key,
	categories_id INTEGER,
	numerology_id INTEGER,	
	constraint fk_categories_numerology_categories_id
    		foreign key (category_id)
            references categories (category_id),
	constraint fk_categories_numerology_id
    		foreign key (numerology_id)
            references numerology (numerology_id)
);	