CREATE DATABASE bd_ferregestion;

CREATE TABLE `clients` (
  `id` integer PRIMARY KEY,
  `name` varchar(50),
  `phone` integer,
  `address` varchar(50),
  `email` varchar(50)
);

CREATE TABLE `employee` (
  `id` integer PRIMARY KEY,
  `name` varchar(50),
  `charge` varchar(50),
  `wage` integer
);

CREATE TABLE `supplier` (
  `id` integer PRIMARY KEY,
  `name` varchar(50),
  `products_supplied` varchar(50)
);

CREATE TABLE `products` (
  `id` integer PRIMARY KEY,
  `name` varchar(50),
  `category` varchar(50),
  `price` integer,
  `quantity_available` integer,
  `id_supplier` integer
);

CREATE TABLE `orders` (
  `id` integer PRIMARY KEY,
  `date` date,
  `id_client` integer,
  `id_employee` integer,
  `id_product` integer,
  `total` integer,
  `status` varchar(50)
);

CREATE TABLE `sales` (
  `id` integer PRIMARY KEY,
  `date` date,
  `id_client` integer,
  `id_employee` integer
);

ALTER TABLE `products` ADD FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`id_client`) REFERENCES `clients` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`id_product`) REFERENCES `products` (`id`);

ALTER TABLE `sales` ADD FOREIGN KEY (`id_client`) REFERENCES `clients` (`id`);

ALTER TABLE `sales` ADD FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`);
