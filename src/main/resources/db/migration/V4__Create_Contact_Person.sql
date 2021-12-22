create sequence IF NOT EXISTS parking_lot_seq start with 1 increment by 1;
create table parking_lot
(
    id                  integer default nextval('parking_lot_seq') primary key,
    name                varchar(255) not null,
    email               varchar(255) not null,
    mobile_phone_number varchar(255),
    telephone_number    varchar(255),
    address             varchar(255) not null
)
