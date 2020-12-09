-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
SET GLOBAL time_zone = '+3:00';

DROP DATABASE IF EXISTS LIBRARY;
CREATE DATABASE LIBRARY;
USE LIBRARY;



CREATE TABLE category (
    code      int    PRIMARY KEY AUTO_INCREMENT,
    label     varchar(30)
);

CREATE TABLE books (
    idbook              int         PRIMARY KEY AUTO_INCREMENT,
    title               varchar(20) NOT NULL,
    isbn            	varchar(20) NOT NULL,
    releasedate			datetime	NOT NULL DEFAULT CURRENT_TIMESTAMP,
    registerdate		datetime	NOT NULL DEFAULT CURRENT_TIMESTAMP,
    totalexamplaries    int         NOT NULL DEFAULT 0,
    author              varchar(20) NOT NULL,
    cat_code			int         NOT NULL REFERENCES category(code)
);


INSERT INTO category(label) VALUES ("ACTION"),("SF"),("dada");
INSERT INTO books(title,isbn,totalexamplaries,author,cat_code) VALUES ("titleAction","dsaqhjkdsqjkhdfsq",50,"author","1"),("titleAction22","dsaqhjkdsqjkhdfsq",502,"author","2");


CREATE TABLE customers (
    id      int    	PRIMARY KEY AUTO_INCREMENT,
    firstname     	varchar(30),
    lastname 		varchar(30),
    job				varchar(30),
    address			varchar(30),
    email			varchar(30),
    creationdate	datetime	DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO customers(firstname, lastname) VALUES ("prenom","nom"),("prenom2","nom2"),("prenom3","nom3");

CREATE TABLE loans (
	bookid 				int NOT NULL REFERENCES books(idbook),
	customerid 			int	NOT NULL REFERENCES customers(id),
	creationdatetime 	datetime ,
	
	begindate			datetime DEFAULT CURRENT_TIMESTAMP,
	enddate				datetime ,
	status				varchar(10),
	
	PRIMARY KEY (bookid, customerid, creationdatetime)	
);

INSERT INTO loans(bookid, customerid, creationdatetime) VALUES ( 1,1,NOW()),( 1,2,NOW());


