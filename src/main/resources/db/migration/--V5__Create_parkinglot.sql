create sequence IF NOT EXISTS parking_lot_seq start with 1 increment by 1;
create table parking_lot
(
    id            integer default nextval('parking_lot_seq') primary key,
    name          varchar(255) not null,
    category      varchar(255) not null,
    capacity      integer not null,
    contact_person integer not null,
    street_name varchar(255) not null,
    street_number varchar(255) not null,
    numeral_code varchar(255) not null,
    city_label varchar(255) not null,
    price_per_hour float not null
)
