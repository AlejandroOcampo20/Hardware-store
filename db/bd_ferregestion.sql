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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_ferregestion.clients: ~3 rows (aproximadamente)
REPLACE INTO `clients` (`id`, `address`, `created_at`, `document`, `email`, `is_active`, `name`, `phone`) VALUES
	(1, 'Calle Sur 101, Col. Centro', '2025-08-10 22:23:45.000000', '12345678', 'carlos.mendez@email.com', b'1', 'Carlos Méndez', '5551122334'),
	(2, 'Av. Reforma 202, Col. Moderna', '2025-08-10 22:23:45.000000', '87654321', 'lucia.ramirez@email.com', b'1', 'Lucía Ramírez', '5554433221'),
	(3, 'Carretera Nacional 303, Toluca', '2025-08-10 22:23:45.000000', '98765432', 'ventas@construvalle.com', b'1', 'Constructora del Valle S.A.', '5557788990'),
	(4, 'Calle 10 #45, Zapopan', '2025-08-12 12:43:37.000000', '11111111', 'carlos.martinez@mail.com', b'1', 'Carlos Martínez', '5551112233'),
	(5, 'Av. Reforma 202, Guadalajara', '2025-08-12 12:43:37.000000', '22222222', 'lucia.gomez@mail.com', b'1', 'Lucía Gómez', '5552223344'),
	(6, 'Blvd. Norte 88, Tlajomulco', '2025-08-12 12:43:37.000000', '33333333', 'miguel.rios@mail.com', b'1', 'Miguel Ríos', '5553334455'),
	(7, 'Calle Sur 301, Tonalá', '2025-08-12 12:43:37.000000', '44444444', 'patricia.ortiz@mail.com', b'1', 'Patricia Ortiz', '5554445566'),
	(8, 'Calle Real 505, El Salto', '2025-08-12 12:43:37.000000', '55555555', 'rosario.lopez@mail.com', b'1', 'Rosario López', '5555556677');

