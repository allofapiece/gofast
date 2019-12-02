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

insert into `vehicle` (`name`)
values ('car'),
       ('plane'),
       ('train');