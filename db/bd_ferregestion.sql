-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.42 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para bd_ferregestion
CREATE DATABASE IF NOT EXISTS `bd_ferregestion` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_ferregestion`;

-- Volcando estructura para tabla bd_ferregestion.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(200) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `document` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKs0ydiqwgl7l9b8ripxjdvd2xb` (`document`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla bd_ferregestion.employees
CREATE TABLE IF NOT EXISTS `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `document` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKo75l60nkejlmtftfww06pxk1y` (`document`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla bd_ferregestion.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(50) DEFAULT NULL,
  `code` varchar(50) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `min_stock` int DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `price` int NOT NULL,
  `stock` int NOT NULL,
  `supplier_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK57ivhy5aj3qfmdvl6vxdfjs4p` (`code`),
  KEY `FK6i174ixi9087gcvvut45em7fd` (`supplier_id`),
  CONSTRAINT `FK6i174ixi9087gcvvut45em7fd` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla bd_ferregestion.sales
CREATE TABLE IF NOT EXISTS `sales` (
  `id` int NOT NULL AUTO_INCREMENT,
  `notes` varchar(200) DEFAULT NULL,
  `sale_date` datetime(6) DEFAULT NULL,
  `status` enum('CANCELLED','COMPLETED','PENDING') NOT NULL,
  `total` int NOT NULL,
  `client_id` int DEFAULT NULL,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbbif9cb3ecyusyms54yvwlhd5` (`client_id`),
  KEY `FK2jitm3kpqyikxeo6s0a37v99j` (`employee_id`),
  CONSTRAINT `FK2jitm3kpqyikxeo6s0a37v99j` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `FKbbif9cb3ecyusyms54yvwlhd5` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla bd_ferregestion.sale_details
CREATE TABLE IF NOT EXISTS `sale_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `subtotal` int NOT NULL,
  `unit_price` int NOT NULL,
  `product_id` int NOT NULL,
  `sale_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqvh82ispfukxa2yssnok0m12o` (`product_id`),
  KEY `FK6nruj5m7ntwhc29etigqnlk0m` (`sale_id`),
  CONSTRAINT `FK6nruj5m7ntwhc29etigqnlk0m` FOREIGN KEY (`sale_id`) REFERENCES `sales` (`id`),
  CONSTRAINT `FKqvh82ispfukxa2yssnok0m12o` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla bd_ferregestion.suppliers
CREATE TABLE IF NOT EXISTS `suppliers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(200) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
