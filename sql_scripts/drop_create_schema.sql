DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS _group;

CREATE TABLE _group
(
    id SERIAL PRIMARY KEY,
    year INTEGER
);

CREATE TABLE course
(
    id SERIAL PRIMARY KEY,
    hours INTEGER,
    name VARCHAR(255),
    group_id BIGINT REFERENCES _group
);

CREATE TABLE student
(
    id SERIAL PRIMARY KEY,
    dateofbirth DATE,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    group_id BIGINT REFERENCES _group
);

