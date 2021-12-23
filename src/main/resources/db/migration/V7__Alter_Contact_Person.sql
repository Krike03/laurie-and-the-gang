alter table contact_person
drop
column name;

alter table contact_person
    add column first_name varchar(255);

alter table contact_person
    add column last_name varchar(255);