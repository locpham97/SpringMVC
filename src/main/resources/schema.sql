DROP DATABASE IF EXISTS bmspring;

CREATE DATABASE bmspring;

CREATE TABLE Author
(
    author_id SERIAL       NOT NUll,
    name      varchar(255) NOT NULL,
    PRIMARY KEY (author_id)
);

CREATE TABLE Book
(
    book_id     SERIAL       NOT NUll,
    name        varchar(255) NOT NULL,
    category_id SERIAL       NOT NULL
);

create index FKam9riv8y6rjwkua1gapdfew4j
    on book (category_id);

create table books_authors
(
    author_id SERIAL not null,
    book_id   SERIAL not null,
    PRIMARY KEY (author_id, book_id)
);

create index FK6ojkw2gy23xsgdkqih628favt
    on books_authors (book_id);

create table category
(
    category_id SERIAL not null
        primary key,
    name        varchar(255) null
);