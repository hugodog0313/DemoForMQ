CREATE TABLE users (
	id varchar NOT NULL,
	"name" varchar(30) NOT NULL,
	tel varchar NOT NULL,
	age int4 NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);