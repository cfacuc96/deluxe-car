-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 08-04-2021 a las 16:35:53
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `finalProject`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `discount_rates`
--

CREATE TABLE `discount_rates` (
  `id_discount_rate` bigint(20) NOT NULL,
  `description` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `discount` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `discount_rates`
--

INSERT INTO `discount_rates` (`id_discount_rate`, `description`, `discount`) VALUES
(1, 'Clarin 365', '%20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parts`
--

CREATE TABLE `parts` (
  `id_part` bigint(20) NOT NULL,
  `description` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `last_modification` datetime NOT NULL,
  `long_dimension` int(11) NOT NULL,
  `net_weight` int(11) NOT NULL,
  `part_code` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `tall_dimension` int(11) NOT NULL,
  `width_dimension` int(11) NOT NULL,
  `id_provider` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `parts`
--

INSERT INTO `parts` (`id_part`, `description`, `last_modification`, `long_dimension`, `net_weight`, `part_code`, `quantity`, `tall_dimension`, `width_dimension`, `id_provider`) VALUES
(2, 'Amortiguador delantero derecho - BMW 220i', '2021-01-05 16:53:11', 45, 5000, 11111112, 140, 45, 16, 1),
(3, 'Amortiguador delantero izquierdo - BMW 220i', '2021-01-05 16:54:11', 45, 3000, 11111113, 140, 45, 16, 1),
(4, 'Amortiguador trasero izquierdo - BMW 220i', '2021-01-05 16:54:22', 47, 3200, 11111114, 140, 47, 15, 1),
(5, 'Amortiguador trasero derecho - BMW 220i', '2021-01-05 16:55:22', 47, 3200, 11111115, 135, 47, 15, 1),
(6, 'Capot - BMW 220i', '2021-01-06 16:53:11', 130, 10000, 11111116, 120, 120, 140, 1),
(7, 'Capot - BMW 320i', '2021-01-06 16:55:11', 135, 11000, 11111117, 110, 125, 145, 1),
(8, 'Capot - BMW 325i', '2021-01-06 17:55:11', 137, 11000, 11111118, 100, 125, 147, 1),
(9, 'Capot - BMW 330i', '2021-01-08 17:56:11', 138, 12200, 11111119, 95, 135, 148, 1),
(10, 'Parabrisa - BMW 330i', '2021-01-08 18:58:11', 125, 7000, 11111121, 120, 105, 135, 1),
(11, 'Parabrisa - BMW 220i', '2021-01-06 17:58:11', 120, 7000, 11111120, 120, 105, 135, 1),
(12, 'Amortiguador delantero derecho - Scania S620', '2021-02-06 17:59:11', 160, 1500, 11111122, 100, 160, 45, 2),
(13, 'Amortiguador delantero izquierdo - Scania S620', '2021-02-07 10:54:11', 160, 8000, 11111123, 100, 160, 45, 2),
(14, 'Amortiguador trasero izquierdo - Scania S620', '2021-02-07 10:55:11', 175, 8000, 11111124, 102, 170, 45, 2),
(15, 'Amortiguador trasero derecho - Scania S620', '2021-02-07 10:55:25', 175, 8000, 11111125, 102, 170, 45, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `part_records`
--

CREATE TABLE `part_records` (
  `id_part_record` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `normal_price` double NOT NULL,
  `sale_price` double NOT NULL,
  `urgent_price` double NOT NULL,
  `id_discount_rate` bigint(20) NOT NULL,
  `id_part` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `part_records`
--

INSERT INTO `part_records` (`id_part_record`, `created_at`, `normal_price`, `sale_price`, `urgent_price`, `id_discount_rate`, `id_part`) VALUES
(15, '2021-02-08 12:48:33', 75000, 76500, 78000, 1, 11),
(16, '2021-02-08 14:48:55', 40000, 43000, 45000, 1, 2),
(17, '2021-02-08 14:49:33', 75000, 76500, 78000, 1, 12),
(18, '2021-02-09 14:48:33', 35000, 37000, 39000, 1, 4),
(19, '2021-02-09 12:48:33', 55000, 56500, 58000, 1, 14),
(20, '2021-02-09 14:55:33', 35000, 37500, 39000, 1, 3),
(21, '2021-02-10 12:24:14', 55000, 56500, 58000, 1, 13),
(22, '2021-03-02 15:24:14', 45000, 46000, 48000, 1, 5),
(23, '2021-03-12 15:24:14', 48000, 49000, 50000, 1, 6),
(24, '2021-03-12 10:24:14', 50000, 51000, 52000, 1, 7),
(25, '2021-03-13 15:20:14', 52000, 51500, 55000, 1, 8),
(26, '2021-02-25 15:24:14', 32000, 33500, 35000, 1, 9),
(27, '2021-03-15 15:24:14', 35000, 36000, 37000, 1, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `providers`
--

CREATE TABLE `providers` (
  `id_provider` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `country` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `providers`
--

INSERT INTO `providers` (`id_provider`, `address`, `country`, `name`, `phone`) VALUES
(1, 'Direccion1', 'Argentina', 'Jose', '1234567890'),
(2, 'Mi direccion2', 'Brasil', 'Josesinho', '12345667890');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `discount_rates`
--
ALTER TABLE `discount_rates`
  ADD PRIMARY KEY (`id_discount_rate`);

--
-- Indices de la tabla `parts`
--
ALTER TABLE `parts`
  ADD PRIMARY KEY (`id_part`),
  ADD KEY `FKm5h63f8s5ybj664h6fyie2tpr` (`id_provider`);

--
-- Indices de la tabla `part_records`
--
ALTER TABLE `part_records`
  ADD PRIMARY KEY (`id_part_record`),
  ADD KEY `FK6q5ju2ig6sfsum0pyjx67rf7t` (`id_discount_rate`),
  ADD KEY `FKetmvn84jrjrrh61siw2pad8tc` (`id_part`);

--
-- Indices de la tabla `providers`
--
ALTER TABLE `providers`
  ADD PRIMARY KEY (`id_provider`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `discount_rates`
--
ALTER TABLE `discount_rates`
  MODIFY `id_discount_rate` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `parts`
--
ALTER TABLE `parts`
  MODIFY `id_part` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `part_records`
--
ALTER TABLE `part_records`
  MODIFY `id_part_record` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `providers`
--
ALTER TABLE `providers`
  MODIFY `id_provider` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `parts`
--
ALTER TABLE `parts`
  ADD CONSTRAINT `FKm5h63f8s5ybj664h6fyie2tpr` FOREIGN KEY (`id_provider`) REFERENCES `providers` (`id_provider`);

--
-- Filtros para la tabla `part_records`
--
ALTER TABLE `part_records`
  ADD CONSTRAINT `FK6q5ju2ig6sfsum0pyjx67rf7t` FOREIGN KEY (`id_discount_rate`) REFERENCES `discount_rates` (`id_discount_rate`),
  ADD CONSTRAINT `FKetmvn84jrjrrh61siw2pad8tc` FOREIGN KEY (`id_part`) REFERENCES `parts` (`id_part`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
