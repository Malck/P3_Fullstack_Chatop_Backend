DROP TABLE IF EXISTS RENTALS;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS MESSAGES;

CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `RENTALS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `MESSAGES` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

ALTER TABLE `RENTALS` ADD FOREIGN KEY (`owner_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`rental_id`) REFERENCES `RENTALS` (`id`);

INSERT INTO USERS (email, name, password)
 VALUES ('admin@admin.fr','admin', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC'),
('hugo@test.com', 'test TEST', 'hugo!31');


INSERT INTO RENTALS (id, name, surface, price, picture, description, owner_id)
VALUES (1, 'Maison Pierre', 45, 5, 'Files/maisons-pierre.jpg', 'Cette maison chaleureuse est entouree d un petit jardin fleuri et dispose d un toit en tuiles rouges.', 1),
       (5, 'Villa almeida', 55, 36, 'Files/maison-moderne.jpg', 'Cette villa luxueuse est nichee sur une colline offrant une vue panoramique sur la mer Mediterranee. Elle est dotee d une piscine a debordement de vastes terrasses et de jardins paysagers exquis.', 2);
