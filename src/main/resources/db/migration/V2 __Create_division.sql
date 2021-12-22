create sequence IF NOT EXISTS division_seq start with 1 increment by 1;
create table division{
    id integer default division_seq.nextval primary key,
    name varchar(255) not null ,
    original_name varchar(255) not null ,
    director varchar(255) not null
    }