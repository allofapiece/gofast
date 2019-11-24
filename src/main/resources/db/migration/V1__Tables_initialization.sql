drop table if exists `password`;
drop table if exists `user`;
drop table if exists `verification_token`;
drop table if exists `user_role`;
create table `password`
(
    `id`         bigint not null auto_increment,
    `created_at` datetime,
    `status`     varchar(255),
    `updated_at` datetime,
    `value`      varchar(255),
    `user_id`    bigint,
    primary key (`id`)
) engine = MyISAM;
create table `user`
(
    `id`           bigint      not null auto_increment,
    `dtype`        varchar(31) not null,
    `is_company`   bit         not null,
    `about`        longtext,
    `created_at`   datetime,
    `display_name` varchar(255),
    `email`        varchar(255),
    `first_name`   varchar(255),
    `last_name`    varchar(255),
    `name`         varchar(255),
    `slug`         varchar(255),
    `status`       varchar(255),
    `updated_at`   datetime,
    primary key (`id`)
) engine = MyISAM;
create table `verification_token`
(
    `id`      bigint not null auto_increment,
    `expire`  datetime,
    `token`   varchar(255),
    `user_id` bigint,
    primary key (`id`)
) engine = MyISAM;

create table `user_role`
(
    `user_id` bigint not null,
    `roles`   varchar(255)
) engine = MyISAM;

alter table `password`
    add constraint `FKl66hk0u0yklus3ru7mkjdy050` foreign key (`user_id`) references `user` (`id`);
alter table `verification_token`
    add constraint `FK5xj84jyghtm3fyraxx7frbe4v` foreign key (`user_id`) references `user` (`id`);
alter table `user_role`
    add constraint `FKhjx9nk20h4mo745tdqj8t8n9d` foreign key (`user_id`) references `user` (`id`);
