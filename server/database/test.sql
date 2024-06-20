DROP SCHEMA IF EXISTS test CASCADE;

CREATE SCHEMA IF NOT EXISTS test;

SET search_path TO test;

CREATE TABLE test.zodiac (
	zodiac_id SERIAL primary key,
	zodiac_name VARCHAR (50),
	start_dates DATE,
	end_dates DATE,
	zodiac_description TEXT
);

CREATE TABLE test.concord_group (
	concord_group_id SERIAL primary key,
	concord_group_number INTEGER,
	concord_group_description TEXT
);

CREATE TABLE numerology_description (
	numerology_description_id SERIAL primary key,
	numerology_type INTEGER,
	numerology_description TEXT,
	numerology_number INTEGER
);

CREATE TABLE test.users (
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
	constraint fk_users_zodiac
    	foreign key (zodiac_id)
        references test.zodiac(zodiac_id),
    constraint fk_users_concord_group
        foreign key (concord_group_id)
        references test.concord_group (concord_group_id)
);

CREATE TABLE user_numerology_mapping (
    user_numerology_mapping_id SERIAL primary key,
    user_id INTEGER,
    numerology_type VARCHAR(50),
    numerology_description_id INTEGER,
    constraint fk_user_numerology_mapping_user
        foreign key (user_id)
        references users (user_id),
    constraint fk_user_numerology_mapping_description
        foreign key (numerology_description_id)
        references numerology_description (numerology_description_id)
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

