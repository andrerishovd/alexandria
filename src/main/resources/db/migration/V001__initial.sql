create table if not exists author(
    author_id bigint primary key,
    last_name varchar(100) not null,
    age int not null
);

create sequence author_seq;

create table if not exists book(
    book_id bigint primary key,
    title varchar(100) not null,
    publisher varchar(150),
    location_id bigint,
    status varchar(20)
);

create sequence book_seq;

create table if not exists location(
    location_id bigint primary key,
    shelf char not null,
    location_row int not null
);

create sequence location_seq;

create table if not exists book_author(
    book_id bigint,
    author_id bigint
);