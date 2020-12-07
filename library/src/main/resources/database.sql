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
    cat_code			int         UNIQUE NOT NULL REFERENCES category(code)
);


INSERT INTO category(label) VALUES ("ACTION"),("SF"),("dada");
INSERT INTO books(title,isbn,totalexamplaries,author,cat_code) VALUES ("titleAction","dsaqhjkdsqjkhdfsq",50,"author","1"),("titleAction22","dsaqhjkdsqjkhdfsq",502,"author","2");


