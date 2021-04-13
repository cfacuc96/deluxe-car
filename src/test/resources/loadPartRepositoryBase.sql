-- test.sql
INSERT INTO `discount_rates` (`description`, `discount`) VALUES
('Clarin 365', '%20');

INSERT INTO `providers` (`address`, `country`, `name`, `phone`) VALUES
('Direccion1', 'Argentina', 'Jose', '1234567890');

INSERT INTO `parts` (`description`, `last_modification`, `long_dimension`, `net_weight`, `part_code`, `quantity`, `tall_dimension`, `width_dimension`, `id_provider`) VALUES
('Amortiguador delantero derecho - BMW 220i', '2021-01-05 16:53:11', 45, 5000, 11111112, 140, 45, 16, 1),
( 'Amortiguador delantero izquierdo - BMW 220i', '2021-01-05 16:54:11', 45, 3000, 11111113, 140, 45, 16, 1);

INSERT INTO `part_records` (`created_at`, `normal_price`, `sale_price`, `urgent_price`, `id_discount_rate`, `id_part`) VALUES
( '2021-01-05 16:53:11', 75000, 76500, 78000, 1, 1),
( '2021-01-05 16:54:11', 40000, 43000, 45000, 1, 2);
