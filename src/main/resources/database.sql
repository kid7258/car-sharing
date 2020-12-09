create table MEMBER
(
    id          bigint auto_increment,
    email       varchar(255),
    name        varchar(255),
    password    varchar(255),
    role        varchar(10),
    create_ymdt timestamp,
    last_mod_ymdt timestamp
);

create table BOARD
(
    id           bigint auto_increment,
    member_id    bigint,
    title        varchar(255),
    content      CLOB,
    create_ymdt timestamp,
    last_mod_ymdt timestamp
);

