create sequence IF NOT EXISTS member_seq start with 1 increment by 1;
create table member (
    id integer default nextval('member_seq') primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    street_name varchar(255),
    street_number varchar(255),
    numeral_code varchar(255),
    city_label varchar(255),
    phone_number varchar (255),
    email varchar(255),
    license_number varchar(255),
    country_label varchar(255),
    registration_date date
)