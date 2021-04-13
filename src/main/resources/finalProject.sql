INSERT INTO `discount_rates` (`id_discount_rate`, `description`, `discount`) VALUES
(0, 'Clarin 365', '%20');

INSERT INTO `providers` (`id_provider`, `address`, `country`, `name`, `phone`) VALUES
(0, 'Direccion1', 'Argentina', 'Jose', '1234567890'),
(0, 'Mi direccion2', 'Brasil', 'Josesinho', '12345667890');

INSERT INTO `parts` (`id_part`, `description`, `last_modification`, `long_dimension`, `net_weight`, `part_code`, `quantity`, `tall_dimension`, `width_dimension`, `id_provider`) VALUES
(0, 'Amortiguador delantero derecho - BMW 220i', '2021-01-05 16:53:11', 45, 5000, 11111112, 140, 45, 16, 1),
(0, 'Amortiguador delantero izquierdo - BMW 220i', '2021-01-05 16:54:11', 45, 3000, 11111113, 140, 45, 16, 1),
(0, 'Amortiguador trasero izquierdo - BMW 220i', '2021-01-05 16:54:22', 47, 3200, 11111114, 140, 47, 15, 1),
(0, 'Amortiguador trasero derecho - BMW 220i', '2021-01-05 16:55:22', 47, 3200, 11111115, 135, 47, 15, 1),
(0, 'Capot - BMW 220i', '2021-01-06 16:53:11', 130, 10000, 11111116, 120, 120, 140, 1),
(0, 'Capot - BMW 320i', '2021-01-06 16:55:11', 135, 11000, 11111117, 110, 125, 145, 1),
(0, 'Capot - BMW 325i', '2021-01-06 17:55:11', 137, 11000, 11111118, 100, 125, 147, 1),
(0, 'Capot - BMW 330i', '2021-01-08 17:56:11', 138, 12200, 11111119, 95, 135, 148, 1),
(0, 'Parabrisa - BMW 330i', '2021-01-08 18:58:11', 125, 7000, 11111121, 120, 105, 135, 1),
(0, 'Parabrisa - BMW 220i', '2021-02-06 17:58:11', 120, 7000, 11111120, 120, 105, 135, 1),
(0, 'Amortiguador delantero derecho - Scania S620', '2021-02-06 17:59:11', 160, 1500, 11111122, 100, 160, 45, 2),
(0, 'Amortiguador delantero izquierdo - Scania S620', '2021-02-07 10:54:11', 160, 8000, 11111123, 100, 160, 45, 2),
(0, 'Amortiguador trasero izquierdo - Scania S620', '2021-02-07 10:55:11', 175, 8000, 11111124, 102, 170, 45, 2),
(0, 'Amortiguador trasero derecho - Scania S620', '2021-02-07 10:55:25', 175, 8000, 11111125, 102, 170, 45, 2);

INSERT INTO `part_records` (`id_part_record`, `created_at`, `normal_price`, `sale_price`, `urgent_price`, `id_discount_rate`, `id_part`) VALUES
(0, '2021-01-05 16:53:11', 75000, 76500, 78000, 1, 1),
(0, '2021-02-06 17:59:11', 75000, 76500, 78000, 1, 11),
(0, '2021-01-05 16:54:11', 40000, 43000, 45000, 1, 2),
(0, '2021-02-07 10:54:11', 75000, 76500, 78000, 1, 12),
(0, '2021-01-05 16:55:22', 35000, 37000, 39000, 1, 4),
(0, '2021-02-07 10:55:25', 55000, 56500, 58000, 1, 14),
(0, '2021-01-05 16:54:22', 35000, 37500, 39000, 1, 3),
(0, '2021-02-07 10:55:11', 55000, 56500, 58000, 1, 13),
(0, '2021-01-06 16:53:11', 45000, 46000, 48000, 1, 5),
(0, '2021-01-06 16:55:11', 48000, 49000, 50000, 1, 6),
(0, '2021-01-06 17:55:11', 50000, 51000, 52000, 1, 7),
(0, '2021-01-08 17:56:11', 52000, 51500, 55000, 1, 8),
(0, '2021-01-08 18:58:11', 32000, 33500, 35000, 1, 9),
(0, '2021-02-06 17:58:11', 35000, 36000, 37000, 1, 10);

INSERT INTO `part_records` (`created_at`, `normal_price`, `sale_price`, `urgent_price`, `id_discount_rate`, `id_part`) VALUES
('2021-01-06 10:53:11', 90000, 91500, 93000, 1, 1);
INSERT INTO `part_records` (`created_at`, `normal_price`, `sale_price`, `urgent_price`, `id_discount_rate`, `id_part`) VALUES
('2021-01-08 16:53:11', 85000, 86500, 88000, 1, 1);
UPDATE `parts` SET
	`last_modification` = '2021-01-08 16:53:11'
WHERE
    `id_part` = 1;
INSERT INTO `part_records` (`created_at`, `normal_price`, `sale_price`, `urgent_price`, `id_discount_rate`, `id_part`) VALUES
('2021-04-07 10:53:11', 40000, 41500, 43000, 1, 10);
UPDATE `parts` SET
	`last_modification` = '2021-04-07 10:53:11'
WHERE
    `id_part` = 10;

INSERT INTO `subsidiaries` (`id_subsidiary`, `address`, `country`, `name`, `phone`) VALUES
(NULL, 'Mi casa', 'Uruguay', 'Mercedez Uruguay', '123456'),
(NULL, 'Mi direccion', 'Argentina', 'Mercedez Argentina', '789123'),
(NULL, 'My home', 'Colombia', 'Mercedez Colombia', '3456789');

INSERT INTO `orders` (`id_order`, `delivered_date`, `delivery_date`, `delivery_status`, `order_date`, `id_subsidiary`) VALUES
(NULL, NULL, '2021-04-09 14:17:58.000000', 'P', '2021-04-01 09:17:58.000000', '1'),
(NULL, '2021-04-04 14:17:58.000000', '2021-04-04 14:17:58.000000', 'F', '2021-04-01 14:17:58.000000', '2'),
(NULL, NULL, '2021-04-09 14:21:46', 'C', '2021-04-03 14:21:46', 1),
(NULL, NULL, '2021-04-05 14:23:20.000000', 'D', '2021-04-01 14:23:20.000000', '2'),
(NULL, '2021-04-07 14:17:58.000000', '2021-04-04 14:17:58.000000', 'F', '2021-04-01 14:17:58.000000', '3');

INSERT INTO `order_details` (`id_order_detail`, `account_type`, `quantity`, `id_order`, `id_part`) VALUES
(NULL, 'R', '999', '3', '1'),
(NULL, 'R', '5', '1', '5'),
(NULL, 'R', '5', '1', '6'),
(NULL, 'R', '5', '1', '7'),
(NULL, 'G', '1', '1', '8'),
(NULL, 'R', '140', '2', '1'),
(NULL, 'R', '10', '4', '10'),
(NULL, 'R', '10', '4', '3'),
(NULL, 'R', '10', '4', '13'),
(NULL, 'G', '1', '5', '3'),
(NULL, 'G', '1', '5', '4');

INSERT INTO subsidiary_stock (id_stock, quantity, id_part, id_subsidiary) VALUES
(NULL, '20', '15', '1'),
(NULL, '50', '25', '1'),
(NULL, '35', '15', '2'),
(NULL, '60', '17', '3'),
(NULL, '45', '21', '3'),
(NULL, '90', '23', '2'),
(NULL, '20', '28', '2'),
(NULL, '50', '23', '1'),
(NULL, '56', '27', '1'),
(NULL, '12', '26', '3');