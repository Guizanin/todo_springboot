CREATE TABLE todos (
    id bigint not null auto_increment primary key,
    title varchar(100) not null,
    description varchar(250),
    checked boolean not null
);