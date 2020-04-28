drop table if exists `password`;
drop table if exists `point`;
drop table if exists `route`;
drop table if exists `user`;
drop table if exists `vehicle`;
drop table if exists `verification_token`;
drop table if exists `route_vehicle`;
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
create table `point`
(
    `id`         bigint not null auto_increment,
    `address`    varchar(255),
    `company_id` bigint,
    primary key (`id`)
) engine = MyISAM;
create table `route`
(
    `id`         bigint not null auto_increment,
    `company_id` bigint,
    `from_id`    bigint,
    `to_id`      bigint,
    primary key (`id`)
) engine = MyISAM;
create table `user`
(
    `dtype`      varchar(31) not null,
    `id`         bigint      not null auto_increment,
    `about`      longtext,
    `created_at` datetime,
    `email`      varchar(255),
    `is_company` bit         not null,
    `slug`       varchar(255),
    `status`     varchar(255),
    `updated_at` datetime,
    `first_name` varchar(255),
    `last_name`  varchar(255),
    `name`       varchar(255),
    primary key (`id`)
) engine = MyISAM;
create table `vehicle`
(
    `id`   bigint not null auto_increment,
    `name` varchar(255),
    `capacity` int(11),
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
create table `route_vehicle`
(
    `route_id`   bigint not null,
    `vehicle_id` bigint not null,
    primary key (`route_id`, `vehicle_id`)
) engine = MyISAM;
create table `user_role`
(
    `user_id` bigint not null,
    `roles`   varchar(255)
) engine = MyISAM;
alter table `password`
    add constraint `FKl66hk0u0yklus3ru7mkjdy050` foreign key (`user_id`) references `user` (`id`);
alter table `point`
    add constraint `FKk3pbsu89x1mwlsglmw2hlcjjq` foreign key (`company_id`) references `user` (`id`);
alter table `route`
    add constraint `FK3s565cwsbi9otgqxjecqq0nrp` foreign key (`company_id`) references `user` (`id`);
alter table `route`
    add constraint `FKjtxk69nsrgux0xkhqnxh1lyxm` foreign key (`from_id`) references `point` (`id`);
alter table `route`
    add constraint `FKd3el2537fqjgpw2misb7hruww` foreign key (`to_id`) references `point` (`id`);
alter table `verification_token`
    add constraint `FK5xj84jyghtm3fyraxx7frbe4v` foreign key (`user_id`) references `user` (`id`);
alter table `route_vehicle`
    add constraint `FKo4js8aktlfgookotq4w1vm8b3` foreign key (`vehicle_id`) references `vehicle` (`id`);
alter table `route_vehicle`
    add constraint `FK2fv24etgw0mf0ceabobxw6e38` foreign key (`route_id`) references `route` (`id`);
alter table `user_role`
    add constraint `FKhjx9nk20h4mo745tdqj8t8n9d` foreign key (`user_id`) references `user` (`id`);