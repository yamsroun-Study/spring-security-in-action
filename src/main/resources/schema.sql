create schema if not exists ssia11;

create table if not exists ssia11.member (
    username varchar(45) null,
    password text null,
    primary key (username)
);

create table if not exists ssia11.member_otp (
    username varchar(45) not null,
    code varchar(45) null,
    primary key (username)
);
