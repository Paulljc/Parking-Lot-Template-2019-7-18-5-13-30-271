create table `parking_lot`
(
   `id` long auto_increment primary key,
   `name` varchar(255) not null,
   `location` varchar(255) not null,
   `capacity` INT CHECK(capacity > 0),
);