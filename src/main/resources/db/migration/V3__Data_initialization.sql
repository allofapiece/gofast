INSERT INTO `user_role` (`user_id`, `roles`)
VALUES (1, 'USER'),
       (1, 'ADMIN');

INSERT INTO `user` (`id`, `dtype`, `email`, `name`, `first_name`, `last_name`, `slug`, `status`, `created_at`, `updated_at`)
VALUES (1, 'Client', 'client@gmail.com', null, 'Stas', 'Listratenko', 'anabel-pinwheel', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 'Company', 'company@gmail.com', 'Atlansis', null, null, 'anabel-pinwheel', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `password` (`id`, `user_id`, `value`, `status`, `created_at`)
VALUES (1, 1, '$2a$08$X5pFdPxOBbiqPiYoTmyn3O32y/6B/78fMwsBe1ilsQ3K3gzlL0S8e', 'ACTIVE', CURRENT_TIMESTAMP),
       (2, 2, '$2a$08$X5pFdPxOBbiqPiYoTmyn3O32y/6B/78fMwsBe1ilsQ3K3gzlL0S8e', 'ACTIVE', CURRENT_TIMESTAMP);

insert into `vehicle` (`name`)
values ('car'),
       ('plane'),
       ('train');