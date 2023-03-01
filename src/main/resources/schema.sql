create table token (
    token_id   int         not null AUTO_INCREMENT,
    identifier varchar(45) not null,
    token      varchar(255),
    primary key (token_id)
);
