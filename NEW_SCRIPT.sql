-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 12, 2020 at 05:23 PM
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
(3, 'Read, write and edit', 3, NULL, NULL),
(4, 'Full access', 4, NULL, NULL);

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
(32, 'project', NULL, 'Project', 6, NULL, NULL, NULL),
(33, 'key', NULL, 'Key', 6, NULL, NULL, NULL),
(34, 'summary', 'Nombre', 'Title', 6, 1, NULL, NULL),
(35, 'description', 'Descripción', 'Description', 6, 3, NULL, NULL),
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
(92, 'taskType', 'Tipo Tarea', 'Tipo incidencia', 7, 2, NULL, NULL),
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `features`
--

INSERT INTO `features` (`feature_id`, `feature_name`, `url`, `title`, `parent_title`, `created_at`, `updated_at`) VALUES
(1, 'Input file management ', 'files/import', 'Import file', 'Files', NULL, NULL),
(2, 'User management', 'users', 'Users', NULL, NULL, NULL),
(3, 'Project management', 'projects', 'Projects', NULL, NULL, NULL),
(4, 'Output file management', 'files/export', 'Export file', 'Files', NULL, NULL),
(5, 'Permission management', 'permissions', 'Permissions', NULL, NULL, NULL),
(6, 'Dashboard', 'dashboard', 'Dashboard', NULL, NULL, NULL);

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
(25, NULL, NULL, '2020-05-12 16:04:24', '', 'Construcción', 'SIGLO-3170', 'ECOFISAS-9565', '', '0,0', NULL, '2020-01-30 18:54:00', 'Resolved', 'ECOFISAS-8381 INC_EXT_02 - Resolución SONAR', NULL, '2020-01-30 18:54:00', 7, 7, 3, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(26, NULL, NULL, '2020-05-12 16:04:24', 'Los errores detectados son los siguientes (algunos por el tema del SQLPlus que ya hemos comentado): \n• 001_NUEVA_TABLA_ALM_SISTEMAMENSAJE.sql ? Falla el create table por tener líneas vacías. La creación de la primary key falla porque el nombre ya se está usando en otra tabla. \n• 004_CREAR_NUEVO_PARAMETRO_EN_ORG_PARAMETRO.sql ? Fallo en el insert por incluir línea vacía. \n• 005_NUEVA_TABLA_ALM_HISTMENCARRUSELEP.sql ?Fallo en el create table por incluir líneas vacías. También falla porque los nombres de las foreing key ya se estaban usando en otro objeto (en concreto en un script previo). \n• 006_NUEVA_TABLA_ALM_HISTMENCARRUSELEPLINEA.sql ? Se llamaba inicialmente 006_NUEVA_TABLA_ALM_HISTMENCARRUSELEPLINEA .sql, es decir con un espacio en blanco. Se ha renombrado porque daba error al ejecutarse. También fallaba por las líneas vacías. La foreign key fallaba porque en el script 005 no se había creado primary key- \n• 007_NUEVA_TABLA_ALM_HISTMENCARRUSELEPERROR.sql ? Se ha renombrado igual que el punto anterior. También fallaba por líneas vacías en el create table. \n• 009_INSERT_ORG_TAREA.sql ? No se habían cerrado las comillas. \n• 010_PROCEDURE_PASOHISTORICOMENCARRUSELEP.sql ? Faltaba al final del procedure la barra / para indicar que ha finalizado la sentencia. Se ha eliminado la ejecución posterior del procedure “EXEC PASOHISTORICOMENCARRUSELEP;”. Lo he hablado con David y me ha comentado que no debería ejecutarse el procedure al lanzar el script. \n\nTiempo estimado : 0.5h ( se necesita solo subir los archivos corregidos por Borja) \n', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9531', 'ECOFISAS-9507', '0,0', NULL, '2020-01-29 12:54:00', 'Resolved', 'ECOFISAS-9110 INC_INT_31 - Errores en scripts', '0,1', '2020-02-24 16:28:00', 7, 7, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(27, NULL, NULL, '2020-05-12 16:04:24', 'Se han detectado errores en los scripts y se procede a su corrección: \n\nDO: \n- 001: El formato debe se ANSI, jamás debe ser utf-8 o no funcionará en SQLPLUS \n- 007: El script estaba mal a la hora de añadir las columnas de la tabla \nUNDO: \n- 003: La \"/\" estaba mal colocada, encima del END del script \n- 007: Faltaba realizar el drop de la secuencia', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9554', '', '0,0', NULL, '2020-01-30 17:14:00', 'Resolved', 'ECOFISAS-9110 INC_INT_32- Errores en los scripts de la tarea.', NULL, '2020-01-31 12:42:00', 7, 7, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(28, NULL, NULL, '2020-05-12 16:04:24', 'Cuando haber un registro en tabla ALM_MENMOVSTOCK con el motivo de error \"Las existencias que indica son superiores a las que hay en SIGLO\", en la busqueda de pantalla Consulta de Recuentos RFID se muestra el error NPE \n\n\n', 'No es una incidencia', 'SIGLO-2937', 'ECOFISAS-9553', 'ECOFISAS-9552', '0,0', NULL, '2020-02-03 17:11:00', 'Resolved', 'ECOFISAS-9110 INC_INT_30 - NPE cuando buscar registros con incidencias', NULL, '2020-02-03 17:11:00', 7, 7, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(29, NULL, NULL, '2020-05-12 16:04:24', 'No se genera el mensaje de devolución cuando se ejecuta una OT con destino físico a una ubicación de una zona integrada. \nPaso. \nSe registra la solicitud de devolución ORIGEN ADC destino AC ubicación carrusel. \nSe edita la OT y se le incluye la ubicación destino.8RLQP \nSe revisa la tabla de select * FROM siglo.ALM_MENCARRUSELEP cmsj order by fechaenvio desc ; y no ha generado mensaje de devolución. \nSe revisa la OT y esta ejecutada correctamente. \n\n\n\n', 'Construcción', 'SIGLO-2949', 'ECOFISAS-9698', 'ECOFISAS-9625', '0,0', NULL, '2020-02-10 17:20:00', 'Resolved', 'ECOFISAS-7609 INC_EXT_22- No genera mensaje de orden de trabajo de devolución.', '5,0', '2020-02-11 08:52:00', 6, 6, 3, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(30, NULL, NULL, '2020-05-12 16:04:24', 'No se cumple el requisito. \n005-FEF-3965-01-04-01 \nSi el genérico de centro incluido en la OT de devolución, pertenece a una ubicación física de un carrusel integrado, deberá crearse la OT con la línea cumplimentada con la ubicación física automáticamente, para que el gestor no tenga que completar la ubicación. \nDevolución 1479036 (todas las solicitudes se han creado con fecha de 06/02/2020). \nGC E67698 --> L-LOGISONE006 \n\n\n', 'Análisis', 'SIGLO-2949', 'ECOFISAS-9696', 'ECOFISAS-9625', '0,0', NULL, '2020-02-10 17:22:00', 'Resolved', 'ECOFISAS-7609 INC_EXT_21- No asocia automáticamente la ubicación a la OT de devolución', '2,5', '2020-02-11 09:03:00', 6, 6, 3, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(31, NULL, NULL, '2020-05-12 16:04:24', 'Estimación 2h', 'Construcción', 'SIGLO-3250', 'ECOFISAS-9225', 'ECOFISAS-9258', '0,0', NULL, '2020-01-10 10:45:00', 'Resolved', 'ECOFISAS-8933 INC_INT_06 - No se tiene en cuenta el destino para los criterios de búsqueda', '2,0', '2020-01-30 14:43:00', 7, 7, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(32, NULL, NULL, '2020-05-12 16:04:24', 'Se detecta un error al acceder a los detalles de la solicitud de devolución. \nPara reproducirlo: Acceder Gestión de Solicitudes de Devolución --> Consultar OT 1479033 y luego pulsar en el botón detalle. \n\"com.hp.geos.almacen.modelo.objetosNegocio.implementacion.SolicitudDevolucionImpl\" \n', 'Construcción', 'SIGLO-2949', 'ECOFISAS-9692', 'ECOFISAS-9625', '0,0', NULL, '2020-02-07 15:35:00', 'Resolved', 'ECOFISAS-7609 INC_EXT_18- Error al acceder al detalle de una solicitud de devolución', '0,5', '2020-02-07 15:36:00', 6, 6, 3, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(33, NULL, NULL, '2020-05-12 16:04:24', '', 'Construcción', 'SIGLO-3264', 'ECOFISAS-9435', 'ECOFISAS-9394, ECOFISAS-9858', '0,0', NULL, '2020-01-27 08:51:00', 'Resolved', 'ECOFISAS-9059 INC_INT_03 No se ejecuta la tarea de generación de pedidos externos en segundo plano', '16,0', '2020-02-20 22:59:00', 6, 6, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(34, NULL, NULL, '2020-05-12 16:04:24', '', 'Construcción', 'SIGLO-3264', 'ECOFISAS-9394', 'ECOFISAS-9358, ECOFISAS-9435', '0,0', NULL, '2020-01-23 17:03:00', 'Resolved', 'ECOFISAS-9059 INC_INT_02 No se muestran los nuevos campos observaciones por proveedor y recordatorio', '2,0', '2020-02-11 17:39:00', 6, 6, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(35, NULL, NULL, '2020-05-12 16:04:24', 'Columna generación pedido de pantalla Consulta de Recuentos RFID se muestra valores incorrectos, debe mostrar valores Sí o No \nCódigo mensaje no muestra ningún valor \nen la exportación de excel se muestra correctamente el valor de la columna generación pedido y no se muestra el valor de la columna código mensaje \n\nDatos de pruebas en Nube 1 \nPlataforma Sevila Almacen _[DS] ALMACEN DISTRITO A.P. SEVILLA \nfechas 21/012020 hasta 23/01/2020 \n\nResponsable ==> Nafie \n', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9538', 'ECOFISAS-9537, ECOFISAS-9552', '0,0', NULL, '2020-02-03 17:26:00', 'Resolved', 'ECOFISAS-9110 INC_INT_28 - se muestran valores incorrectos en las columnas generación pedido y código mensaje de pantalla Consulta de Recuentos RFID', '2,0', '2020-02-03 18:29:00', 7, 7, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36'),
(36, NULL, NULL, '2020-05-12 16:04:24', 'No se muestra el aviso que debe haber un unico articulo en pantalla Consulta de Recuentos RFID cuando seleccionar el check de datos historicos y no hay GC \n\nResponsable ==> Nafie \n', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9537', 'ECOFISAS-9536, ECOFISAS-9538', '0,0', NULL, '2020-02-05 01:05:00', 'Resolved', 'ECOFISAS-9110 INC_INT_27 - No se muestra el aviso que debe haber un unico articulo en pantalla Consulta de Recuentos RFID', '0,2', '2020-02-05 01:05:00', 7, 7, 4, 4, '2020-05-12 16:04:36', '2020-05-12 16:04:36');

-- --------------------------------------------------------

--
-- Table structure for table `justifications`
--

DROP TABLE IF EXISTS `justifications`;
CREATE TABLE IF NOT EXISTS `justifications` (
  `justification_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `is_validated` bit(1) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `validation_date` date DEFAULT NULL,
  `justificator_id` bigint(20) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `validator_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`justification_id`),
  KEY `FKbhj6gpkn58kqmygyuo2m5pbsk` (`justificator_id`),
  KEY `FKtk4d55jdiv9c5is4unggd45al` (`project_id`),
  KEY `FKo113xoleone88q7wb7e9q3c3i` (`validator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`project_id`, `description`, `is_closed`, `project_name`, `created_at`, `updated_at`) VALUES
(1, 'desc1', b'0', 'project1', NULL, NULL),
(2, 'desc2', b'0', 'project2', NULL, NULL),
(4, 'SAS Description', b'0', 'ECOFISAS - EcoFI-SAS (Mantenimiento de los sistemas económico financieros...', NULL, NULL),
(5, 'desc nouv projet updated', b'1', 'nouveau projet updated', NULL, NULL);

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
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'ROLE_ADMIN', NULL, NULL),
(2, 'ROLE_SUPERVISOR', NULL, NULL),
(3, 'ROLE_TEAMLEADER', NULL, NULL),
(4, 'ROLE_SENIOR', NULL, NULL),
(5, 'ROLE_DEVELOPER', NULL, NULL),
(6, 'ROLE_JUNIOR', NULL, NULL);

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
(3, 1, 4, NULL, NULL),
(1, 2, 4, NULL, NULL),
(1, 3, 4, NULL, NULL),
(3, 3, 1, NULL, NULL),
(3, 4, 4, NULL, NULL),
(1, 5, 4, NULL, NULL);

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
(108, NULL, '2020-03-27 01:03:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10491', '2,0', '0,0', '2020-03-27 17:05:00', 'Resolved', 'ECOFISAS-10372 G02-T01- Tarea programada y Notificaciones', 'No es una incidencia', '2,0', '2020-03-27 17:05:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(109, NULL, '2020-03-27 00:58:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10490', '2,0', '2,0', NULL, 'Open', 'ECOFISAS-10372 G01-T07-005 - Configuración de acciones y comprobaciones de las pantallas de MTO', 'No es una incidencia', NULL, '2020-03-29 00:41:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(110, NULL, '2020-03-27 00:57:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10489', '1,0', '1,0', NULL, 'Open', 'ECOFISAS-10372 G01-T07-004 - Configuración de acciones y comprobaciones de las pantallas de MTO', 'No es una incidencia', NULL, '2020-03-29 00:41:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(111, NULL, '2020-03-27 00:23:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10488', '3,0', '3,0', NULL, 'In Progress', 'ECOFISAS-10372 G01-T07-003 - Configuración de acciones y comprobaciones de las pantallas de MTO', 'No es una incidencia', NULL, '2020-03-30 12:02:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(112, NULL, '2020-03-27 00:05:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10487', '2,0', '0,2', NULL, 'In Progress', 'ECOFISAS-10372 G01-T07-002 - Configuración de acciones y comprobaciones de las pantallas de MTO', 'No es una incidencia', '3,8', '2020-03-27 20:40:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(113, NULL, '2020-03-26 22:50:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10485', '1,0', '0,0', '2020-03-27 20:09:00', 'Resolved', 'ECOFISAS-10372 G01-T07-001 - Nuevos servicios y constantes', 'No es una incidencia', '1,0', '2020-03-27 20:09:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(114, NULL, '2020-03-26 22:40:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10484', '0,5', '0,0', '2020-03-27 20:05:00', 'Resolved', 'ECOFISAS-10372 G01-T06 - Creación de los mensajes', 'No es una incidencia', '0,5', '2020-03-27 20:35:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(115, NULL, '2020-03-26 15:02:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10481', '0,0', '0,0', '2020-03-26 22:37:00', 'Resolved', 'ECOFISAS-10372 INC_INT_01 - Varios Errores', 'Construcción', '1,0', '2020-03-26 22:37:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(116, NULL, '2020-03-26 00:19:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10451', '4,0', '3,0', NULL, 'In Progress', 'ECOFISAS-10372 G01-T04 - Configuración de los componentes de pantallas', 'No es una incidencia', '1,0', '2020-03-27 17:15:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(117, NULL, '2020-03-26 00:16:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10450', '2,0', '0,0', '2020-03-27 20:14:00', 'Resolved', 'ECOFISAS-10372 G01-T05 - Creación del nuevo perfil y asignación de permisos', 'No es una incidencia', '2,0', '2020-03-27 20:14:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(118, NULL, '2020-03-26 00:12:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10449', '1,5', '0,0', '2020-03-26 21:35:00', 'Resolved', 'ECOFISAS-10372 G01-T03 - Creacion de los DAO de las nuevas entidades', 'No es una incidencia', '1,5', '2020-03-26 21:35:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(119, NULL, '2020-03-26 00:11:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10448', '1,0', '0,0', '2020-03-26 21:35:00', 'Resolved', 'ECOFISAS-10372 G01-T02 - Cambios sobre los nombres de las pantallas en el menú', 'No es una incidencia', '1,0', '2020-03-26 21:35:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(120, NULL, '2020-03-25 23:59:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10447', '4,0', '0,0', '2020-03-26 21:58:00', 'Resolved', 'ECOFISAS-10372 G01-T01-002 - Creación nuevas tablas, entidades correspondientes y mapeo', 'No es una incidencia', '4,5', '2020-03-26 21:58:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(121, NULL, '2020-03-25 23:51:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10446', '0,5', '0,0', '2020-03-26 21:33:00', 'Resolved', 'ECOFISAS-10372 G01-T00-002 - Lectura del DT y de los requisitos', 'No es una incidencia', '0,5', '2020-03-26 21:33:00', 9, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(122, NULL, '2020-03-25 23:49:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10445', '0,5', '0,0', '2020-03-26 16:24:00', 'Resolved', 'ECOFISAS-10372 G01-T00-001 - Lectura del DT y de los requisitos', 'No es una incidencia', '0,5', '2020-03-26 16:24:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(123, NULL, '2020-03-23 22:52:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10376', NULL, NULL, NULL, 'Open', 'ECOFISAS-10372 CLONE - Manual de usuario', 'No es una incidencia', NULL, '2020-03-23 22:52:00', NULL, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(124, NULL, '2020-03-23 22:52:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10375', NULL, NULL, NULL, 'Open', 'ECOFISAS-10372 CLONE - B01-TR03-Validación funcional (Plantilla OTC)', 'No es una incidencia', NULL, '2020-03-23 22:52:00', NULL, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(125, NULL, '2020-03-23 22:52:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10374', NULL, NULL, NULL, 'Open', 'ECOFISAS-10372 CLONE - B01-TR02-Ejecución plan de pruebas desarrollador', 'No es una incidencia', NULL, '2020-03-23 22:52:00', NULL, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(126, NULL, '2020-03-23 22:52:00', '2020-05-12 15:02:02', NULL, 'ECOFISAS-10373', '6,0', '0,0', '2020-03-27 17:08:00', 'Resolved', 'ECOFISAS-10372 G01-T01-001 - Creación nuevas tablas, entidades correspondientes y mapeo', 'No es una incidencia', '6,0', '2020-03-27 17:08:00', 7, 7, 4, '2020-05-12 15:02:16', NULL, NULL, '2020-05-12 15:02:16'),
(127, NULL, '2020-03-23 01:54:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10356', '4,0', '1,0', NULL, 'In Progress', 'ECOFISAS-10348 Tests unitarios', 'No es una incidencia', '7,0', '2020-03-26 00:06:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(128, NULL, '2020-03-23 01:53:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10355', '2,0', '0,0', '2020-03-25 19:08:00', 'Resolved', 'ECOFISAS-10348 G01-T05 - Proceso de comprobación para mostrar el mensaje de aviso', 'No es una incidencia', '2,0', '2020-03-25 19:08:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(129, NULL, '2020-03-23 01:52:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10354', '4,0', '0,0', '2020-03-24 21:09:00', 'Resolved', 'ECOFISAS-10348 G01-T04 - Método de verificación del caso de sustitución de compra menor a expediente', 'No es una incidencia', '4,5', '2020-03-24 21:48:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(130, NULL, '2020-03-23 01:51:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10353', '2,0', '0,0', '2020-03-24 21:03:00', 'Resolved', 'ECOFISAS-10348 G01-T03 - Añadir la nueva restricción en las comprobaciones', 'No es una incidencia', '2,5', '2020-03-24 21:33:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(131, NULL, '2020-03-23 01:38:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10350', '0,5', '0,0', '2020-03-24 20:55:00', 'Resolved', 'ECOFISAS-10348 G01-T02 - Nuevo mensaje de advertencia', 'No es una incidencia', '0,5', '2020-03-24 21:10:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(132, NULL, '2020-03-23 01:38:00', '2020-05-12 15:12:31', NULL, 'ECOFISAS-10349', '0,5', '0,0', '2020-03-24 20:53:00', 'Resolved', 'ECOFISAS-10348 G01-T01 - Lectura de los requisitos', 'No es una incidencia', '0,5', '2020-03-25 08:34:00', 9, 7, 4, '2020-05-12 15:12:44', NULL, NULL, '2020-05-12 15:12:44'),
(133, 'Preparación de datos para crear MUS', NULL, '2020-05-12 15:13:02', NULL, 'ECOFISAS-7814', '4.0', '2.0', NULL, 'In Progress', 'ECOFISAS-7810> G03-T03 - Manual de usuario', 'Desarrollo', '2.0', NULL, 8, 6, 4, '2020-05-12 15:13:06', 'Crear el manual del usuario', '30.0.0', '2020-05-12 15:13:06'),
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
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `email`, `firstname`, `jira_username`, `lastname`, `password`, `phone`, `user_code`, `username`, `role_id`, `is_archived`, `created_at`, `updated_at`) VALUES
(1, 'admin1@gmail.com', 'wail', 'jira_admin1', 'el yousfi', '$2a$10$kqT8My2gCJn0xNRzMZvkxunz89PdSuDGyZhKsaoE5lYEfm9uRcpvG', '+212697935718', 100, 'admin1', 1, b'0', NULL, NULL),
(3, 'supervisor1@gmail.com', 'mohamed', 'jira_supervisor1', 'achkour', '$2a$10$l3EHu80DR0ZjL3PVOMg7ieJ7.8WEYzfUthoYsulQRQtLmlfbp.69O', '+212697935118', 101, 'supervisor1', 2, b'0', NULL, NULL),
(4, 'senior1@gmail.com', 'moad', 'jira_senior1', 'khider', '$2a$10$0Ic8Q43vlI04vNeFzpfRCOIZsNfxMRJBz8G/A0T6irh7wkLNgb5Ui', '+212691475118', 102, 'senior1', 4, b'0', NULL, NULL),
(5, 'teamleader1@gmail.com', 'hicham', 'jira_teamleader1', 'sbihi', '$2a$10$x4GqPMMH6oHBZ5uNsQUokOb0hHtGV/uPI7l/JdtA82da93TUWml.y', '+212691479918', 103, 'teamleader1', 3, b'0', NULL, NULL),
(6, 'zakaria.mtougui@gmail.com', 'zakaria', 'Zakaria Mtougui', 'mtougui', '$2a$10$Z739lDeaKHpM7gF3dMdzjOvjPInOqGr0LF7lNytIhrNZaAohqLZv6', '+212611496744', 159541, 'zakariamtougui', 5, b'0', NULL, NULL),
(7, 'nafie.boudakkou@gmail.com', 'nafie', 'Nafie Boudakkou', 'boudakkou', '$2a$10$nonaMLJVMajxfNCJHYTZFuuIxsgIFR1rD6BCXaf0Or0ZVuEaM3kOe', '+212611666744', 177355, 'nafieboudakkou', 6, b'0', NULL, NULL),
(8, 'najlae.sadrat@gmail.com', 'najlae', 'Najlae Sadrat', 'sadrat', '$2a$10$24rFBPXg9AmyGDzU6tHb5eVRIKvaTNt4VUGjK1xs6azRO1QF39ZcO', '+212647011491', 148515, 'najlaesadrat', 3, b'0', NULL, NULL),
(9, 'souhaila.benomar@gmail.com', 'souhaila', 'Souhaila Benomar', 'Benomar', '$2a$10$eHhT9vv6jG7N4WgvhvsOReslh5B5a80Q2XfCowkStRZNFY5rkZ1KO', '+212632121144', 184831, 'souhailabenomar', 6, b'0', NULL, NULL),
(10, 'new2@gmail.com', 'new2', 'jiranew2', 'new2', '$2a$10$qYD/57tljNBHicanrrcnge0lPVJh5YZPl37P9wbFyuq7Sql5hGcHS', NULL, 555555, 'new2', 6, b'0', NULL, NULL),
(11, 'new3@gmail.com', 'new3', 'jiranew3', 'new3', '$2a$10$mnCXzc/yIdFyx0ZWPmMn2ebZvq2R2PpyiX.umKHhQj1bAbtEgPXMG', NULL, 142514, 'new3', 6, b'0', NULL, NULL),
(12, 'hamide@gmail.com', 'hamid', 'hamidjira', 'ham', '$2a$10$71efuCQM3s/LD0.DACfeKeORBqOQx/tD6mDasWt7O2dkBc73AKzJu', NULL, 144444, 'hamide', 6, b'0', NULL, NULL),
(13, 'new4@gmail.com', 'new4', 'jiranew4', 'new4', '$2a$10$c4wz/9vEfZ45B6JzKTK14Oxsk8.r.FqVRzbRJR8j5fdxleT0N1Ddi', NULL, 444444, 'new4', 6, b'0', NULL, NULL),
(14, 'jalal@gmail.com', 'jalal', 'jalaljira', 'jalal', '$2a$10$aA7OvSC4.jsd/CZlfI3nmuxeQcHm/.3c2FTF2FVqibsSjaa23En26', '0632132132', 987000, 'jalal', 5, b'0', NULL, NULL),
(15, 'karim@gmail.com', 'karim', 'karimjira', 'karim', '$2a$10$erurPc12ClxwnOfpV2Pgze1G0rPw8WznQAzW7.EApQLtqTWjlgMq.', '0698712354', 556644, 'karim', 5, b'0', NULL, NULL),
(16, 'yousef@gmail.com', 'yousef', 'yousefjira', 'yousef', '$2a$10$OVVCI8OUTn23u1ONUJtc9.l2um751pQ3GIe77GaNMDYb6PUEzPO7y', '+212685236911', 99991, 'yousef', 6, b'0', NULL, NULL),
(17, 'amin@gmail.com', 'amin', 'aminjira', 'amin', '$2a$10$coOaSyJ8AgTQBy2U38pL0OobiPoTTpAUwayCu0YtdFvOgSDOY/uk.', '0654126555', 987987, 'amin', 6, b'0', NULL, NULL),
(18, 'taha@gmail.com', 'taha', 'tahajira', 'taha', '$2a$10$.lP7aaLy9pPl8RQYrTc8dOCpkrUpkf0gGvS4OJpE4QCkjJM8P9T6u', '0632112366', 1471470, 'taha', 6, b'0', NULL, NULL),
(19, 'walid@gmail.com', 'walid', 'walidjira', 'walid', '$2a$10$El15cYbFFN.XlWt72yEZN.D3JdkJ0UC9vtNeZ59/LpJITMgzLeSIu', '0654111444', 199998, 'walid', 6, b'0', NULL, NULL),
(20, 'dd@dd.com', 'dddd', 'ddjira', 'ddd', '$2a$10$suDwhYyhFWMHnKPgAESi8uwjfNHhHzO2nLJAu3C51aetex1.bNj.y', '0655555555', 191991, 'ddd', 6, b'0', NULL, NULL),
(21, 'qqq@qq', 'qqq', 'qqq', 'qqq', '$2a$10$cZ4LRNYOsH8ryra9RlgcJObt4r9P9StVs9CqrQOgI6B0I6EOAd0hu', '0255555555', 5551110, 'qqq', 6, b'0', NULL, NULL),
(22, 'pppp@ppp', 'ppp', 'ppp', 'ppp', '$2a$10$OCyEgIvjeGAuEVgWJMPXQO6gdoca/yYtMVo1jT5VXovlkofF1b6l.', '0222222221', 777333, 'ppp', 6, b'0', NULL, NULL),
(23, 'ooo@oooo.com', 'ooo', 'ooo', 'ooo', '$2a$10$A0S34yhBeKRr/6fa0d9Qd.X2lLmbs28PxruuuNFDF4sp8M.3/nYBq', '0666666666', 55446633, 'ooo', 6, b'0', NULL, NULL),
(24, 'kkk@kkk.com', 'kkk', 'kkk', 'kkk', '$2a$10$7KRcpSQSaZix3iOv3RcBieyattF4EpWJhZdWyMNQDM/7XXLzy0ANK', '0665565656', 141235, 'kkk', 6, b'0', NULL, NULL),
(25, 'ccc@ccc', 'ccc', 'ccc', 'ccc', '$2a$10$CS/aVaoFUb28QZQBEMJ3RePgUyV3tX8r6gLx0ZWR2swivzBaEi2UC', '0655555555', 5541369, 'ccc', 6, b'0', NULL, NULL),
(26, 'upd4@gmail.com', 'upd4', 'upd4', 'upd4', '$2a$10$gw3KdV5mab1oIK16XfvEb.Y.dQhTPNEoBt25WH3KdlLMcXOikmru2', '0614141414', 66666, 'upd4', 5, b'0', NULL, NULL),
(27, 'fff@fff', 'fff', 'fff', 'fff', '$2a$10$nvJpiDA7pM2jBwNmB7IoS./okoJdFXm.DTidftlhQO8tHGk/E0Q.i', '0655256566', 2140032, 'fff', 5, b'0', NULL, NULL),
(28, 'uuu@uuu', 'uuu', 'uuu', 'uuu', '$2a$10$t6Z0QgDsSW8937wAXfERBuQRi3zanGzAI4zwwJNg/4pjHGOT8tJnm', '0555555555', 1400225, 'uuu', 6, b'0', NULL, NULL),
(29, 'rrr@rrr', 'rrr', 'rrr', 'rrr', '$2a$10$Q04.xzSd/Rvjw1OoTUxbeuMQn3zNn2lEKvRbHNzPIRTBnuRr8kh1m', '0655555555', 250112, 'rrr', 5, b'0', NULL, NULL),
(30, 'lala@gmail.com', 'lala', 'lalala', 'lala', '$2a$10$.N1NqjDGO6vNuz9Mg/RIO.i12z2o.2uMYS48mMhQhkdQbbW3I5dYi', '+212521412536', 151515, 'lalalala', 5, b'0', NULL, NULL),
(31, 'oaoa@oaoa.com', 'oaoa', 'oaoa', 'oaoa', '$2a$10$dIfMbGkScL8yWO/cxx8mSuzaceyCMiO9ETpomfMw0/kGmhumKipDy', '0656555412', 515195, 'oaoaoa', 6, b'0', NULL, NULL),
(32, 'nana@gmail.com', 'nana', 'nana', 'nana', '$2a$10$vDU7WxZozEJxx7W5ZZ4M1O862bkGYrKDe7G3kjg5x.UfwWjilFQim', '0654454545', 141236, 'nana', 4, b'0', NULL, NULL),
(33, 'fafa@fafa.com', 'fafa', 'fafa', 'fafa', '$2a$10$ndfd5LKk.egEmCfkqp0QDOhHZ8M/zf29pDCr7Wjca5l.xXhNUkTIK', '0625555544', 25588, 'fafa', 5, b'0', NULL, NULL),
(34, 'plpl@pl.com', 'plpl', 'plpl', 'plpl', '$2a$10$wSmP3I7aLoeRM6VAasksTupwgp7JnCuUrAJ31K179W//iuUJcY74S', '0622232155', 1472511, 'plpl', 6, b'0', NULL, NULL),
(35, 'nmnm@nm.com', 'nmnm', 'nmnm', 'nmnm', '$2a$10$6S6EPd9WfeKM6z.8yvCcFOj7zANPK/.Zwp8mWtVbaKOiAIT1LUrK2', '0987745555', 1425999, 'nmnm', 6, b'0', NULL, NULL),
(36, 'lll@lll.com', 'lll', 'lll', 'lll', '$2a$10$/jiaH2p1sbiSrcTdwGe42ODejTy1vid7puCltH7DVmodrvSsrCGDi', '0625412222', 419985, 'lll', 5, b'0', NULL, NULL),
(37, 'dada@gmail.com', 'dada', 'dadajira', 'dada', '$2a$10$ceQIYtZ46ApV1QfVjH5OIuOccYyTeHhEy/QIo0PE0ZNq0lK3CgYc2', '+212547855561', 1479520, 'dada', 5, b'0', NULL, NULL),
(38, 'caca@caca.com', 'caca', 'caca', 'caca', '$2a$10$.SVXoyxVFleIqgjDJ8rvwOGEpYYcx6UJjaa8abwClCd4tbc01yUpe', '0254125412', 541000, 'caca', 6, b'0', NULL, NULL),
(39, 'mmm@mmm.com', 'mmm', 'mmm', 'mmm', '$2a$10$JkfxyKxl0XIcp2DEpU99hOvwyJr/V5Si2vmQ7kolt9wNZBml4hNYO', '0654127777', 1479856, 'mmm', 6, b'0', NULL, NULL),
(40, 'cccc@cccc.com', 'cccc', 'cccc', 'cccc', '$2a$10$21lHgbA/.3RmLjKZnxpiVu25qt3UxlVMa0GUsoFUMqGkGgwm/fHia', '0658777777', 147000, 'cccc', 6, b'0', NULL, NULL),
(41, 'ttt@dd.com', 'ttt', 'ttt', 'ttt', '$2a$10$e8MLOI0EDLerzIqrCcIQP.56dPWV.z/WKZGgeQg9sk5H.s6nhASKK', '0660555555', 140000, 'ttt', 6, b'0', NULL, NULL),
(42, 'bbb@cc.com', 'bbb', 'bbb', 'bbb', '$2a$10$KSfRPj80m04KoARe6FvU/egdMv1XQH/rdDX/3BYvO6dgkUb4qQscu', '0654411111', 200001, 'bbb', 6, b'0', NULL, NULL),
(43, 'ffff@ff.com', 'ffff', 'ffff', 'ffff', '$2a$10$gps0v6V57cEmMIEbOuH55OuSE6PZ6yC4dvoliBKW/NkrD7jv2uEvC', '0622222221', 1000220, 'ffff', 6, b'0', NULL, NULL),
(44, 'jam@jam.com', 'jam', 'jam', 'jam', '$2a$10$GofOpoXuP9KqIukEwJuF1uJwbGQbaPAxRIjAT5poPEHbqCsraSkXG', '0655888888', 14006666, 'jam', 6, b'0', NULL, NULL),
(45, 'kmkm@kmkm.com', 'kmkm', 'kmkm', 'kmkm', '$2a$10$t17V.H.CVuA/D7ogESMxOOGrZM6LMH44oZHy6Rdmj7I7caps8mHXC', '0655554440', 100022109, 'kmkm', 5, b'0', NULL, NULL),
(46, 'wcwc@gmail.com', 'wcwc', 'wcwc', 'wcwc', '$2a$10$2HUIiXVbJRZYWKiZaGS2WeNKsWu.rfs1.o6wAt9PPIwml.n6NEyQm', '0225558899', 100588883, 'wcwc', 6, b'0', NULL, NULL),
(47, 'pu@ppu.com', 'pupu', 'pupu', 'pupu', '$2a$10$Wn85Q6lORIYKaFWh.G8uWuCWwP9ZWSESQVXi/Gjf6VN2Ful8cEv5u', '0544555477', 12222777, 'pupu', 6, b'0', NULL, NULL),
(48, 'dfdf@dd.com', 'dfdf', 'dfdf', 'dfdf', '$2a$10$0IQmjwo1Y.6D1W2cDRucauQuw9SQDAv0eeGT88B9j/W2vGk9Jvt/q', '0655477831', 1978830, 'dfdf', 6, b'0', NULL, NULL),
(49, 'thth@th.com', 'thth', 'thth', 'thth', '$2a$10$NKywgmaK9l7jer9dHqscs.MTJbAXcoswzM0rhdjIFQG3Yi2U99Lxy', '0658899999', 8799889, 'thth', 6, b'0', NULL, NULL),
(50, 'vbvb@ddd.com', 'vbvb', 'vbvb', 'vbvb', '$2a$10$ymZBRQIBL8jpf3AKqvfOXu4p7Vzff8pTEEEIDHye6EYsoM4o5YwDm', '0699999998', 1918730, 'vbvb', 4, b'0', NULL, NULL),
(51, 'kkkkkkk@dddckk', 'kkkkkk', 'kkkkkkkk', 'kkkkkk', '$2a$10$0Hjugu4CgNgdKgHtLQad4uvjHxxFigzG6btDiwcYVnn9fEhMcKZ1C', '0699989850', 9889800, 'kkkkkk', 6, b'0', NULL, NULL),
(52, 'qzqzqz@qzqz', 'qzqzqz', 'qzqzzq', 'qzqzqz', '$2a$10$pZOfwdpowOrW.ieCrNJvWuXU6VD4fo4oiC0k/.0J3dcf5HwX9Mua.', '0697123654', 9995510, 'qzqzqz', 6, b'0', NULL, NULL),
(53, 'ghhj@ghhj', 'ghhj', 'ghhj', 'ghhj', '$2a$10$iLcVlgvmgc/Ckgbngiw9Uu54VsGdC7adhZqTsapzYO7SCPBX2i4gC', '0654123000', 9870000, 'ghhj', 6, b'0', NULL, NULL),
(54, 'lqlq@lqlq', 'lqlq', 'lqlq', 'lqlq', '$2a$10$zrx1pKjfM1nYVFMs4.rn5OfxQP9HKilHuODhFjyi0sLrEZZI7mSgW', '0654777882', 2587410, 'lqlq', 6, b'0', NULL, NULL),
(55, 'meme@meme', 'meme', 'meme', 'meme', '$2a$10$cdLNH4C1W94JHmilCU7yW.9NnFRkwMV13/.A1WIVsGo924phJ3eE6', '0658741300', 15935722, 'meme', 6, b'0', NULL, NULL),
(56, 'sisi@sisi', 'sisi', 'sisi', 'sisi', '$2a$10$iyfZ.NnVS2SkqKuC.gWRWeSr7M/tkMQ2hWXpe.jazvaqeV8ez0CLK', '0658111999', 1593366, 'sisi', 6, b'0', NULL, NULL),
(57, 'sql@sql', 'sql', 'sqll', 'sql', '$2a$10$1oRR3UBXLp4Lux0LEc7eB.N9.j1ah26KqOj.dKuO8Z.wreIwVKa6K', '0588778895', 1919, 'sql', 5, b'0', NULL, NULL),
(58, 'hdhd@hdhd', 'hdhd', 'hdhd', 'hdhd', '$2a$10$TrjqAC/xCXndGG.1U68ZoekY3YbKP7BFvN.bUmUFvYjGyQdWao3F.', '0987444475', 111199999, 'hdhd', 5, b'0', NULL, NULL),
(59, 'zzzzz@zzz', 'zzzzz', 'zzzzz', 'zzzzz', '$2a$10$oPDfOXYDM0qznvQhgd.r3.hDC9OaoiFWWunC51lQJzoevE7kM6Q/S', '0658888887', 11333369, 'zzzzz', 6, b'0', NULL, NULL),
(60, 'sal@sal', 'sal', 'sal', 'sal', '$2a$10$e09h7EctCkQBQNtK/wk1YeqrqF9qVDMCw0mJbE/1rs2GE8t1zvL3K', '0698555555', 88889999, 'sal', 6, b'0', NULL, NULL),
(61, 'alll@alll', 'alll', 'alll', 'alll', '$2a$10$l5yOMDS.WUOTyLrNhLI2Ye93PUG.pGDyFfFzgqhGMPtHfS9JoUgNC', '0658582221', 1222999, 'alll', 6, b'0', NULL, NULL),
(62, 'akak@akak', 'akak', 'akak', 'akak', '$2a$10$hx.MuRmDnuiGfNRNxotbWuK03dULeUuqeZ3bncA5V4A6UsXP.0LSy', '0222229999', 114477, 'akak', 5, b'0', NULL, NULL),
(63, 'axax@axax', 'axax', 'axax', 'axax', '$2a$10$rGiZGpHrCmsV/rV6etjIBekkieZYsUR63EcF.Jo2QOQ634IUNPcSO', '0229999777', 98745569, 'axax', 5, b'0', NULL, NULL),
(64, 'amam@amam', 'amam', 'amam', 'amam', '$2a$10$eAHC5JXt8vOtXEwlWFUYDeU3QtB.d.x8oQ4nSIjw8BxW/Irs/TgSi', '0699997777', 98855200, 'amam', 6, b'0', NULL, NULL),
(65, 'abab@abab', 'abab', 'abab', 'abab', '$2a$10$RGof3sRBBN52SIwNOhzUIuL4bYx90g/zqdW1mOJQsknJmW4556Fci', '0699988877', 15999633, 'abab', 5, b'0', NULL, NULL),
(66, 'kgkg@kgkg', 'kgkg', 'kgkg', 'kgkg', '$2a$10$BYfYMcE9//19k6hb2LfYVOGuanC0kNdEnUZQcWBarbJQ.fJYR2ilC', '0698888332', 6999880, 'kgkg', 6, b'0', NULL, NULL),
(67, 'jkjk@gmail.com', 'jkjk', 'jkjk', 'jkjk', '$2a$10$aZr3/IC2xUHdacAwghAvh.1g.9AsdKrcZOmtyZgdLmmxWr1EOc1BK', '0255588877', 199987220, 'jkjk', 6, b'0', NULL, NULL),
(68, 'ffb@ffb', 'ffb', 'ffbffb', 'ffb', '$2a$10$zJEMKHVxJninGIA6NfK0VO56L7i2/yL0uosYwVQ3ziLhf1F7/8yam', '0654111111', 15590000, 'ffb', 6, b'0', NULL, NULL),
(69, 'upup@upup', 'upupupup', 'upup', 'upup', '$2a$10$i8hKKn8q3S5L61NHCMdKA.vK8FbDuIbVsI4cdEJqMYp4VyuU7mgvO', '0999877700', 999999, 'upup', 6, b'0', NULL, NULL),
(70, 'psps@psps', 'psps', 'psps', 'psps', '$2a$10$Y0k58RT/E7Az9qvF7DiAieNy1BOHeMJS7/AEHbzfK6.NKOQcykLeS', '0698870000', 19220000, 'psps', 6, b'0', NULL, NULL),
(71, 'llm@llm', 'llm', 'llm', 'llm', '$2a$10$D6ZpfOzZDkX8LzSWFDzvzuce6AjYhPXbrLBcd9/KsGtXm6wF/Bamu', '0222189999', 900090, 'llm', 6, b'0', NULL, NULL),
(72, 'xoxo@xoxo', 'xoxo', 'xoxo', 'xoxo', '$2a$10$7mU7ZwOEWeCRq79UaAzDJ.yiaBnNo1k4tgY3VIPuOP6q8VaCnouwG', '0111477777', 9999, 'xoxo', 6, b'0', NULL, NULL),
(73, 'chch@chch', 'chch', 'chch', 'chch', '$2a$10$1uDafKI4nKCUruAa9odf/O647zyvlTgWuilVVOmVF8qWgqTn4.rOq', '0222288889', 19995444, 'chch', 6, b'0', NULL, NULL),
(74, 'jsjs@jsjs', 'jsjs', 'jsjs', 'jsjs', '$2a$10$qp/Qk9IaI9x/uvFJ6qyJduKspWruJqwByYR2M81f65xMcwdlokr7u', '0222588880', 90000, 'jsjs', 6, b'0', NULL, NULL),
(75, 'mostafa@mostafa.com', 'mostafa', 'mostafajira', 'mostafa', '$2a$10$bVfYJ1DTQO3jNtaGXLWf9uG5ullPQAUDF.zA/RoN68sDlVlPgjJkS', '0698888778', 1199880, 'mostafa', 5, b'0', NULL, NULL),
(76, 'mehdi@mehdi', 'mehdi', 'mehdijira', 'mehdi', '$2a$10$jevCIXkbBNSyh/3GyJTaS.2XVkZveKsp3SEuJzdjJHYFtcjrLP6cy', '0369998880', 1999000, 'mehdi', 5, b'0', NULL, NULL),
(77, 'cam@cam', 'cam', 'camjira', 'cam', '$2a$10$QU7YBmPk1PtZZTxfp5ujtuVBYFe2WhMa2M3poxcAhqxEqLUB7cD76', '0697556656', 657474, 'cam', 6, b'0', NULL, NULL),
(78, 'kar@kar', 'kar', 'karjira', 'kar', '$2a$10$lHprHYdOpZfNFqBOE1EG9ep6palhsATiPh0kJXLHhPYsTIPlFE0Ae', '0698887700', 1991919, 'kar', 6, b'0', NULL, NULL),
(79, 'bil@bil', 'bil', 'biljira', 'bil', '$2a$10$8INAyt/l8iG6h7Y9T5UEw.4pME5cYsefPwIT4tF6jv02o2uBR/XH.', '0322555540', 19919100, 'bil', 6, b'0', NULL, NULL),
(80, 'kaf@kaf', 'kaf', 'kafjira', 'kaf', '$2a$10$1M9iG59sN/EB9yK9MNkxpe/LhOeUucnre.IJT7A4rxwd7jMYk4QhW', '0332222222', 11122336, 'kaf', 6, b'0', NULL, NULL),
(81, 'zoubir@zoubir', 'zoubir', 'zoujira', 'zoubir', '$2a$10$uH6jzeN9IAQyXz/wfNSoPOf5NacIxr.0Fzpq4N4uLl9k95YGgOpKC', '0808808088', 101102, 'zoubir', 5, b'1', NULL, NULL),
(82, 'fyfy@fyfy', 'fyfy', 'fyfyjira', 'fyfy', '$2a$10$/yemynvBiVa0AxdjTbvqw.ciPOiIQ9sJts64lzEvvSAD/mfb/9jDm', '0322588888', 15588880, 'fyfy', 6, b'0', NULL, NULL),
(83, 'ooooo@ooooo', 'ooooo', 'ooooojira', 'ooooo', '$2a$10$4XusyKYXbD3pK/cpGKOmQ.o.1suKpqR/xTHmJoDb4MccM6O8FYsxe', '0660660006', 50050505, 'ooooo', 4, b'0', NULL, NULL),
(84, 'foto@foto', 'foto', 'fotojira', 'foto', '$2a$10$soD1QwDkIjN3cbg6tMgpK.KUqoXK5YyU23KK3be2Zn92g87TqOA4W', '0332222232', 97977970, 'foto', 6, b'0', NULL, NULL),
(85, 'ihj@ihj', 'ihj', 'ihjjira', 'ihj', '$2a$10$NDExOKIQl2XEEvSGYB2jH.N7xlFJRyfTvLO/satc46ug01BAihZBS', '0606600604', 607403, 'ihj', 5, b'0', NULL, NULL),
(86, 'kjkjk@kjkjk', 'kjkjk', 'kjkjkjira', 'kjkjk', '$2a$10$KZNW1khOLkkEH7qSZGT.ZeXDQuaqV9RLxWe5hyAb7ALpcWMBOtjzu', '0332222220', 123123228, 'kjkjk', 5, b'0', NULL, NULL),
(87, 'ucuc@ucuc', 'ucuc', 'ucucjira', 'ucuc', '$2a$10$Th4rkqWAyfdfzCFucVfJLe6YWkZEzmo00Gj.Hc9o/AU87SiRj5LXG', '0088088080', 888008, 'ucuc', 6, b'0', NULL, NULL),
(88, 'drdr@drdr', 'drdr', 'drdrjira', 'drdr', '$2a$10$36vMRPz3Mqe5TNgjThyyJ.OmkL5D.1vrv4zbWjqJbfGlwPROyJ6Ru', '0565055050', 500000655, 'drdr', 6, b'0', NULL, NULL),
(89, 'enen@enen', 'enen', 'enenjiraa', 'enen', '$2a$10$8m6GEaGoS8s4gH9bLBsl5.bQFvoES1Md2HaLSl6llex5BRfZ37YLa', '0550550606', 50056054, 'enen', 6, b'0', NULL, NULL),
(90, 'uxux@uxux', 'uxux', 'uxux', 'uxux', '$2a$10$6b5Wx3GJZp8FbrjevT9w1.c73faq4GtgqVdUmEU4d6zPqF3YDjhyC', '0222144774', 17171717, 'uxux', 5, b'0', NULL, NULL),
(91, 'frfr@frfr', 'frfr', 'frfrjira', 'frfr', '$2a$10$08WbYb.NV5OjJGNnrWuSmu4OMFqGB5QfJf.FIqhtO7MaiIwIn4nVO', '0221477774', 1121212878, 'frfr', 6, b'0', NULL, NULL),
(92, 'nnn@nnn', 'nnn', 'nnnjira', 'nnn', '$2a$10$wTL9oDxnh5S0NB1mjdyJNOAwxMs/GyE3vcBGH0CqK8NoJftu4emMS', '0247788000', 151513970, 'nnn', 6, b'0', NULL, NULL),
(93, 'sty@sty', 'sty', 'styjira', 'sty', '$2a$10$rhuZ7NsSDgiwozF9j0WAhO.MuCBjnDxdIyJdz0XUZMZqHeiRuIege', '0580808000', 255707, 'sty', 6, b'0', NULL, NULL),
(94, 'danna@danna', 'danna', 'dannajira', 'dann', '$2a$10$AzVovyV8A8PbRo16iQywBeID3IBH.eos5yHhemoMKPVUrVJ7i4mwC', '0554782145', 8007070, 'dann', 6, b'0', NULL, NULL),
(95, 'pqpq@pqpqpqpq', 'pqpqpq', 'pqpqpqjira', 'pqpqpq', '$2a$10$GKc9XjZSRAr7vg9g7Hhe7.hoKrg41Isa.L37JhUh7YZjAProLDa7O', '0650600000', 7778, 'pqpqpq', 6, b'0', NULL, NULL),
(96, 'paola@danna', 'paola', 'paolajira', 'paola', '$2a$10$s5jx5vD9Vt7D9D/Sq1siJ.rlN5hX0vP15SwoYVQ03GR1LpFeM7Ngy', '0505005077', 87009040, 'paola', 6, b'0', NULL, NULL),
(97, 'fam@fam', 'fam', 'famjira', 'fam', '$2a$10$PVMOpukObAfN2.DnKr7FVO9ywUNuvsLnVIbrx/yi/rkHm0peZwVya', '0500500409', 12809700, 'fam', 6, b'0', NULL, NULL),
(98, 'kaw@kaw', 'kaw', 'kawjira', 'kaw', '$2a$10$0WGbV3mTzS3XyMz5Nz/FjeEgZLClngWp1cqu2A3TL7SaQoM3cEfxG', '0505040793', 12045088, 'kaw', 6, b'0', NULL, NULL),
(99, 'wewe@wewe', 'wewe', 'wewejira', 'wewe', '$2a$10$7EVte9eiXtltLzLv28R6VOo6PfiCcglE73d2u70saY.R/0Qi6o5qu', '0214471250', 8700001, 'wewe', 5, b'0', NULL, NULL),
(100, 'poi@poi', 'poi', 'poijira', 'poi', '$2a$10$3oKOVEeeq63bXTLsIOM6ieDCW2aNvtZPVM3FU1sMdXqiDwsInkLB6', '0504010705', 807077000, 'poi', 6, b'0', NULL, NULL),
(101, 'cve@cve', 'cve', 'cvejira', 'cve', '$2a$10$elXQF0/.Q0MbvCk6lBy1Vuvjjlo1F5HIPRcD.7Gu6B59e1QXkRMSy', '0040777009', 501170000, 'cve', 6, b'0', NULL, NULL),
(102, 'ksd@ksd', 'ksd', 'ksdjira', 'ksd', '$2a$10$llCrtzilOy9IqhuL2iOYtOdUA.1n9941AJIsiVpuqlYWD3bZH4M1u', '0987777000', 77007707, 'ksd', 5, b'0', NULL, NULL),
(103, 'lokl@lokl', 'lokl', 'lokl', 'lokl', '$2a$10$tPEgNEBQ/wpnA3DX2lWkeuhQzf3oUBZytGWrcBWA7IBG7uXsohpaK', '0202200017', 707700920, 'lokl', 6, b'0', NULL, NULL),
(104, 'hgfh@hgfh', 'hgfh', 'hgfh', 'hgfh', '$2a$10$HQ5dlyatpPoM9FEVsNKqBOg0/O8ZIweHnc6kpdhgjxy2fe67..lMS', '0880070792', 10201208, 'hgfh', 6, b'0', NULL, NULL),
(105, 'des@des', 'des', 'desjira', 'des', '$2a$10$nEmjARba2n/H5Oc1A05/Hu8EFkQ8YU6ehN292kftOQmJRsSp5Ur3C', '0500007087', 1040477, 'des', 4, b'0', NULL, NULL),
(106, 'ert@ert', 'ert', 'ertjira', 'ert', '$2a$10$9ZC9DlpPpPX3zbV/Ow9cKOwUmk7h64W/fdu8gXtB1WYrgG3thyBYK', '0110104000', 101419336, 'ert', 5, b'0', NULL, NULL),
(107, 'vbn@vbn', 'vbn', 'vbn', 'vbn', '$2a$10$YZEzSzW0rLJDtdNO8tquteECV.pI6G/zseE4DCnXdfA1RIRAm8Jna', '0221899880', 12120778, 'vbn', 6, b'0', NULL, NULL),
(108, 'agagag@agal', 'agagag', 'agagagjira', 'agagag', '$2a$10$pF2WNVHcJVkw8DnS5NVShubDCl/OZp/dzcgK2L.3HWF2.bfSUxC6m', '0101150800', 1019988888, 'agagag', 5, b'0', NULL, NULL),
(109, 'lwlwlw@lelwlw', 'lwlwlw', 'lwlwlwlwjira', 'lwlwlw', '$2a$10$9LeTHb1.b5b5eU6xh0x8fOsPeOKDF.WIa6uA6noSYckDw4sduk/DC', '0230001110', 1200211018, 'lwlwlw', 6, b'0', NULL, NULL),
(110, 'hgbn@hgbn', 'hgbn', 'hgbnjira', 'hgbn', '$2a$10$ylfNf.y6ktXCp1PAqd7UieX32CgAg2KjpbbxhSCEa0LOcyhDvyaDS', '0111000014', 21333000888, 'hgbn', 6, b'0', NULL, NULL),
(111, 'wer@wer', 'wer', 'werwer', 'wer', '$2a$10$A31htdQBrHTeDTA2Z7kCdu0i4NHdNBHLFpjT/SsvBWyCj1RIObbrG', '0255402699', 110477777, 'wer', 6, b'0', NULL, NULL),
(112, 'imad@imad', 'imad', 'imadjira', 'imad', '$2a$10$dApeJWDGlSewDgZkgejOgeNgtKLJcbbuxcxHuOUuVwBtscSk0QsF.', '0501017988', 40040110077, 'imad', 6, b'1', NULL, NULL),
(113, 'mari@mari', 'mari', 'marijira', 'mari', '$2a$10$93i30aEglYc98e6r2zlpAOIyF89PU1onVkofXRIPN1B7iSeTcCiGe', '0214478500', 1010177179, 'mari', 5, b'1', NULL, NULL),
(114, 'sayda@sayda', 'sayda', 'sayda', 'sayda', '$2a$10$cIgOvceFZv.hvon2dsSlxeQd.MP5vgdvRjI6M.ALNZfiAdEobnonC', '0011000058', 101588866, 'sayda', 5, b'1', NULL, NULL),
(115, 'admin2@gmail.com', 'admin2', 'admin2jira', 'admin2', '$2a$10$7xCSQSjMT9btDg/d0yYhhOoqZvSlDsy2RgHaz2iE4B5zjZYLBR6b6', '0258258964', 101777778, 'admin2', 1, b'0', NULL, NULL),
(116, 'super@super', 'super', 'super', 'super', '$2a$10$1CZRAx3/O2fsMGyPvdMa6eHwCRakQDjF1V2tKqlar9/1RXhcCmY6C', '0214444777', 88800001, 'super', 2, b'0', NULL, NULL),
(118, 'admin3@gmail.com', 'admin3 ', 'admin3jira', 'admin3', '$2a$10$ryRCQXF4QUbW3B.lnZE9Ce0UzR.OmC3Ey94//LkCF3tmtfC5HRrqO', '0214799996', 1143439007, 'admin3', 1, b'1', NULL, NULL),
(119, 'super2@gmail.com', 'super2', 'super2jira', 'super2', '$2a$10$ey.zYDKfUvTbqns3s0KZsOEs5/nH8j2dRtdKo6UMFmK8a4mbjLvB.', '0211199977', 43767341, 'super2', 2, b'1', NULL, NULL),
(121, 'sup2@gmail.com', 'sup2', 'sup2jira', 'sup2', '$2a$10$SMZ2kNmlekyp8/c6PCwPx.k843/54UR2wa23oQ.Zqt/S/w9IaH9RC', '0217793145', 107834219, 'sup2', 2, b'1', NULL, NULL),
(122, 'jun@jun', 'jun', 'junjira', 'jun', '$2a$10$yqOcuhbMX5NHstm86RmKBu.ROCQHfncshG7PtMTMDbl/EgLVpprCS', '0211144773', 9090911, 'jun', 6, b'1', NULL, NULL);

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
(3, 1),
(3, 2),
(4, 1),
(5, 1),
(7, 4),
(6, 4),
(9, 1),
(9, 2),
(10, 1),
(11, 2),
(12, 1),
(13, 1),
(14, 1),
(15, 1),
(15, 2),
(16, 1),
(16, 2),
(17, 1),
(18, 1),
(18, 2),
(19, 1),
(19, 2),
(20, 1),
(20, 2),
(21, 1),
(22, 1),
(22, 2),
(23, 1),
(23, 2),
(25, 1),
(25, 2),
(27, 2),
(28, 1),
(28, 2),
(29, 1),
(30, 1),
(30, 2),
(31, 1),
(31, 2),
(32, 1),
(33, 1),
(34, 1),
(35, 1),
(36, 1),
(37, 1),
(37, 5),
(38, 1),
(39, 1),
(40, 1),
(41, 1),
(41, 2),
(42, 1),
(43, 1),
(44, 1),
(45, 1),
(46, 1),
(47, 1),
(48, 1),
(49, 1),
(50, 1),
(51, 1),
(52, 1),
(53, 2),
(54, 1),
(54, 2),
(55, 1),
(56, 1),
(57, 2),
(58, 1),
(59, 1),
(60, 1),
(61, 1),
(62, 1),
(63, 1),
(64, 1),
(65, 1),
(66, 1),
(67, 1),
(68, 1),
(69, 1),
(70, 1),
(71, 1),
(72, 1),
(73, 1),
(74, 1),
(75, 5),
(76, 1),
(77, 1),
(78, 1),
(78, 2),
(79, 1),
(80, 1),
(81, 1),
(82, 1),
(83, 1),
(84, 1),
(85, 1),
(86, 1),
(87, 1),
(88, 1),
(89, 1),
(90, 1),
(91, 1),
(92, 1),
(93, 1),
(94, 1),
(95, 1),
(96, 1),
(97, 1),
(98, 1),
(99, 1),
(100, 1),
(101, 1),
(102, 1),
(103, 1),
(104, 1),
(105, 1),
(106, 1),
(107, 1),
(108, 1),
(109, 1),
(110, 1),
(111, 1),
(112, 1),
(113, 1),
(114, 1),
(115, 1),
(116, 1),
(116, 2),
(118, 1),
(119, 1),
(121, 1),
(121, 2),
(121, 5),
(122, 1),
(122, 2),
(122, 5),
(8, 4);

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
