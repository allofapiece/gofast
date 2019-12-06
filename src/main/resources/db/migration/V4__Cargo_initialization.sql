create table `cargo`
(
    `id`         bigint not null auto_increment,
    `name`       varchar(255),
    `price`      DECIMAL,
    primary key (`id`)
) engine = MyISAM;

INSERT INTO `cargo` (`id`, `name`, `price`)
VALUES (1, 'gold', 100),
       (2, 'iron', 20),
       (3, 'glass', 60);

create table `order`
(
    `id`         bigint not null auto_increment,
    `cargo_id`       bigint,
    `user_id`       bigint,
    `weight`      float,
    primary key (`id`)
) engine = MyISAM;


alter table `order`
    add constraint `osiepgsegh` foreign key (`cargo_id`) references `cargo` (`id`);
alter table `order`
    add constraint `osiepgsegsegh` foreign key (`user_id`) references `user` (`id`);