-- Volcando estructura para tabla bd_ferregestion.employees
CREATE TABLE IF NOT EXISTS `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `document` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL COMMENT '1 es activo y 2 inactivo',
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKo75l60nkejlmtftfww06pxk1y` (`document`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_ferregestion.employees: ~3 rows (aproximadamente)
REPLACE INTO `employees` (`id`, `created_at`, `document`, `email`, `is_active`, `name`, `phone`, `position`) VALUES
	(1, '2025-08-10 22:24:23.000000', '11223344', 'ana.lopez@ferregestion.com', b'1', 'Ana López', '5551010101', 'Vendedor'),
	(2, '2025-08-10 22:24:23.000000', '22334455', 'jorge.perez@ferregestion.com', b'1', 'Jorge Pérez', '5552020202', 'Encargado de Almacén'),
	(3, '2025-08-10 22:24:23.000000', '33445566', 'maria.gonzalez@ferregestion.com', b'1', 'María González', '5553030303', 'Supervisor de Ventas'),
	(4, '2025-08-12 12:43:37.000000', '10101010', 'admin@ferre.com', b'1', 'Administrador General', '5550001001', 'ADMINISTRADOR'),
	(5, '2025-08-12 12:43:37.000000', '20202020', 'ana.lopez@ferre.com', b'1', 'Ana López', '5550002002', 'Vendedor'),
	(6, '2025-08-12 12:43:37.000000', '30303030', 'jorge.perez@ferre.com', b'1', 'Jorge Pérez', '5550003003', 'Encargado Almacén'),
	(7, '2025-08-12 12:43:37.000000', '40404040', 'maria.gonzalez@ferre.com', b'1', 'María González', '5550004004', 'Supervisor Ventas'),
	(8, '2025-08-12 12:43:37.000000', '50505050', 'luis.morales@ferre.com', b'1', 'Luis Morales', '5550005005', 'Cajero');

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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_ferregestion.products: ~6 rows (aproximadamente)
REPLACE INTO `products` (`id`, `category`, `code`, `created_at`, `description`, `is_active`, `min_stock`, `name`, `price`, `stock`, `supplier_id`) VALUES
	(1, 'Herramientas Manuales', 'MTL-001', '2025-08-10 22:23:01.000000', 'Martillo de carpintero con mango de fibra de vidrio', b'1', 10, 'Martillo de acero 16 oz', 250, 12, 1),
	(2, 'Herramientas Eléctricas', 'TLD-002', '2025-08-10 22:23:01.000000', 'Taladro inalámbrico con batería recargable', b'1', 5, 'Taladro eléctrico 12V', 1800, 1, 2),
	(3, 'Fijaciones', 'CLV-003', '2025-08-10 22:23:01.000000', 'Clavos galvanizados, caja de 1 kg', b'1', 30, 'Clavos de 2 pulgadas', 80, 147, 1),
	(4, 'Herramientas Eléctricas', 'SRR-004', '2025-08-10 22:23:01.000000', 'Sierra eléctrica para corte de madera', b'1', 3, 'Sierra circular de 7.25"', 3200, 0, 2),
	(5, 'Tuberías', 'TUB-005', '2025-08-10 22:23:01.000000', 'Tubo PVC para instalaciones hidráulicas, 6 metros', b'1', 20, 'Tubo PVC 1/2 pulgada', 120, 53, 3),
	(11, 'Herramientas', 'hlf-004', '2025-08-11 21:04:00.000000', 'herramientas generales', b'1', 30, 'zapatos', 100000, 35, 3),
	(32, 'Herramientas Manuales', 'MART-001', '2025-08-12 12:43:37.000000', 'Martillo de garra de 20 oz con mango de fibra de vidrio', b'1', 5, 'Martillo 20 oz Fibra', 290, 30, 1),
	(33, 'Herramientas Eléctricas', 'TAL-002', '2025-08-12 12:43:37.000000', 'Taladro inalámbrico con batería 18 V y cargador rápido', b'1', 3, 'Taladro Percutor 18V', 2100, 15, 2),
	(34, 'Fijaciones', 'CLAV-003', '2025-08-12 12:43:37.000000', 'Caja 1 kg clavos 2" galvanizados para madera', b'1', 30, 'Clavos Galvanizados 2"', 95, 200, 5),
	(35, 'Herramientas Eléctricas', 'SIER-004', '2025-08-12 12:43:37.000000', 'Sierra eléctrica potente 1500 W, 7¼ pulgadas', b'1', 2, 'Sierra Circular 7¼"', 3400, 10, 2),
	(36, 'Tuberías', 'TdUB-005', '2025-08-12 12:43:37.000000', 'Tubo PVC presión ½ pulgada x 6 metros', b'1', 15, 'Tubo PVC ½" 6 m', 135, 89, 6),
	(37, 'Herramientas Manuales', 'DES-006', '2025-08-12 12:43:37.000000', 'Destornillador estrella 100 mm mango ergonómico', b'1', 20, 'Destornillador Cruz #2', 120, 150, 1),
	(38, 'Herramientas Manuales', 'LLE-007', '2025-08-12 12:43:37.000000', 'Llave ajustable 10 pulgadas acero cromado', b'1', 8, 'Llave Inglesa 10"', 280, 50, 1),
	(39, 'Accesorios', 'BRO-008', '2025-08-12 12:43:37.000000', 'Broca helicoidal 5 mm para metal y madera', b'1', 40, 'Broca 5 mm HSS', 45, 300, 4),
	(40, 'Medición', 'CIN-009', '2025-08-12 12:43:37.000000', 'Cinta 5 m con traba automática y cinta de acero', b'1', 10, 'Cinta Métrica 5 m', 90, 80, 3),
	(41, 'Herramientas Eléctricas', 'SOP-010', '2025-08-12 12:43:37.000000', 'Soplete portátil para soldadura blanda', b'1', 5, 'Soplete Gas 500 ml', 750, 25, 3),
	(42, 'Abrasivos', 'LIJ-011', '2025-08-12 12:43:37.000000', 'Paquete 10 hojas lija grano 80 para madera y metal', b'1', 50, 'Lija Papel 80 G', 60, 500, 4),
	(43, 'Materiales', 'CEM-012', '2025-08-12 12:43:37.000000', 'Cemento Portland grado 50 kg para construcción', b'1', 10, 'Cemento Gris 50 kg', 2900, 45, 6),
	(44, 'Medición', 'ESC-013', '2025-08-12 12:43:37.000000', 'Escuadra de acero inoxidable 30 cm', b'1', 12, 'Escuadra 30 cm', 150, 60, 5),
	(45, 'Pinturas', 'PIN-014', '2025-08-12 12:43:37.000000', 'Pintura látex lavable blanca empaque 19 litros', b'1', 5, 'Pintura Látex Blanca 19 L', 1800, 20, 4),
	(46, 'Accesorios', 'ROD-015', '2025-08-12 12:43:37.000000', 'Rodillo para pintura 4 pulgadas mango plástico', b'1', 15, 'Rodillo 4"', 120, 100, 5);

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_ferregestion.sales: ~13 rows (aproximadamente)
REPLACE INTO `sales` (`id`, `notes`, `sale_date`, `status`, `total`, `client_id`, `employee_id`) VALUES
	(1, 'Venta en efectivo', '2025-08-10 22:24:45.000000', 'CANCELLED', 530, 1, 1),
	(2, 'Compra con tarjeta', '2025-08-09 22:24:45.000000', 'COMPLETED', 1880, 2, 1),
	(3, 'Pedido para obra, entrega pendiente', '2025-08-08 22:24:45.000000', 'PENDING', 3320, 3, 3),
	(4, 'Cancelado por falta de stock', '2025-08-07 22:24:45.000000', 'CANCELLED', 0, 1, 2),
	(5, 'gracias por tu venta', '2025-08-10 22:27:26.174905', 'COMPLETED', 1640, 3, 3),
	(6, '', '2025-08-10 22:28:31.165339', 'COMPLETED', 18000, 1, 1),
	(7, 'hola', '2025-08-10 22:31:32.274661', 'COMPLETED', 660, NULL, 1),
	(8, '', '2025-08-11 07:10:09.326808', 'COMPLETED', 21600, 3, 1),
	(9, 'hola perros', '2025-08-11 08:13:51.730796', 'COMPLETED', 1960, 2, 2),
	(10, '', '2025-08-11 10:40:28.194704', 'COMPLETED', 960, 1, 1),
	(11, '', '2025-08-11 12:37:16.490837', 'COMPLETED', 3360, 2, 2),
	(12, '', '2025-08-11 17:19:22.245350', 'COMPLETED', 3000, NULL, 2),
	(13, '', '2025-08-11 17:56:11.704882', 'COMPLETED', 3000, 1, 1),
	(14, '', '2025-08-12 10:12:53.657792', 'COMPLETED', 41400, 1, 1),
	(15, '', '2025-08-12 10:17:34.305705', 'COMPLETED', 1800, 1, 1),
	(16, '', '2025-08-12 13:16:31.355214', 'COMPLETED', 400, 5, 1),
	(17, '', '2025-08-12 15:10:55.997962', 'COMPLETED', 860, 4, 1),
	(18, '', '2025-08-12 15:32:49.916837', 'COMPLETED', 1425, 6, 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_ferregestion.sale_details: ~13 rows (aproximadamente)
REPLACE INTO `sale_details` (`id`, `quantity`, `subtotal`, `unit_price`, `product_id`, `sale_id`) VALUES
	(1, 10, 800, 80, 3, 5),
	(2, 7, 840, 120, 5, 5),
	(3, 10, 18000, 1800, 2, 6),
	(4, 2, 500, 250, 1, 7),
	(5, 2, 160, 80, 3, 7),
	(6, 12, 21600, 1800, 2, 8),
	(7, 1, 1800, 1800, 2, 9),
	(8, 2, 160, 80, 3, 9),
	(9, 12, 960, 80, 3, 10),
	(10, 12, 960, 80, 3, 11),
	(11, 20, 2400, 120, 5, 11),
	(12, 12, 3000, 250, 1, 12),
	(13, 12, 3000, 250, 1, 13),
	(14, 12, 3000, 250, 1, 14),
	(15, 12, 38400, 3200, 4, 14),
	(16, 1, 1800, 1800, 2, 15),
	(17, 5, 400, 80, 3, 16),
	(18, 4, 320, 80, 3, 17),
	(19, 4, 540, 135, 36, 17),
	(20, 6, 480, 80, 3, 18),
	(21, 7, 945, 135, 36, 18);

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_ferregestion.suppliers: ~3 rows (aproximadamente)
REPLACE INTO `suppliers` (`id`, `address`, `created_at`, `email`, `is_active`, `name`, `phone`) VALUES
	(1, 'Av. Industrial 123, Ciudad Madero', '2025-08-10 22:22:33.000000', 'contacto@ferrecentral.com', b'1', 'Ferretería Central S.A.', '5551234567'),
	(2, 'Calle Obrera 456, Monterrey', '2025-08-10 22:22:33.000000', 'ventas@herramientasnorte.com', b'1', 'Herramientas del Norte Ltda.', '5559876543'),
	(3, 'Calle Edificios 789, Guadalajara', '2025-08-10 22:22:33.000000', 'info@mycr.com', b'1', 'Materiales y Construcciones R&M', '5554567890'),
	(4, 'Av. Industrial 123, Ciudad Madero', '2025-08-12 12:32:05.000000', 'contacto@ferrecentral.com', b'1', 'Ferretería Central S.A.', '5551234567'),
	(5, 'Calle Obrera 456, Monterrey', '2025-08-12 12:32:05.000000', 'ventas@herramientasnorte.com', b'1', 'Herramientas del Norte Ltda.', '5559876543'),
	(6, 'Calle Edificios 789, Guadalajara', '2025-08-12 12:32:05.000000', 'info@mycr.com', b'1', 'Materiales y Construcciones R&M', '5554567890'),
	(7, 'Calle 1 #123, Guadalajara', '2025-08-12 12:43:21.000000', 'contacto@ferreind.com', b'1', 'Ferretería Industrial S.A.', '5551002001'),
	(8, 'Av. Central 456, Monterrey', '2025-08-12 12:43:21.000000', 'ventas@hexpress.com', b'1', 'Herramientas Express Ltda.', '5552003002'),
	(9, 'Blvd. Norte 789, Tijuana', '2025-08-12 12:43:21.000000', 'info@matnorte.com', b'1', 'Materiales del Norte', '5553004003'),
	(10, 'Carretera 101, León', '2025-08-12 12:43:21.000000', 'compras@construtodo.com', b'1', 'ConstruTodo Distribuidora', '5554005004'),
	(11, 'Zona Industrial 202, Puebla', '2025-08-12 12:43:21.000000', 'ventas@fyt.com', b'1', 'Fijaciones y Tornillos S.A.', '5555006005'),
	(12, 'Calle Tubo 303, Querétaro', '2025-08-12 12:43:21.000000', 'ventas@tyaplus.com', b'1', 'Tuberías y Accesorios Plus', '5556007006'),
	(13, 'Calle 1 #123, Guadalajara', '2025-08-12 12:43:37.000000', 'contacto@ferreind.com', b'1', 'Ferretería Industrial S.A.', '5551002001'),
	(14, 'Av. Central 456, Monterrey', '2025-08-12 12:43:37.000000', 'ventas@hexpress.com', b'1', 'Herramientas Express Ltda.', '5552003002'),
	(15, 'Blvd. Norte 789, Tijuana', '2025-08-12 12:43:37.000000', 'info@matnorte.com', b'1', 'Materiales del Norte', '5553004003'),
	(16, 'Carretera 101, León', '2025-08-12 12:43:37.000000', 'compras@construtodo.com', b'1', 'ConstruTodo Distribuidora', '5554005004'),
	(17, 'Zona Industrial 202, Puebla', '2025-08-12 12:43:37.000000', 'ventas@fyt.com', b'1', 'Fijaciones y Tornillos S.A.', '5555006005'),
	(18, 'Calle Tubo 303, Querétaro', '2025-08-12 12:43:37.000000', 'ventas@tyaplus.com', b'1', 'Tuberías y Accesorios Plus', '5556007006');

-- Volcando estructura para tabla bd_ferregestion.user_auth
CREATE TABLE IF NOT EXISTS `user_auth` (
  `email` varchar(255) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_ferregestion.user_auth: ~0 rows (aproximadamente)
REPLACE INTO `user_auth` (`email`, `is_active`, `password`, `role`) VALUES
	('admin@ferre.com', b'1', '$2a$12$7A0Bjc/eD5WSctlD.CsE1.71cxcQq97jUYveoJjFuvcgFbt0Xhnc2', 'ADMINISTRADOR'),
	('ana.lopez@ferre.com', b'1', '$2a$12$7A0Bjc/eD5WSctlD.CsE1.71cxcQq97jUYveoJjFuvcgFbt0Xhnc2', 'EMPLEADO');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
