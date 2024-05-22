CREATE TABLE `role`
(
    `id`   varchar(255) NOT NULL,
    `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `role` (`id`, `name`)
VALUES ('74b8e45a-1760-11ef-a250-0242ac130002', 'ROLE_ADMIN'),
       ('74b8eed4-1760-11ef-a250-0242ac130002', 'ROLE_USER');
CREATE TABLE `users`
(
    `id`            varchar(255) NOT NULL,
    `date_of_birth` varchar(50)  NOT NULL,
    `email`         varchar(50)  NOT NULL,
    `first_name`    varchar(50)  NOT NULL,
    `last_name`     varchar(50)  NOT NULL,
    `password`      varchar(255) DEFAULT NULL,
    `username`      varchar(50)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `users` (`id`, `date_of_birth`, `email`, `first_name`, `last_name`, `password`, `username`)
VALUES ('a32c2455-98ce-4a22-85cf-c291d2c1ffbc', '1994-07-24', 'sushantshiwakoti@gmail.com', 'Sushant', 'shiwakoti',
        '$2a$10$24r.0MtNCid7H.1tfzgHreZYvIJv00i143tFQnigUn.HAswDz93gO', 'sushant123'),
       ('feef079f-2133-46bb-a8bf-766d9c656fe6', '2024-09-90', 'test@gmail.com', 'test', 'test',
        '$2a$10$wJ9hRM33PH5t6UQb86bwy.pV8nlyEFLpt1ERKOf6guEU1goCsZWc6', 'test11');
CREATE TABLE `user_role`
(
    `user_id` varchar(255) NOT NULL,
    `role_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES ('a32c2455-98ce-4a22-85cf-c291d2c1ffbc', '74b8e45a-1760-11ef-a250-0242ac130002'),
       ('feef079f-2133-46bb-a8bf-766d9c656fe6', '74b8eed4-1760-11ef-a250-0242ac130002');

ALTER TABLE `role`
    ADD PRIMARY KEY (`id`);
ALTER TABLE `users`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_1vj6kbtboiaeen9hvg7f9v8bk` (`date_of_birth`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`);

ALTER TABLE `user_role`
    ADD PRIMARY KEY (`user_id`, `role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

ALTER TABLE `user_role`
    ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
