alter table contact_person
drop
column address;

alter table contact_person
    add column street_number varchar(255);

alter table contact_person
    add column street_name varchar(255);

alter table contact_person
    add column numeral_code varchar(255);

alter table contact_person
    add column city_label varchar(255);
