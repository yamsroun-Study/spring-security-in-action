create table if not exists `member` (
    `member_id` int not null auto_increment,
    `username`  varchar(45) not null,
    `password`  varchar(45) null,
    `enabled`   int         not null,
    primary key (`member_id`)
);

alter table member
    add constraint uk_member_username unique (username);

create table if not exists `member_authority` (
    `auth_id`   int not null auto_increment,
    `member_id` int not null,
    `member_role` varchar(45) null,
    primary key (`auth_id`)
);

create table csrf_token (
    session_id varchar(255) not null,
    token      varchar(255),
    primary key (session_id)
);
