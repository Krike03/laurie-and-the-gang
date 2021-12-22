create sequence IF NOT EXISTS contact_person_seq start with 1 increment by 1;
create table contact_person
(
    id                  integer default nextval('contact_person_seq') primary key,
    name                varchar(255) not null,
    email               varchar(255) not null,
    mobile_phone_number varchar(255),
    telephone_number    varchar(255),
    address             varchar(255) not null
)
