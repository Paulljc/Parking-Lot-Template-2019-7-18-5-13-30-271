create table `park_order`
(
   `id` long auto_increment primary key,
   `parking_lot_id` long ,
   `parking_lot_name` VARCHAR(255) NOT NULL,
   `car_lisence` VARCHAR(255) NOT NULL ,
   `park_start_at` TIMESTAMP not null,
   `park_end_at` TIMESTAMP not null,
   `status` boolean not null
);