create table `park_order`
(
   `id` long auto_increment primary key,
   `order_number` varchar(255) not null,
   `parkingLot_id` long ,
   `car_lisence` long ,
   `park_start_at` TIMESTAMP not null,
   `park_end_at` TIMESTAMP not null,
   `order_status` boolean not null
);