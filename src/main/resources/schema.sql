create table if not exists customers
(
    id           serial primary key,
    name         char(128),
    surname      char(128),
    age          int,
    phone_number char(128) unique default '0'
);

create table if not exists orders
(
    id           serial primary key,
    date         date,
    customer_id  int,
    product_name char(255),
    amount       int not null,
    foreign key (customer_id) references customers (id)
);



