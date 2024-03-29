-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 24, 2020 at 03:10 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `accesslevels`
--

DROP TABLE IF EXISTS `accesslevels`;
CREATE TABLE IF NOT EXISTS `accesslevels` (
  `accesslevel_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `level_number` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`accesslevel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accesslevels`
--

INSERT INTO `accesslevels` (`accesslevel_id`, `description`, `level_number`, `created_at`, `updated_at`) VALUES
(1, 'Read only', 1, NULL, NULL),
(2, 'Read and write', 2, NULL, NULL),
(3, 'Full access', 3, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `equivalences`
--

DROP TABLE IF EXISTS `equivalences`;
CREATE TABLE IF NOT EXISTS `equivalences` (
  `equivalence_id` int(11) NOT NULL AUTO_INCREMENT,
  `column_name` varchar(255) DEFAULT NULL,
  `fenix_equivalence` varchar(255) DEFAULT NULL,
  `jira_equivalence` varchar(255) DEFAULT NULL,
  `file_type_id` int(11) DEFAULT NULL,
  `column_order` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`equivalence_id`),
  KEY `FKft0b6ikw387477tx3qg7tfgb5` (`file_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equivalences`
--

INSERT INTO `equivalences` (`equivalence_id`, `column_name`, `fenix_equivalence`, `jira_equivalence`, `file_type_id`, `column_order`, `created_at`, `updated_at`) VALUES
(4, 'key', NULL, 'Key', 4, NULL, NULL, NULL),
(5, 'summary', 'Nombre Incidencia', 'Summary', 4, 2, NULL, NULL),
(6, 'description', 'Descripción', 'Description', 4, 5, NULL, NULL),
(7, 'originalEstimate', 'Esfuerzo (HH)', 'Original Estimate', 4, 8, NULL, NULL),
(8, 'created', NULL, NULL, 4, NULL, NULL, NULL),
(9, 'updated', NULL, 'Updated', 4, NULL, NULL, NULL),
(10, 'resolved', NULL, 'Resolved', 4, NULL, NULL, NULL),
(11, 'timeSpent', NULL, 'Time Spent', 4, NULL, NULL, NULL),
(12, 'status', NULL, 'Status', 4, NULL, NULL, NULL),
(13, 'incidenceType', NULL, 'Tipo incidencia', 4, NULL, NULL, NULL),
(14, 'assignedUser', NULL, 'Assignee', 4, NULL, NULL, NULL),
(15, 'idIncidencia', 'Id Incidencia', NULL, 4, 0, NULL, NULL),
(16, 'idPeticion', 'Id Petición / OT', NULL, 4, 1, NULL, NULL),
(17, 'localizadaEn', 'Localizada En', NULL, 4, 3, NULL, NULL),
(18, 'fechaInicio', 'Fecha Inicio', NULL, 4, 6, NULL, NULL),
(19, 'fechaFin', 'Fecha Fin', NULL, 4, 7, NULL, NULL),
(20, 'urgencia', 'Urgencia', NULL, 4, 9, NULL, NULL),
(21, 'impacto', 'Impacto', NULL, 4, 10, NULL, NULL),
(22, 'resueltaPorCliente', 'Resuelta por Cliente', NULL, 4, 11, NULL, NULL),
(23, 'prioridad', 'Prioridad', NULL, 4, 12, NULL, NULL),
(24, 'fechaPrevistaCentro', 'Fecha Prevista Centro', NULL, 4, 13, NULL, NULL),
(25, 'tareaCausante', 'Tarea Causante', NULL, 4, 14, NULL, NULL),
(26, 'otCorrector', 'OT Corrector', NULL, 4, 15, NULL, NULL),
(27, 'accCorrector', 'ACC Corrector', NULL, 4, 16, NULL, NULL),
(28, 'estado', 'Estado', NULL, 4, 17, NULL, NULL),
(29, 'convertibleEnMejora', 'Convertible en Mejora', NULL, 4, 18, NULL, NULL),
(30, 'comentarioAlDesestimar', 'Comentario al Desestimar', NULL, 4, 19, NULL, NULL),
(31, 'tipoIncidencia', 'Tipo Incidencia', NULL, 4, 4, NULL, NULL),
(32, 'project', NULL, 'Project', 6, NULL, NULL, '2020-07-02 11:27:05'),
(33, 'key', NULL, 'Key', 6, NULL, NULL, '2020-07-01 12:55:41'),
(34, 'summary', 'Nombre', 'Title', 6, 1, NULL, NULL),
(35, 'description', 'Descripción', 'Description', 6, 3, NULL, '2020-07-01 12:50:37'),
(36, 'fixVersion', NULL, 'Fix Version/s', 6, NULL, NULL, NULL),
(37, 'originalEstimate', 'Esfuerzo', 'Original Estimate', 6, 11, NULL, NULL),
(38, 'remainingEstimate', NULL, 'Remaining Estimate', 6, NULL, NULL, NULL),
(39, 'status', NULL, 'Status', 6, NULL, NULL, NULL),
(40, 'timeSpent', NULL, 'Time Spent', 6, NULL, NULL, NULL),
(41, 'taskType', NULL, 'Tipo Tarea', 6, NULL, NULL, NULL),
(42, 'assignedUser', 'Responsable', 'Username', 6, 7, NULL, NULL),
(43, 'comment', NULL, 'Comment', 6, NULL, NULL, NULL),
(44, 'idAcc', 'ID ACC', NULL, 6, 0, NULL, NULL),
(45, 'codigoPeticionCliente', 'Código Petición Cliente', NULL, 6, 2, NULL, NULL),
(46, 'estado', 'Estado', NULL, 6, 4, NULL, NULL),
(47, 'tipo', 'Tipo', NULL, 6, 5, NULL, NULL),
(48, 'idOt', 'Pet/Ot. Asociada', NULL, 6, 6, NULL, NULL),
(49, 'subtipo', 'Subtipo', NULL, 6, 8, NULL, NULL),
(50, 'rechazosEntrega', 'Rechazos Entrega', NULL, 6, 9, NULL, NULL),
(51, 'criticidad', 'Criticidad', NULL, 6, 10, NULL, NULL),
(52, 'esfuerzoCliente', 'Esfuerzo Cliente', NULL, 6, 12, NULL, NULL),
(53, 'fechaCreacion', 'Fecha Creación', NULL, 6, 13, NULL, NULL),
(54, 'fechaSolicitudCliente', 'Fecha Solicitud Cliente', NULL, 6, 14, NULL, NULL),
(55, 'fechaPrevistaProyecto', 'Fecha Prevista Proyecto', NULL, 6, 15, NULL, NULL),
(56, 'fechaEntrega', 'Fecha Entrega', NULL, 6, 16, NULL, NULL),
(57, 'fechaCierre', 'Fecha Cierre', NULL, 6, 17, NULL, NULL),
(58, 'fechaDesestimacion', 'Fecha Desestimación', NULL, 6, 18, NULL, NULL),
(59, 'fechaInicioCentro', 'Fecha Inicio Centro', NULL, 6, 19, NULL, NULL),
(60, 'resultadoTesting', 'Resultado Testing', NULL, 6, 20, NULL, NULL),
(61, 'puntosHistoria', 'Puntos Historia', NULL, 6, 21, NULL, NULL),
(62, 'historiaUsuario', 'Historia Usuario', NULL, 6, 22, NULL, NULL),
(63, 'epica', 'Épica', NULL, 6, 23, NULL, NULL),
(64, 'jiraSas', NULL, 'Jira SAS', 4, NULL, NULL, NULL),
(65, 'linkedIssues', NULL, 'Linked Issues', 4, NULL, NULL, NULL),
(67, 'causedUser', NULL, 'Causante', 4, NULL, NULL, NULL),
(68, 'project', NULL, 'Project', 4, NULL, NULL, NULL),
(90, NULL, 'ID Tarea', NULL, 7, 0, NULL, NULL),
(91, NULL, 'Orden', NULL, 7, 1, NULL, NULL),
(92, 'taskType', 'Tipo Tarea', 'Tipo incidencia', 7, 2, NULL, '2020-07-02 11:27:11'),
(93, 'summary', 'Nombre', 'Summary', 7, 3, NULL, NULL),
(94, NULL, 'Tipo Planificación', NULL, 7, 4, NULL, NULL),
(95, 'assignedUser', 'Responsable', 'Assignee', 7, 5, NULL, NULL),
(96, 'originalEstimate', 'Estimado', 'Original Estimate', 7, 6, NULL, NULL),
(97, NULL, 'Id OT', NULL, 7, 7, NULL, NULL),
(98, NULL, 'Comienzo', NULL, 7, 8, NULL, NULL),
(99, NULL, 'Fin', NULL, 7, 9, NULL, NULL),
(100, NULL, 'Planificado', NULL, 7, 10, NULL, NULL),
(101, NULL, 'Incurrido', NULL, 7, 11, NULL, NULL),
(102, NULL, 'ETC', NULL, 7, 12, NULL, NULL),
(103, NULL, 'ETC Manual', NULL, 7, 13, NULL, NULL),
(104, 'key', NULL, 'Key', 7, NULL, NULL, NULL),
(105, 'status', NULL, 'Status', 7, NULL, NULL, NULL),
(106, 'created', NULL, 'Created', 7, NULL, NULL, NULL),
(107, 'updated', NULL, 'Updated', 7, NULL, NULL, NULL),
(108, 'remainingEstimate', NULL, 'Remaining Estimate', 7, NULL, NULL, NULL),
(109, 'timeSpent', NULL, 'Time Spent', 7, NULL, NULL, NULL),
(110, 'resolved', NULL, 'Resolved', 7, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `features`
--

DROP TABLE IF EXISTS `features`;
CREATE TABLE IF NOT EXISTS `features` (
  `feature_id` int(11) NOT NULL AUTO_INCREMENT,
  `feature_name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `parent_title` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`feature_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `features`
--

INSERT INTO `features` (`feature_id`, `feature_name`, `url`, `title`, `parent_title`, `created_at`, `updated_at`) VALUES
(1, 'Input file management ', 'files/import', 'Import file', 'Files', NULL, NULL),
(2, 'User management', 'users', 'Users', NULL, NULL, NULL),
(3, 'Project management', 'projects', 'Projects', NULL, NULL, NULL),
(4, 'Output file management', 'files/export', 'Export file', 'Files', NULL, NULL),
(5, 'Permission management', 'permissions', 'Permissions', NULL, NULL, NULL),
(6, 'My tasks', 'tasks', 'Tasks', NULL, NULL, NULL),
(7, 'Profile', 'profile', 'My profile', NULL, NULL, NULL),
(8, 'Files parameters', 'settings', 'Files settings', NULL, NULL, NULL),
(9, 'My incidences', 'incidences', 'Incidences', NULL, NULL, NULL),
(10, 'Your justifications', 'justifications', 'Justifications', NULL, NULL, NULL),
(11, 'Descendants justifications', 'justifications', 'Justifications', NULL, NULL, NULL),
(12, 'Team members tasks', 'tasks', 'Tasks', NULL, NULL, NULL),
(13, 'Team members incidences', 'incidences', 'Incidences', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `incidences`
--

DROP TABLE IF EXISTS `incidences`;
CREATE TABLE IF NOT EXISTS `incidences` (
  `incidence_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `description` longtext,
  `incidence_type` varchar(255) DEFAULT NULL,
  `jira_sas` varchar(255) DEFAULT NULL,
  `incidence_key` varchar(255) DEFAULT NULL,
  `linked_issues` varchar(255) DEFAULT NULL,
  `original_estimate` varchar(255) DEFAULT NULL,
  `planned_end` datetime DEFAULT NULL,
  `resolved` datetime DEFAULT NULL,
  `task_status` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `time_spent` varchar(255) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `assigned_user_id` bigint(20) DEFAULT NULL,
  `caused_user_id` bigint(20) DEFAULT NULL,
  `file_type_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`incidence_id`),
  KEY `FKaaw4xljp45ujl5rib6u71tq9e` (`assigned_user_id`),
  KEY `FKmuuyn26xem3qriljwhyj0xpyy` (`caused_user_id`),
  KEY `FKs7xhs54k23t87qkp7yxyopsub` (`file_type_id`),
  KEY `FKnrpal2aoljkhlhp7l1t9ynk60` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `incidences`
--

INSERT INTO `incidences` (`incidence_id`, `comment`, `created`, `date`, `description`, `incidence_type`, `jira_sas`, `incidence_key`, `linked_issues`, `original_estimate`, `planned_end`, `resolved`, `task_status`, `summary`, `time_spent`, `updated`, `assigned_user_id`, `caused_user_id`, `file_type_id`, `project_id`, `created_at`, `updated_at`) VALUES
(25, NULL, NULL, '2020-05-12 16:04:24', '', 'Construcción', 'SIGLO-3170', 'ECOFISAS-9565', '', '0,0', NULL, '2020-03-20 00:00:00', 'Resolved', 'ECOFISAS-8381 INC_EXT_02 - Resolución SONAR', NULL, '2020-01-30 18:54:00', 7, 7, 3, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(26, NULL, NULL, '2020-05-12 16:04:24', 'Los errores detectados son los siguientes (algunos por el tema del SQLPlus que ya hemos comentado): \n• 001_NUEVA_TABLA_ALM_SISTEMAMENSAJE.sql ? Falla el create table por tener líneas vacías. La creación de la primary key falla porque el nombre ya se está usando en otra tabla. \n• 004_CREAR_NUEVO_PARAMETRO_EN_ORG_PARAMETRO.sql ? Fallo en el insert por incluir línea vacía. \n• 005_NUEVA_TABLA_ALM_HISTMENCARRUSELEP.sql ?Fallo en el create table por incluir líneas vacías. También falla porque los nombres de las foreing key ya se estaban usando en otro objeto (en concreto en un script previo). \n• 006_NUEVA_TABLA_ALM_HISTMENCARRUSELEPLINEA.sql ? Se llamaba inicialmente 006_NUEVA_TABLA_ALM_HISTMENCARRUSELEPLINEA .sql, es decir con un espacio en blanco. Se ha renombrado porque daba error al ejecutarse. También fallaba por las líneas vacías. La foreign key fallaba porque en el script 005 no se había creado primary key- \n• 007_NUEVA_TABLA_ALM_HISTMENCARRUSELEPERROR.sql ? Se ha renombrado igual que el punto anterior. También fallaba por líneas vacías en el create table. \n• 009_INSERT_ORG_TAREA.sql ? No se habían cerrado las comillas. \n• 010_PROCEDURE_PASOHISTORICOMENCARRUSELEP.sql ? Faltaba al final del procedure la barra / para indicar que ha finalizado la sentencia. Se ha eliminado la ejecución posterior del procedure “EXEC PASOHISTORICOMENCARRUSELEP;”. Lo he hablado con David y me ha comentado que no debería ejecutarse el procedure al lanzar el script. \n\nTiempo estimado : 0.5h ( se necesita solo subir los archivos corregidos por Borja) \n', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9531', 'ECOFISAS-9507', '0,0', NULL, '2020-01-29 12:54:00', 'Resolved', 'ECOFISAS-9110 INC_INT_31 - Errores en scripts', '0,1', '2020-02-24 16:28:00', 7, 7, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(27, NULL, NULL, '2020-05-12 16:04:24', 'Se han detectado errores en los scripts y se procede a su corrección: \n\nDO: \n- 001: El formato debe se ANSI, jamás debe ser utf-8 o no funcionará en SQLPLUS \n- 007: El script estaba mal a la hora de añadir las columnas de la tabla \nUNDO: \n- 003: La \"/\" estaba mal colocada, encima del END del script \n- 007: Faltaba realizar el drop de la secuencia', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9554', '', '0,0', NULL, '2020-03-20 17:14:00', 'Resolved', 'ECOFISAS-9110 INC_EXT_32- Errores en los scripts de la tarea.', NULL, '2020-01-31 12:42:00', 7, 7, 3, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(28, NULL, NULL, '2020-05-12 16:04:24', 'Cuando haber un registro en tabla ALM_MENMOVSTOCK con el motivo de error \"Las existencias que indica son superiores a las que hay en SIGLO\", en la busqueda de pantalla Consulta de Recuentos RFID se muestra el error NPE \n\n\n', 'No es una incidencia', 'SIGLO-2937', 'ECOFISAS-9553', 'ECOFISAS-9552', '0,0', NULL, '2020-02-03 17:11:00', 'Resolved', 'ECOFISAS-9110 INC_INT_30 - NPE cuando buscar registros con incidencias', NULL, '2020-02-03 17:11:00', 7, 7, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(29, NULL, NULL, '2020-05-12 16:04:24', 'No se genera el mensaje de devolución cuando se ejecuta una OT con destino físico a una ubicación de una zona integrada. \nPaso. \nSe registra la solicitud de devolución ORIGEN ADC destino AC ubicación carrusel. \nSe edita la OT y se le incluye la ubicación destino.8RLQP \nSe revisa la tabla de select * FROM siglo.ALM_MENCARRUSELEP cmsj order by fechaenvio desc ; y no ha generado mensaje de devolución. \nSe revisa la OT y esta ejecutada correctamente. \n\n\n\n', 'Construcción', 'SIGLO-2949', 'ECOFISAS-9698', 'ECOFISAS-9625', '0,0', NULL, '2020-02-10 17:20:00', 'Resolved', 'ECOFISAS-7609 INC_EXT_22- No genera mensaje de orden de trabajo de devolución.', '8,0', '2020-02-11 08:52:00', 6, 6, 3, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(30, NULL, NULL, '2020-05-12 16:04:24', 'No se cumple el requisito. \n005-FEF-3965-01-04-01 \nSi el genérico de centro incluido en la OT de devolución, pertenece a una ubicación física de un carrusel integrado, deberá crearse la OT con la línea cumplimentada con la ubicación física automáticamente, para que el gestor no tenga que completar la ubicación. \nDevolución 1479036 (todas las solicitudes se han creado con fecha de 06/02/2020). \nGC E67698 --> L-LOGISONE006 \n\n\n', 'Análisis', 'SIGLO-2949', 'ECOFISAS-9696', 'ECOFISAS-9625', '0,0', NULL, '2020-02-10 17:22:00', 'Resolved', 'ECOFISAS-7609 INC_EXT_21- No asocia automáticamente la ubicación a la OT de devolución', '2,5', '2020-02-11 09:03:00', 6, 6, 3, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(31, NULL, NULL, '2020-05-12 16:04:24', 'Estimación 2h', 'Construcción', 'SIGLO-3250', 'ECOFISAS-9225', 'ECOFISAS-9258', '0,0', NULL, '2020-02-10 10:45:00', 'Resolved', 'ECOFISAS-8933 INC_INT_06 - No se tiene en cuenta el destino para los criterios de búsqueda', '2,0', '2020-01-30 14:43:00', 7, 6, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(32, NULL, NULL, '2020-05-12 16:04:24', 'Se detecta un error al acceder a los detalles de la solicitud de devolución. \nPara reproducirlo: Acceder Gestión de Solicitudes de Devolución --> Consultar OT 1479033 y luego pulsar en el botón detalle. \n\"com.hp.geos.almacen.modelo.objetosNegocio.implementacion.SolicitudDevolucionImpl\" \n', 'Construcción', 'SIGLO-2949', 'ECOFISAS-9692', 'ECOFISAS-9625', '0,0', NULL, '2020-03-07 15:35:00', 'Resolved', 'ECOFISAS-7609 INC_EXT_18- Error al acceder al detalle de una solicitud de devolución', '0,5', '2020-02-07 15:36:00', 6, 6, 3, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(33, NULL, NULL, '2020-05-12 16:04:24', '', 'Construcción', 'SIGLO-3264', 'ECOFISAS-9435', 'ECOFISAS-9394, ECOFISAS-9858', '0,0', NULL, '2020-01-27 08:51:00', 'Resolved', 'ECOFISAS-9059 INC_INT_03 No se ejecuta la tarea de generación de pedidos externos en segundo plano', '16,0', '2020-02-20 22:59:00', 6, 6, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(34, NULL, NULL, '2020-05-12 16:04:24', '', 'Construcción', 'SIGLO-3264', 'ECOFISAS-9394', 'ECOFISAS-9358, ECOFISAS-9435', '0,0', NULL, '2020-01-23 17:03:00', 'Resolved', 'ECOFISAS-9059 INC_INT_02 No se muestran los nuevos campos observaciones por proveedor y recordatorio', '2,0', '2020-02-11 17:39:00', 6, 6, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(35, NULL, NULL, '2020-05-12 16:04:24', 'Columna generación pedido de pantalla Consulta de Recuentos RFID se muestra valores incorrectos, debe mostrar valores Sí o No \nCódigo mensaje no muestra ningún valor \nen la exportación de excel se muestra correctamente el valor de la columna generación pedido y no se muestra el valor de la columna código mensaje \n\nDatos de pruebas en Nube 1 \nPlataforma Sevila Almacen _[DS] ALMACEN DISTRITO A.P. SEVILLA \nfechas 21/012020 hasta 23/01/2020 \n\nResponsable ==> Nafie \n', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9538', 'ECOFISAS-9537, ECOFISAS-9552', '0,0', NULL, '2020-02-03 17:26:00', 'Resolved', 'ECOFISAS-9110 INC_INT_28 - se muestran valores incorrectos en las columnas generación pedido y código mensaje de pantalla Consulta de Recuentos RFID', '2,0', '2020-02-03 18:29:00', 7, 7, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(36, NULL, NULL, '2020-05-12 16:04:24', 'No se muestra el aviso que debe haber un unico articulo en pantalla Consulta de Recuentos RFID cuando seleccionar el check de datos historicos y no hay GC \n\nResponsable ==> Nafie \n', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9537', 'ECOFISAS-9536, ECOFISAS-9538', '0,0', NULL, '2020-05-05 01:05:00', 'Resolved', 'ECOFISAS-9110 INC_INT_27 - No se muestra el aviso que debe haber un unico articulo en pantalla Consulta de Recuentos RFID', '3,5', '2020-02-05 01:05:00', 7, 6, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36');

-- --------------------------------------------------------

--
-- Table structure for table `justifications`
--

DROP TABLE IF EXISTS `justifications`;
CREATE TABLE IF NOT EXISTS `justifications` (
  `justification_id` int(11) NOT NULL AUTO_INCREMENT,
  `concerned_month` date DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `is_validated` bit(1) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `validation_date` datetime DEFAULT NULL,
  `justificator_id` bigint(20) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `validator_id` bigint(20) DEFAULT NULL,
  `is_archived` bit(1) DEFAULT NULL,
  PRIMARY KEY (`justification_id`),
  KEY `FKbhj6gpkn58kqmygyuo2m5pbsk` (`justificator_id`),
  KEY `FKtk4d55jdiv9c5is4unggd45al` (`project_id`),
  KEY `FKo113xoleone88q7wb7e9q3c3i` (`validator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `justifications`
--

INSERT INTO `justifications` (`justification_id`, `concerned_month`, `content`, `created_at`, `is_validated`, `subject`, `updated_at`, `validation_date`, `justificator_id`, `project_id`, `validator_id`, `is_archived`) VALUES
(1, '2020-07-01', 'justification content ...', '2020-08-18 12:31:18', b'1', 'faible efficience', '2020-08-18 12:32:34', '2020-08-18 12:32:34', 6, 4, 8, b'0'),
(2, '2020-06-01', 'justifi...', '2020-08-18 20:03:36', b'0', 'faible eff', '2020-08-18 20:03:36', NULL, 4, 4, NULL, b'0'),
(4, '2019-10-01', 'justification text...', '2020-08-23 00:48:08', b'1', 'description', '2020-08-23 01:06:39', '2020-08-23 01:06:39', 4, 4, 8, b'0'),
(5, '2020-07-01', 'justification text....', '2020-08-24 12:54:44', b'0', 'low efficiency', '2020-08-24 13:28:08', NULL, 8, 4, NULL, b'0'),
(6, '2020-06-01', 'Justification description ...', '2020-08-24 12:56:01', b'0', 'subject 2 : low efficiency... june...', '2020-08-24 12:56:20', NULL, 8, 4, NULL, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
CREATE TABLE IF NOT EXISTS `projects` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `is_closed` bit(1) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`project_id`, `description`, `is_closed`, `project_name`, `created_at`, `updated_at`) VALUES
(1, 'desc1', b'0', 'project1', NULL, NULL),
(2, 'desc2', b'0', 'project2', NULL, NULL),
(4, 'SAS Description', b'0', 'ECOFISAS - EcoFI-SAS (Mantenimiento de los sistemas económico financieros...', NULL, NULL),
(5, 'desc nouv projet updated', b'1', 'nouveau projet updated', NULL, NULL),
(6, 'project test description ...', b'1', 'Project Test', '2020-07-02 12:47:07', '2020-07-02 12:47:23');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `child_role_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `FKhond2qgppu2q1b50nwx6hy4g9` (`child_role_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `name`, `created_at`, `updated_at`, `child_role_role_id`) VALUES
(1, 'ROLE_ADMIN', NULL, NULL, NULL),
(2, 'ROLE_SUPERVISOR', NULL, NULL, 3),
(3, 'ROLE_TEAMLEADER', NULL, NULL, 4),
(4, 'ROLE_SENIOR', NULL, NULL, 5),
(5, 'ROLE_DEVELOPER', NULL, NULL, 6),
(6, 'ROLE_JUNIOR', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `role_features`
--

DROP TABLE IF EXISTS `role_features`;
CREATE TABLE IF NOT EXISTS `role_features` (
  `role_id` int(11) NOT NULL,
  `feature_id` int(11) NOT NULL,
  `accesslevel_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`feature_id`,`role_id`),
  KEY `FK17369effe8mrvk1ugtbrw8wly` (`role_id`),
  KEY `FKa4r88t23vyipysnn7kdc14kpu` (`accesslevel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_features`
--

INSERT INTO `role_features` (`role_id`, `feature_id`, `accesslevel_id`, `created_at`, `updated_at`) VALUES
(3, 1, 3, '2020-08-24 15:22:09', '2020-08-24 15:22:09'),
(1, 2, 3, '2020-05-26 18:58:59', '2020-05-26 18:58:59'),
(1, 3, 3, '2020-05-26 18:58:59', '2020-05-26 18:58:59'),
(3, 4, 3, '2020-08-24 15:22:09', '2020-08-24 15:22:09'),
(1, 5, 3, '2020-05-26 18:58:59', '2020-05-26 18:58:59'),
(3, 6, 3, '2020-08-24 15:22:09', '2020-08-24 15:22:09'),
(4, 6, 3, '2020-08-23 00:45:28', '2020-08-23 00:45:28'),
(5, 6, 3, '2020-08-20 19:47:08', '2020-08-20 19:47:08'),
(6, 6, 3, '2020-08-19 17:38:29', '2020-08-19 17:38:29'),
(1, 7, 3, NULL, NULL),
(2, 7, 3, '2020-08-24 15:21:42', '2020-08-24 15:21:42'),
(3, 7, 3, '2020-08-24 15:22:09', '2020-08-24 15:22:09'),
(4, 7, 3, '2020-08-23 00:45:28', '2020-08-23 00:45:28'),
(5, 7, 3, '2020-08-20 19:47:08', '2020-08-20 19:47:08'),
(6, 7, 3, '2020-08-19 17:38:29', '2020-08-19 17:38:29'),
(1, 8, 3, NULL, NULL),
(3, 9, 3, '2020-08-24 15:22:09', '2020-08-24 15:22:09'),
(4, 9, 3, '2020-08-23 00:45:28', '2020-08-23 00:45:28'),
(5, 9, 3, '2020-08-20 19:47:08', '2020-08-20 19:47:08'),
(6, 9, 3, '2020-08-19 17:38:29', '2020-08-19 17:38:29'),
(3, 10, 3, '2020-08-24 15:22:09', '2020-08-24 15:22:09'),
(4, 10, 3, '2020-08-23 00:45:28', '2020-08-23 00:45:28'),
(5, 10, 3, '2020-08-20 19:47:08', '2020-08-20 19:47:08'),
(6, 10, 3, '2020-08-19 17:38:29', '2020-08-19 17:38:29'),
(2, 11, 3, '2020-08-24 15:21:42', '2020-08-24 15:21:42'),
(3, 11, 3, '2020-08-24 15:22:09', '2020-08-24 15:22:09'),
(4, 11, 3, '2020-08-23 00:45:28', '2020-08-23 00:45:28'),
(5, 11, 3, '2020-08-20 19:47:08', '2020-08-20 19:47:08'),
(2, 12, 3, '2020-08-24 15:21:42', '2020-08-24 15:21:42'),
(3, 12, 3, '2020-08-24 15:22:09', '2020-08-24 15:22:09'),
(2, 13, 3, '2020-08-24 15:21:42', '2020-08-24 15:21:42'),
(3, 13, 3, '2020-08-24 15:22:09', '2020-08-24 15:22:09');

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
CREATE TABLE IF NOT EXISTS `tasks` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `id_ot` int(11) DEFAULT NULL,
  `task_key` varchar(255) DEFAULT NULL,
  `original_estimate` varchar(255) DEFAULT NULL,
  `remaining_estimate` varchar(255) DEFAULT NULL,
  `resolved` datetime DEFAULT NULL,
  `task_status` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `task_type` varchar(255) DEFAULT NULL,
  `time_spent` varchar(255) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `file_type_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` longtext,
  `fix_version` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `FK6s1ob9k4ihi75xbxe2w0ylsdh` (`user_id`),
  KEY `FKhryvlhgbhxm4r1cq53y0ogyhv` (`file_type_id`),
  KEY `FKsfhn82y57i3k9uxww1s007acc` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`task_id`, `comment`, `created`, `date`, `id_ot`, `task_key`, `original_estimate`, `remaining_estimate`, `resolved`, `task_status`, `summary`, `task_type`, `time_spent`, `updated`, `user_id`, `file_type_id`, `project_id`, `created_at`, `description`, `fix_version`, `updated_at`) VALUES
(108, NULL, '2020-03-27 01:03:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10491', '2,0', '0,0', '2020-03-27 17:05:00', 'Resolved', 'ECOFISAS-10372 G02-T01- Tarea programada y Notificaciones', 'No es una incidencia', '2,0', '2020-03-27 17:05:00', 6, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(109, NULL, '2020-03-27 00:58:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10490', '2,0', '2,0', '2020-02-12 00:00:00', 'Resolved', 'ECOFISAS-10372 G01-T07-005 - Configuración de acciones y comprobaciones de las pantallas de MTO', 'No es una incidencia', '1,0', '2020-03-29 00:41:00', 6, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(110, NULL, '2020-03-27 00:57:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10489', '1,0', '1,0', NULL, 'Open', 'ECOFISAS-10372 G01-T07-004 - Configuración de acciones y comprobaciones de las pantallas de MTO', 'No es una incidencia', NULL, '2020-03-29 00:41:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(111, NULL, '2020-03-27 00:23:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10488', '3,0', '3,0', NULL, 'In Progress', 'ECOFISAS-10372 G01-T07-003 - Configuración de acciones y comprobaciones de las pantallas de MTO', 'No es una incidencia', NULL, '2020-03-30 12:02:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(112, NULL, '2020-03-27 00:05:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10487', '2,0', '0,2', NULL, 'In Progress', 'ECOFISAS-10372 G01-T07-002 - Configuración de acciones y comprobaciones de las pantallas de MTO', 'No es una incidencia', '3,8', '2020-03-27 20:40:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(113, NULL, '2020-03-26 22:50:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10485', '1,0', '0,0', '2020-01-27 20:09:00', 'Resolved', 'ECOFISAS-10372 G01-T07-001 - Nuevos servicios y constantes', 'No es una incidencia', '1,0', '2020-03-27 20:09:00', 6, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(114, NULL, '2020-03-26 22:40:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10484', '0,5', '0,0', '2020-03-27 20:05:00', 'Resolved', 'ECOFISAS-10372 G01-T06 - Creación de los mensajes', 'No es una incidencia', '0,5', '2020-03-27 20:35:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(115, NULL, '2020-03-26 15:02:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10481', '0,0', '0,0', '2020-03-26 22:37:00', 'Resolved', 'ECOFISAS-10372 INC_INT_01 - Varios Errores', 'Construcción', '1,0', '2020-03-26 22:37:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(116, NULL, '2020-03-26 00:19:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10451', '4,0', '3,0', NULL, 'In Progress', 'ECOFISAS-10372 G01-T04 - Configuración de los componentes de pantallas', 'No es una incidencia', '1,0', '2020-03-27 17:15:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(117, NULL, '2020-03-26 00:16:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10450', '2,0', '0,0', '2020-03-27 20:14:00', 'Resolved', 'ECOFISAS-10372 G01-T05 - Creación del nuevo perfil y asignación de permisos', 'No es una incidencia', '2,0', '2020-03-27 20:14:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(118, NULL, '2020-03-26 00:12:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10449', '1,5', '0,0', '2020-03-26 21:35:00', 'Resolved', 'ECOFISAS-10372 G01-T03 - Creacion de los DAO de las nuevas entidades', 'No es una incidencia', '1,5', '2020-03-26 21:35:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(119, NULL, '2020-03-26 00:11:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10448', '1,0', '0,0', '2020-03-26 21:35:00', 'Resolved', 'ECOFISAS-10372 G01-T02 - Cambios sobre los nombres de las pantallas en el menú', 'No es una incidencia', '1,0', '2020-03-26 21:35:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(120, NULL, '2020-03-25 23:59:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10447', '4,0', '0,0', '2020-03-26 21:58:00', 'Resolved', 'ECOFISAS-10372 G01-T01-002 - Creación nuevas tablas, entidades correspondientes y mapeo', 'No es una incidencia', '4,5', '2020-03-26 21:58:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(121, NULL, '2020-03-25 23:51:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10446', '0,5', '0,0', '2020-03-26 21:33:00', 'Resolved', 'ECOFISAS-10372 G01-T00-002 - Lectura del DT y de los requisitos', 'No es una incidencia', '0,5', '2020-03-26 21:33:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(122, NULL, '2020-03-25 23:49:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10445', '0,5', '0,0', '2020-03-26 16:24:00', 'Resolved', 'ECOFISAS-10372 G01-T00-001 - Lectura del DT y de los requisitos', 'No es una incidencia', '0,5', '2020-03-26 16:24:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(123, NULL, '2020-03-23 22:52:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10376', '3,0', '0,0', '2020-05-05 00:00:00', 'Resolved', 'ECOFISAS-10372 CLONE - Manual de usuario', 'No es una incidencia', '2,5', '2020-03-23 22:52:00', 6, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(124, NULL, '2020-03-23 22:52:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10375', NULL, NULL, NULL, 'Open', 'ECOFISAS-10372 CLONE - B01-TR03-Validación funcional (Plantilla OTC)', 'No es una incidencia', NULL, '2020-03-23 22:52:00', NULL, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(125, NULL, '2020-03-23 22:52:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10374', NULL, NULL, NULL, 'Open', 'ECOFISAS-10372 CLONE - B01-TR02-Ejecución plan de pruebas desarrollador', 'No es una incidencia', NULL, '2020-03-23 22:52:00', NULL, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(126, NULL, '2020-03-23 22:52:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10373', '6,0', '0,0', '2020-03-27 17:08:00', 'Resolved', 'ECOFISAS-10372 G01-T01-001 - Creación nuevas tablas, entidades correspondientes y mapeo', 'No es una incidencia', '6,0', '2020-03-27 17:08:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(127, NULL, '2020-03-23 01:54:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10356', '4,0', '1,0', NULL, 'In Progress', 'ECOFISAS-10348 Tests unitarios', 'No es una incidencia', '7,0', '2020-03-26 00:06:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(128, NULL, '2020-03-23 01:53:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10355', '2,0', '0,0', '2020-03-25 19:08:00', 'Resolved', 'ECOFISAS-10348 G01-T05 - Proceso de comprobación para mostrar el mensaje de aviso', 'No es una incidencia', '2,0', '2020-03-25 19:08:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(129, NULL, '2020-03-23 01:52:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10354', '4,0', '0,0', '2020-01-24 21:09:00', 'Resolved', 'ECOFISAS-10348 G01-T04 - Método de verificación del caso de sustitución de compra menor a expediente', 'No es una incidencia', '4,5', '2020-03-24 21:48:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(130, NULL, '2020-03-23 01:51:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10353', '2,0', '0,0', '2020-01-24 21:03:00', 'Resolved', 'ECOFISAS-10348 G01-T03 - Añadir la nueva restricción en las comprobaciones', 'No es una incidencia', '2,5', '2020-03-24 21:33:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(131, NULL, '2020-03-23 01:38:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10350', '0,5', '0,0', '2020-03-24 20:55:00', 'Resolved', 'ECOFISAS-10348 G01-T02 - Nuevo mensaje de advertencia', 'No es una incidencia', '0,5', '2020-03-24 21:10:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(132, NULL, '2020-03-23 01:38:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10349', '0,5', '0,0', '2020-03-24 20:53:00', 'Resolved', 'ECOFISAS-10348 G01-T01 - Lectura de los requisitos', 'No es una incidencia', '0,5', '2020-03-25 08:34:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(133, 'Preparación de datos para crear MUS', NULL, '2020-05-12 15:13:02', NULL, 'ECOFISAS-7814', '4.0', '0.0', '2020-03-24 20:53:00', 'Resolved', 'ECOFISAS-7810> G03-T03 - Manual de usuario', 'Desarrollo', '3.0', NULL, 8, 6, 4, '2020-05-12 15:13:06', 'Crear el manual del usuario', '30.0.0', '2020-05-12 15:13:06'),
(134, 'continuar con las pruebas', NULL, '2020-05-12 15:13:02', NULL, 'ECOFISAS-8361', '4.0', '2.0', NULL, 'In Progress', 'ECOFISAS-7810> G03-T02-Revisión y pruebas del analista', 'Desarrollo', '2.0', NULL, 8, 6, 4, '2020-05-12 15:13:06', 'Revisar y probar el desarrollo hecho', '30.0.0', '2020-05-12 15:13:06');

-- --------------------------------------------------------

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
CREATE TABLE IF NOT EXISTS `types` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `types`
--

INSERT INTO `types` (`type_id`, `description`, `type_name`, `created_at`, `updated_at`) VALUES
(1, 'description 1', 'Tarea cargable', NULL, NULL),
(2, 'description 2', 'Tarea no cargable', NULL, NULL),
(3, 'Description incidencia externa', 'Incidencia externa', NULL, NULL),
(4, 'Description incidencia interna', 'Incidencia interna', NULL, NULL),
(5, 'description Cambio de alcance', 'Cambio de alcance', NULL, NULL),
(6, 'Description Acc', 'Acc', NULL, NULL),
(7, 'Description Requerimiento', 'Requerimiento', NULL, NULL),
(8, 'Description Riesgo', 'Riesgo', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `jira_username` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_code` bigint(20) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `is_archived` bit(1) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKl4mppfnka6ftcu5u7p9p6ebsa` (`user_code`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKcuxvxnlkgcrxgsmbk3x302qch` (`jira_username`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `email`, `firstname`, `jira_username`, `lastname`, `password`, `phone`, `user_code`, `username`, `role_id`, `is_archived`, `created_at`, `updated_at`) VALUES
(1, 'admin1@gmail.com', 'wail', 'jira_admin1', 'el yousfi', '$2a$10$kqT8My2gCJn0xNRzMZvkxunz89PdSuDGyZhKsaoE5lYEfm9uRcpvG', '+212697935718', 100, 'admin1', 1, b'0', NULL, NULL),
(3, 'supervisor1@gmail.com', 'mohamed', 'jira_supervisor1', 'achkour', '$2a$10$l3EHu80DR0ZjL3PVOMg7ieJ7.8WEYzfUthoYsulQRQtLmlfbp.69O', '+212697935118', 101, 'supervisor11', 2, b'0', NULL, '2020-08-23 00:37:57'),
(4, 'senior1@gmail.com', 'moad', 'jira_senior1', 'khider', '$2a$10$0Ic8Q43vlI04vNeFzpfRCOIZsNfxMRJBz8G/A0T6irh7wkLNgb5Ui', '+212691475118', 102, 'senior11', 4, b'0', NULL, NULL),
(5, 'teamleader1@gmail.com', 'hicham', 'jira_teamleader1', 'sbihi', '$2a$10$x4GqPMMH6oHBZ5uNsQUokOb0hHtGV/uPI7l/JdtA82da93TUWml.y', '+212691479918', 103, 'sbiihii', 3, b'0', NULL, NULL),
(6, 'zakaria.mtougui@gmail.com', 'zakaria', 'Zakaria Mtougui', 'mtougui', '$2a$10$Z739lDeaKHpM7gF3dMdzjOvjPInOqGr0LF7lNytIhrNZaAohqLZv6', '+212611496744', 159541, 'developer11', 5, b'0', NULL, NULL),
(7, 'nafie.boudakkou@gmail.com', 'nafie', 'Nafie Boudakkou', 'boudakkou', '$2a$10$nonaMLJVMajxfNCJHYTZFuuIxsgIFR1rD6BCXaf0Or0ZVuEaM3kOe', '+212611666744', 177355, 'junior11', 6, b'0', NULL, NULL),
(8, 'najlae.sadrat@everis.com', 'Najlae', 'Najlae Sadrat', 'Sadrat', '$2a$10$24rFBPXg9AmyGDzU6tHb5eVRIKvaTNt4VUGjK1xs6azRO1QF39ZcO', '0633223322', 148515, 'teamleader11', 3, b'0', NULL, '2020-06-22 22:54:35'),
(9, 'souhaila.benomar@gmail.com', 'souhaila', 'Souhaila Benomar', 'Benomar', '$2a$10$ylfNf.y6ktXCp1PAqd7UieX32CgAg2KjpbbxhSCEa0LOcyhDvyaDS', '+212632121144', 184831, 'souhailabenomar', 6, b'0', NULL, NULL),
(12, 'hamide@gmail.com', 'hamid', 'hamidjira', 'ham', '$2a$10$71efuCQM3s/LD0.DACfeKeORBqOQx/tD6mDasWt7O2dkBc73AKzJu', NULL, 144444, 'hamide', 6, b'0', NULL, NULL),
(14, 'jalal@gmail.com', 'jalal', 'jalaljira', 'jalal', '$2a$10$aA7OvSC4.jsd/CZlfI3nmuxeQcHm/.3c2FTF2FVqibsSjaa23En26', '0632132132', 987000, 'jalal', 5, b'0', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_projects`
--

DROP TABLE IF EXISTS `user_projects`;
CREATE TABLE IF NOT EXISTS `user_projects` (
  `user_id` bigint(20) NOT NULL,
  `project_id` int(11) NOT NULL,
  KEY `FKof7c4wufgerxtl9moqol6c516` (`project_id`),
  KEY `FKr25ilmlcm8ugp8i3rogl6jp0l` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_projects`
--

INSERT INTO `user_projects` (`user_id`, `project_id`) VALUES
(4, 4),
(5, 1),
(7, 4),
(6, 4),
(9, 1),
(12, 1),
(14, 1),
(8, 4),
(3, 1),
(3, 4);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `equivalences`
--
ALTER TABLE `equivalences`
  ADD CONSTRAINT `FKft0b6ikw387477tx3qg7tfgb5` FOREIGN KEY (`file_type_id`) REFERENCES `types` (`type_id`);

--
-- Constraints for table `incidences`
--
ALTER TABLE `incidences`
  ADD CONSTRAINT `FKaaw4xljp45ujl5rib6u71tq9e` FOREIGN KEY (`assigned_user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKmuuyn26xem3qriljwhyj0xpyy` FOREIGN KEY (`caused_user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKnrpal2aoljkhlhp7l1t9ynk60` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`),
  ADD CONSTRAINT `FKs7xhs54k23t87qkp7yxyopsub` FOREIGN KEY (`file_type_id`) REFERENCES `types` (`type_id`);

--
-- Constraints for table `justifications`
--
ALTER TABLE `justifications`
  ADD CONSTRAINT `FKbhj6gpkn58kqmygyuo2m5pbsk` FOREIGN KEY (`justificator_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKo113xoleone88q7wb7e9q3c3i` FOREIGN KEY (`validator_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKtk4d55jdiv9c5is4unggd45al` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`);

--
-- Constraints for table `roles`
--
ALTER TABLE `roles`
  ADD CONSTRAINT `FKhond2qgppu2q1b50nwx6hy4g9` FOREIGN KEY (`child_role_role_id`) REFERENCES `roles` (`role_id`);

--
-- Constraints for table `role_features`
--
ALTER TABLE `role_features`
  ADD CONSTRAINT `FK17369effe8mrvk1ugtbrw8wly` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `FK8xtgg4pfqyam4ec1kchx7hjt4` FOREIGN KEY (`feature_id`) REFERENCES `features` (`feature_id`),
  ADD CONSTRAINT `FKa4r88t23vyipysnn7kdc14kpu` FOREIGN KEY (`accesslevel_id`) REFERENCES `accesslevels` (`accesslevel_id`);

--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `FK6s1ob9k4ihi75xbxe2w0ylsdh` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKhryvlhgbhxm4r1cq53y0ogyhv` FOREIGN KEY (`file_type_id`) REFERENCES `types` (`type_id`),
  ADD CONSTRAINT `FKsfhn82y57i3k9uxww1s007acc` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);

--
-- Constraints for table `user_projects`
--
ALTER TABLE `user_projects`
  ADD CONSTRAINT `FKof7c4wufgerxtl9moqol6c516` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`),
  ADD CONSTRAINT `FKr25ilmlcm8ugp8i3rogl6jp0l` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
