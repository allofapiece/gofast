INSERT INTO `user` (`id`, `dtype`, `is_company`, `email`, `name`, `first_name`, `last_name`, `slug`, `status`, `created_at`, `updated_at`)
VALUES (1, 'Client', 0, 'client@gmail.com', null, 'Stas', 'Listratenko', 'anabel-pinwheel', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 'Company', 1, 'company@gmail.com', 'Atlansis', null, null, 'anabel-pinwheel', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `password` (`id`, `user_id`, `value`, `status`, `created_at`)
VALUES (1, 1, '$2a$08$X5pFdPxOBbiqPiYoTmyn3O32y/6B/78fMwsBe1ilsQ3K3gzlL0S8e', 'ACTIVE', CURRENT_TIMESTAMP),
       (2, 2, '$2a$08$X5pFdPxOBbiqPiYoTmyn3O32y/6B/78fMwsBe1ilsQ3K3gzlL0S8e', 'ACTIVE', CURRENT_TIMESTAMP);

INSERT INTO `user_role` (`user_id`, `roles`)
VALUES (2, 'USER'),
       (2, 'ADMIN'),
       (1, 'USER');

insert into `vehicle` (`name`, `capacity`)
values ('car', 400),
       ('plane', 5000),
       ('train', 3000);


insert into `point` (`id`, `company_id`, `address`) values
(1, 2, '230 Douglas Ave'),
(2, 2, '60 Masons Landing'),
(3, 2, '2665 State Hwy 501'),
(4, 2, 'E Plum St'),
(5, 2, 'Boardwalk Cir');

insert into `route` (`company_id`, `from_id`, `to_id`) values
(2, 1, 2),
(2, 2, 3),
(2, 2, 4),
(2, 4, 5),
(2, 2, 5);

insert into `route_vehicle` (`route_id`, `vehicle_id`) values
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1);
