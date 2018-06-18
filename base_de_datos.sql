-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 18-06-2018 a las 23:38:15
-- Versión del servidor: 5.6.35
-- Versión de PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ejerciciofinal`
--
CREATE DATABASE IF NOT EXISTS `ejerciciofinal` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `ejerciciofinal`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividades`
--

CREATE TABLE `actividades` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(16) NOT NULL,
  `NOMBRE_PABELLON` varchar(16) NOT NULL,
  `DESCRIPCION` varchar(32) NOT NULL,
  `INICIO` date NOT NULL,
  `PRECIO` float NOT NULL,
  `PLAZAS_TOTALES` int(11) NOT NULL,
  `PLAZAS_OCUPADAS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `actividades`
--

INSERT INTO `actividades` (`ID`, `NOMBRE`, `NOMBRE_PABELLON`, `DESCRIPCION`, `INICIO`, `PRECIO`, `PLAZAS_TOTALES`, `PLAZAS_OCUPADAS`) VALUES
(1, 'futbol', 'pepito 3º', 'AAA', '0000-00-00', 25, 15, 6),
(2, 'baloncesto', 'pepito 2º', 'BBB', '2018-07-08', 15, 20, 19),
(3, 'cricket', 'pepito 2º', 'CCC', '2018-02-05', 15, 20, 20),
(4, 'water polo', 'pepito 1º', 'DDD', '2018-08-07', 16, 10, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `LOGIN` varchar(16) NOT NULL,
  `PASSWD` varchar(16) NOT NULL,
  `NOMBRE` varchar(16) NOT NULL,
  `APELLIDO` varchar(16) NOT NULL,
  `DIRECCION` varchar(16) NOT NULL,
  `TELEFONO` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`LOGIN`, `PASSWD`, `NOMBRE`, `APELLIDO`, `DIRECCION`, `TELEFONO`) VALUES
('pipo', 'pepito', 'Pipo', 'Grillo', 'c/ pepito grillo', '12345678');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripciones`
--

CREATE TABLE `inscripciones` (
  `LOGIN_CLIENTE` varchar(16) NOT NULL,
  `ID_ACTIVIDAD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `inscripciones`
--

INSERT INTO `inscripciones` (`LOGIN_CLIENTE`, `ID_ACTIVIDAD`) VALUES
('pipo', 1),
('pipo', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pabellones`
--

CREATE TABLE `pabellones` (
  `PABELLON` varchar(16) NOT NULL,
  `LOCALIDAD` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pabellones`
--

INSERT INTO `pabellones` (`PABELLON`, `LOCALIDAD`) VALUES
('pepito 1º', 'Salamanca'),
('pepito 2º', 'Salamanca'),
('pepito 3º', 'Bejar');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividades`
--
ALTER TABLE `actividades`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `NOMBRE_PABELLON` (`NOMBRE_PABELLON`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`LOGIN`);

--
-- Indices de la tabla `inscripciones`
--
ALTER TABLE `inscripciones`
  ADD PRIMARY KEY (`LOGIN_CLIENTE`,`ID_ACTIVIDAD`),
  ADD KEY `ID_ACTIVIDAD` (`ID_ACTIVIDAD`);

--
-- Indices de la tabla `pabellones`
--
ALTER TABLE `pabellones`
  ADD PRIMARY KEY (`PABELLON`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividades`
--
ALTER TABLE `actividades`
  ADD CONSTRAINT `actividades_ibfk_1` FOREIGN KEY (`NOMBRE_PABELLON`) REFERENCES `pabellones` (`PABELLON`);

--
-- Filtros para la tabla `inscripciones`
--
ALTER TABLE `inscripciones`
  ADD CONSTRAINT `inscripciones_ibfk_1` FOREIGN KEY (`ID_ACTIVIDAD`) REFERENCES `actividades` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
