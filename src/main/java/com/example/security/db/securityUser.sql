CREATE DATABASE sbbs;
USE sbbs;

CREATE TABLE securityUser(
	sid int PRIMARY KEY auto_increment,
	uid varchar(64) NOT NULL UNIQUE,
	pwd char(60),
	uname varchar(40),
	email varchar(64),
	provider varchar(16) NOT NULL,
	regDate date DEFAULT (CURRENT_DATE),
	role varchar(16) DEFAULT 'ROLE_USER' NOT NULL,
	isDeleted int DEFAULT 0,
	profile varchar(40),
	github varchar(40),
	insta varchar(40),
	location varchar(20)
);