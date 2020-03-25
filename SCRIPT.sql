INSERT INTO `equivalences` (`equivalence_id`, `column_name`, `fenix_equivalence`, `jira_equivalence`, `file_type_id`, `column_order`) VALUES
(4, 'key', NULL, 'Key', 4, NULL),
(5, 'summary', 'Nombre Incidencia', 'Summary', 4, 2),
(6, 'description', 'Descripción', 'Description', 4, 5),
(7, 'originalEstimate', 'Esfuerzo (HH)', 'Original Estimate', 4, 8),
(8, 'created', NULL, NULL, 4, NULL),
(9, 'updated', NULL, 'Updated', 4, NULL),
(10, 'resolved', NULL, 'Resolved', 4, NULL),
(11, 'timeSpent', NULL, 'Time Spent', 4, NULL),
(12, 'status', NULL, 'Status', 4, NULL),
(13, 'incidenceType', NULL, 'Tipo incidencia', 4, NULL),
(14, 'assignedUser', NULL, 'Assignee', 4, NULL),
(15, 'idIncidencia', 'Id Incidencia', NULL, 4, 0),
(16, 'idPeticion', 'Id Petición / OT', NULL, 4, 1),
(17, 'localizadaEn', 'Localizada En', NULL, 4, 3),
(18, 'fechaInicio', 'Fecha Inicio', NULL, 4, 6),
(19, 'fechaFin', 'Fecha Fin', NULL, 4, 7),
(20, 'urgencia', 'Urgencia', NULL, 4, 9),
(21, 'impacto', 'Impacto', NULL, 4, 10),
(22, 'resueltaPorCliente', 'Resuelta por Cliente', NULL, 4, 11),
(23, 'prioridad', 'Prioridad', NULL, 4, 12),
(24, 'fechaPrevistaCentro', 'Fecha Prevista Centro', NULL, 4, 13),
(25, 'tareaCausante', 'Tarea Causante', NULL, 4, 14),
(26, 'otCorrector', 'OT Corrector', NULL, 4, 15),
(27, 'accCorrector', 'ACC Corrector', NULL, 4, 16),
(28, 'estado', 'Estado', NULL, 4, 17),
(29, 'convertibleEnMejora', 'Convertible en Mejora', NULL, 4, 18),
(30, 'comentarioAlDesestimar', 'Comentario al Desestimar', NULL, 4, 19),
(31, 'tipoIncidencia', 'Tipo Incidencia', NULL, 4, 4),
(32, 'project', NULL, 'Project', 1, NULL),
(33, 'key', NULL, 'Key', 1, NULL),
(34, 'title', 'Nombre', 'Title', 1, 1),
(35, 'description', 'Descripción', 'Description', 1, 3),
(36, 'fixVersion', NULL, 'Fix Version/s', 1, NULL),
(37, 'originalEstimate', 'Esfuerzo', 'Original Estimate', 1, 11),
(38, 'remainingEstimate', NULL, 'Remaining Estimate', 1, NULL),
(39, 'status', NULL, 'Status', 1, NULL),
(40, 'timeSpent', NULL, 'Time Spent', 1, NULL),
(41, 'taskType', NULL, 'Tipo Tarea', 1, NULL),
(42, 'user', 'Responsable', 'Username', 1, 7),
(43, 'comment', NULL, 'Comment', 1, NULL),
(44, NULL, 'ID ACC', NULL, 1, 0),
(45, NULL, 'Código Petición Cliente', NULL, 1, 2),
(46, NULL, 'Estado', NULL, 1, 4),
(47, NULL, 'Tipo', NULL, 1, 5),
(48, NULL, 'Pet/Ot. Asociada', NULL, 1, 6),
(49, NULL, 'Subtipo', NULL, 1, 8),
(50, NULL, 'Rechazos Entrega', NULL, 1, 9),
(51, NULL, 'Criticidad', NULL, 1, 10),
(52, NULL, 'Esfuerzo Cliente', NULL, 1, 12),
(53, NULL, 'Fecha Creación', NULL, 1, 13),
(54, NULL, 'Fecha Solicitud Cliente', NULL, 1, 14),
(55, NULL, 'Fecha Prevista Proyecto', NULL, 1, 15),
(56, NULL, 'Fecha Entrega', NULL, 1, 16),
(57, NULL, 'Fecha Cierre', NULL, 1, 17),
(58, NULL, 'Fecha Desestimación', NULL, 1, 18),
(59, NULL, 'Fecha Inicio Centro', NULL, 1, 19),
(60, NULL, 'Resultado Testing', NULL, 1, 20),
(61, NULL, 'Puntos Historia', NULL, 1, 21),
(62, NULL, 'Historia Usuario', NULL, 1, 22),
(63, NULL, 'Épica', NULL, 1, 23),
(64, 'jiraSas', NULL, 'Jira SAS', 4, NULL),
(65, 'linkedIssues', NULL, 'Linked Issues', 4, NULL),
(66, 'comment', NULL, NULL, 4, NULL),
(67, 'causedUser', NULL, 'Causante', 4, NULL),
(68, 'project', NULL, 'Project', 4, NULL);

-- --------------------------------------------------------



INSERT INTO `features` (`feature_id`, `description`, `feature_name`, `parent_feature_id`) VALUES
(2, 'Permission pour la gestion globale des utilisateurs', 'Gestion des utilisateurs', NULL),
(3, 'Permission pour ajouter un nouvel utilisateur', 'ajouter un utilisateur', 2),
(4, 'Permission pour modifier un ancien utilisateur', 'Modifier un utilisateur', 2),
(5, 'Permission pour la gestion globale des projets', 'Gestion des projets', NULL),
(6, 'Permission pour la gestion globale des roles', 'Gestion des roles', NULL);

-- --------------------------------------------------------


INSERT INTO `incidences` (`incidence_id`, `comment`, `created`, `date`, `description`, `incidence_type`, `jira_sas`, `incidence_key`, `linked_issues`, `original_estimate`, `planned_end`, `resolved`, `task_status`, `summary`, `time_spent`, `updated`, `assigned_user_id`, `caused_user_id`, `file_type_id`, `project_id`) VALUES
(1, NULL, NULL, '2020-03-16 12:17:53', '', 'Construcción', 'SIGLO-3170', 'ECOFISAS-9565', '', '0.0', NULL, '2020-01-30 18:54:00', 'Resolved', 'ECOFISAS-8381 INC_EXT_02 - Resolución SONAR', '', '2020-01-30 18:54:00', 7, 7, 3, 4),
(2, NULL, NULL, '2020-03-16 12:17:53', 'Los errores detectados son los siguientes (algunos por el tema del SQLPlus que ya hemos comentado): \n• 001_NUEVA_TABLA_ALM_SISTEMAMENSAJE.sql ? Falla el create table por tener líneas vacías. La creación de la primary key falla porque el nombre ya se está usando en otra tabla. \n• 004_CREAR_NUEVO_PARAMETRO_EN_ORG_PARAMETRO.sql ? Fallo en el insert por incluir línea vacía. \n• 005_NUEVA_TABLA_ALM_HISTMENCARRUSELEP.sql ?Fallo en el create table por incluir líneas vacías. También falla porque los nombres de las foreing key ya se estaban usando en otro objeto (en concreto en un script previo). \n• 006_NUEVA_TABLA_ALM_HISTMENCARRUSELEPLINEA.sql ? Se llamaba inicialmente 006_NUEVA_TABLA_ALM_HISTMENCARRUSELEPLINEA .sql, es decir con un espacio en blanco. Se ha renombrado porque daba error al ejecutarse. También fallaba por las líneas vacías. La foreign key fallaba porque en el script 005 no se había creado primary key- \n• 007_NUEVA_TABLA_ALM_HISTMENCARRUSELEPERROR.sql ? Se ha renombrado igual que el punto anterior. También fallaba por líneas vacías en el create table. \n• 009_INSERT_ORG_TAREA.sql ? No se habían cerrado las comillas. \n• 010_PROCEDURE_PASOHISTORICOMENCARRUSELEP.sql ? Faltaba al final del procedure la barra / para indicar que ha finalizado la sentencia. Se ha eliminado la ejecución posterior del procedure “EXEC PASOHISTORICOMENCARRUSELEP;”. Lo he hablado con David y me ha comentado que no debería ejecutarse el procedure al lanzar el script. \n\nTiempo estimado : 0.5h ( se necesita solo subir los archivos corregidos por Borja) \n', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9531', 'ECOFISAS-9507', '0.0', NULL, '2020-01-29 12:54:00', 'Resolved', 'ECOFISAS-9110 INC_INT_31 - Errores en scripts', '300.0', '2020-02-24 16:28:00', 7, 7, 4, 4),
(3, NULL, NULL, '2020-03-16 12:17:53', 'Se han detectado errores en los scripts y se procede a su corrección: \n\nDO: \n- 001: El formato debe se ANSI, jamás debe ser utf-8 o no funcionará en SQLPLUS \n- 007: El script estaba mal a la hora de añadir las columnas de la tabla \nUNDO: \n- 003: La \"/\" estaba mal colocada, encima del END del script \n- 007: Faltaba realizar el drop de la secuencia', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9554', '', '0.0', NULL, '2020-01-30 17:14:00', 'Resolved', 'ECOFISAS-9110 INC_INT_32- Errores en los scripts de la tarea.', '', '2020-01-31 12:42:00', 7, 7, 4, 4),
(4, NULL, NULL, '2020-03-16 12:17:53', 'Cuando haber un registro en tabla ALM_MENMOVSTOCK con el motivo de error \"Las existencias que indica son superiores a las que hay en SIGLO\", en la busqueda de pantalla Consulta de Recuentos RFID se muestra el error NPE \n\n\n', 'No es una incidencia', 'SIGLO-2937', 'ECOFISAS-9553', 'ECOFISAS-9552', '0.0', NULL, '2020-02-03 17:11:00', 'Resolved', 'ECOFISAS-9110 INC_INT_30 - NPE cuando buscar registros con incidencias', '', '2020-02-03 17:11:00', 7, 7, 4, 4),
(5, NULL, NULL, '2020-03-16 12:17:53', 'No se genera el mensaje de devolución cuando se ejecuta una OT con destino físico a una ubicación de una zona integrada. \nPaso. \nSe registra la solicitud de devolución ORIGEN ADC destino AC ubicación carrusel. \nSe edita la OT y se le incluye la ubicación destino.8RLQP \nSe revisa la tabla de select * FROM siglo.ALM_MENCARRUSELEP cmsj order by fechaenvio desc ; y no ha generado mensaje de devolución. \nSe revisa la OT y esta ejecutada correctamente. \n\n\n\n', 'Construcción', 'SIGLO-2949', 'ECOFISAS-9698', 'ECOFISAS-9625', '0.0', NULL, '2020-02-10 17:20:00', 'Resolved', 'ECOFISAS-7609 INC_EXT_22- No genera mensaje de orden de trabajo de devolución.', '18000.0', '2020-02-11 08:52:00', 6, 6, 3, 4),
(6, NULL, NULL, '2020-03-16 12:17:53', 'No se cumple el requisito. \n005-FEF-3965-01-04-01 \nSi el genérico de centro incluido en la OT de devolución, pertenece a una ubicación física de un carrusel integrado, deberá crearse la OT con la línea cumplimentada con la ubicación física automáticamente, para que el gestor no tenga que completar la ubicación. \nDevolución 1479036 (todas las solicitudes se han creado con fecha de 06/02/2020). \nGC E67698 --> L-LOGISONE006 \n\n\n', 'Análisis', 'SIGLO-2949', 'ECOFISAS-9696', 'ECOFISAS-9625', '0.0', NULL, '2020-02-10 17:22:00', 'Resolved', 'ECOFISAS-7609 INC_EXT_21- No asocia automáticamente la ubicación a la OT de devolución', '9000.0', '2020-02-11 09:03:00', 6, 6, 3, 4),
(7, NULL, NULL, '2020-03-16 12:17:53', 'Estimación 2h', 'Construcción', 'SIGLO-3250', 'ECOFISAS-9225', 'ECOFISAS-9258', '0.0', NULL, '2020-01-10 10:45:00', 'Resolved', 'ECOFISAS-8933 INC_INT_06 - No se tiene en cuenta el destino para los criterios de búsqueda', '7200.0', '2020-01-30 14:43:00', 7, 7, 4, 4),
(8, NULL, NULL, '2020-03-16 12:17:53', 'Se detecta un error al acceder a los detalles de la solicitud de devolución. \nPara reproducirlo: Acceder Gestión de Solicitudes de Devolución --> Consultar OT 1479033 y luego pulsar en el botón detalle. \n\"com.hp.geos.almacen.modelo.objetosNegocio.implementacion.SolicitudDevolucionImpl\" \n', 'Construcción', 'SIGLO-2949', 'ECOFISAS-9692', 'ECOFISAS-9625', '0.0', NULL, '2020-02-07 15:35:00', 'Resolved', 'ECOFISAS-7609 INC_EXT_18- Error al acceder al detalle de una solicitud de devolución', '1800.0', '2020-02-07 15:36:00', 6, 6, 3, 4),
(9, NULL, NULL, '2020-03-16 12:17:53', '', 'Construcción', 'SIGLO-3264', 'ECOFISAS-9435', 'ECOFISAS-9394, ECOFISAS-9858', '0.0', NULL, '2020-01-27 08:51:00', 'Resolved', 'ECOFISAS-9059 INC_INT_03 No se ejecuta la tarea de generación de pedidos externos en segundo plano', '57600.0', '2020-02-20 22:59:00', 6, 6, 4, 4),
(10, NULL, NULL, '2020-03-16 12:17:53', '', 'Construcción', 'SIGLO-3264', 'ECOFISAS-9394', 'ECOFISAS-9358, ECOFISAS-9435', '0.0', NULL, '2020-01-23 17:03:00', 'Resolved', 'ECOFISAS-9059 INC_INT_02 No se muestran los nuevos campos observaciones por proveedor y recordatorio', '7200.0', '2020-02-11 17:39:00', 6, 6, 4, 4),
(11, NULL, NULL, '2020-03-16 12:17:53', 'Columna generación pedido de pantalla Consulta de Recuentos RFID se muestra valores incorrectos, debe mostrar valores Sí o No \nCódigo mensaje no muestra ningún valor \nen la exportación de excel se muestra correctamente el valor de la columna generación pedido y no se muestra el valor de la columna código mensaje \n\nDatos de pruebas en Nube 1 \nPlataforma Sevila Almacen _[DS] ALMACEN DISTRITO A.P. SEVILLA \nfechas 21/012020 hasta 23/01/2020 \n\nResponsable ==> Nafie \n', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9538', 'ECOFISAS-9537, ECOFISAS-9552', '0.0', NULL, '2020-02-03 17:26:00', 'Resolved', 'ECOFISAS-9110 INC_INT_28 - se muestran valores incorrectos en las columnas generación pedido y código mensaje de pantalla Consulta de Recuentos RFID', '7200.0', '2020-02-03 18:29:00', 7, 7, 4, 4),
(12, NULL, NULL, '2020-03-16 12:17:53', 'No se muestra el aviso que debe haber un unico articulo en pantalla Consulta de Recuentos RFID cuando seleccionar el check de datos historicos y no hay GC \n\nResponsable ==> Nafie \n', 'Construcción', 'SIGLO-2937', 'ECOFISAS-9537', 'ECOFISAS-9536, ECOFISAS-9538', '0.0', NULL, '2020-02-05 01:05:00', 'Resolved', 'ECOFISAS-9110 INC_INT_27 - No se muestra el aviso que debe haber un unico articulo en pantalla Consulta de Recuentos RFID', '600.0', '2020-02-05 01:05:00', 7, 7, 4, 4);

-- --------------------------------------------------------



INSERT INTO `projects` (`project_id`, `description`, `is_closed`, `project_name`) VALUES
(1, 'desc1', b'0', 'project1'),
(2, 'desc2', b'0', 'project2'),
(4, 'SAS Description', b'0', 'ECOFISAS - EcoFI-SAS (Mantenimiento de los sistemas económico financieros...');

-- --------------------------------------------------------



INSERT INTO `roles` (`role_id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_SUPERVISOR'),
(3, 'ROLE_TEAMLEADER'),
(4, 'ROLE_SENIOR'),
(5, 'ROLE_DEVELOPER'),
(6, 'ROLE_JUNIOR');

-- --------------------------------------------------------


INSERT INTO `tasks` (`task_id`, `comment`, `date`, `description`, `fix_version`, `task_key`, `original_estimate`, `remaining_estimate`, `task_status`, `task_type`, `time_spent`, `title`, `file_type_id`, `project_id`, `user_id`) VALUES
(28, 'Preparación de datos para crear MUS', '2020-03-05', 'Crear el manual del usuario', '30.0.0', 'ECOFISAS-7814', '4h', '2h', 'In Progress', 'Desarrollo', '2h', 'ECOFISAS-7810> G03-T03 - Manual de usuario', 1, 4, 8),
(29, 'continuar con las pruebas', '2020-03-05', 'Revisar y probar el desarrollo hecho', '30.0.0', 'ECOFISAS-8361', '4h', '2h', 'In Progress', 'Desarrollo', '2h', 'ECOFISAS-7810> G03-T02-Revisión y pruebas del analista', 1, 4, 8);

-- --------------------------------------------------------


INSERT INTO `types` (`type_id`, `description`, `type_name`) VALUES
(1, 'description 1', 'Tarea cargable'),
(2, 'description 2', 'Tarea no cargable'),
(3, 'description 3', 'Incidencia externa'),
(4, 'description 4', 'Incidencia interna'),
(5, 'description 5', 'Cambio de alcance');

-- --------------------------------------------------------



INSERT INTO `users` (`user_id`, `email`, `firstname`, `jira_username`, `lastname`, `password`, `phone`, `user_code`, `username`, `role_id`) VALUES
(1, 'admin1@gmail.com', 'wail', 'jira_admin1', 'el yousfi', '$2a$10$kqT8My2gCJn0xNRzMZvkxunz89PdSuDGyZhKsaoE5lYEfm9uRcpvG', '+212697935718', 100, 'admin1', 1),
(3, 'supervisor1@gmail.com', 'mohamed', 'jira_supervisor1', 'achkour', '$2a$10$l3EHu80DR0ZjL3PVOMg7ieJ7.8WEYzfUthoYsulQRQtLmlfbp.69O', '+212697935118', 101, 'supervisor1', 2),
(4, 'senior1@gmail.com', 'moad', 'jira_senior1', 'khider', '$2a$10$0Ic8Q43vlI04vNeFzpfRCOIZsNfxMRJBz8G/A0T6irh7wkLNgb5Ui', '+212691475118', 102, 'senior1', 4),
(5, 'teamleader1@gmail.com', 'hicham', 'jira_teamleader1', 'sbihi', '$2a$10$x4GqPMMH6oHBZ5uNsQUokOb0hHtGV/uPI7l/JdtA82da93TUWml.y', '+212691479918', 103, 'teamleader1', 3),
(6, 'zakaria.mtougui@gmail.com', 'zakaria', 'Zakaria Mtougui', 'mtougui', '$2a$10$Z739lDeaKHpM7gF3dMdzjOvjPInOqGr0LF7lNytIhrNZaAohqLZv6', '+212611496744', 1732119, 'zakariamtougui', 5),
(7, 'nafie.boudakkou@gmail.com', 'nafie', 'Nafie Boudakkou', 'boudakkou', '$2a$10$nonaMLJVMajxfNCJHYTZFuuIxsgIFR1rD6BCXaf0Or0ZVuEaM3kOe', '+212611666744', 1517198, 'nafieboudakkou', 6),
(8, 'najlae.sadrat@gmail.com', 'najlae', 'Najlae Sadrat', 'sadrat', '$2a$10$SVisoEzcOlgeUnPaKnChqeRndxBlilgOHqeNbefhTQwjT3M5psRTe', '+212647011491', 1372179, 'najlaesadrat', 3),
(9, 'new@gmail.com', 'new', 'jiranew', 'new', '$2a$10$eHhT9vv6jG7N4WgvhvsOReslh5B5a80Q2XfCowkStRZNFY5rkZ1KO', NULL, 999999, 'new', 6),
(10, 'new2@gmail.com', 'new2', 'jiranew2', 'new2', '$2a$10$qYD/57tljNBHicanrrcnge0lPVJh5YZPl37P9wbFyuq7Sql5hGcHS', NULL, 555555, 'new2', 6),
(11, 'new3@gmail.com', 'new3', 'jiranew3', 'new3', '$2a$10$mnCXzc/yIdFyx0ZWPmMn2ebZvq2R2PpyiX.umKHhQj1bAbtEgPXMG', NULL, 142514, 'new3', 6),
(12, 'hamide@gmail.com', 'hamid', 'hamidjira', 'ham', '$2a$10$71efuCQM3s/LD0.DACfeKeORBqOQx/tD6mDasWt7O2dkBc73AKzJu', NULL, 144444, 'hamide', 6),
(13, 'new4@gmail.com', 'new4', 'jiranew4', 'new4', '$2a$10$c4wz/9vEfZ45B6JzKTK14Oxsk8.r.FqVRzbRJR8j5fdxleT0N1Ddi', NULL, 444444, 'new4', 6),
(14, 'jalal@gmail.com', 'jalal', 'jalaljira', 'jalal', '$2a$10$aA7OvSC4.jsd/CZlfI3nmuxeQcHm/.3c2FTF2FVqibsSjaa23En26', '0632132132', 987000, 'jalal', 5),
(15, 'karim@gmail.com', 'karim', 'karimjira', 'karim', '$2a$10$erurPc12ClxwnOfpV2Pgze1G0rPw8WznQAzW7.EApQLtqTWjlgMq.', '0698712354', 556644, 'karim', 5),
(16, 'yousef@gmail.com', 'yousef', 'yousefjira', 'yousef', '$2a$10$OVVCI8OUTn23u1ONUJtc9.l2um751pQ3GIe77GaNMDYb6PUEzPO7y', '+212685236911', 99991, 'yousef', 6),
(17, 'amin@gmail.com', 'amin', 'aminjira', 'amin', '$2a$10$coOaSyJ8AgTQBy2U38pL0OobiPoTTpAUwayCu0YtdFvOgSDOY/uk.', '0654126555', 987987, 'amin', 6),
(18, 'taha@gmail.com', 'taha', 'tahajira', 'taha', '$2a$10$.lP7aaLy9pPl8RQYrTc8dOCpkrUpkf0gGvS4OJpE4QCkjJM8P9T6u', '0632112366', 1471470, 'taha', 6),
(19, 'walid@gmail.com', 'walid', 'walidjira', 'walid', '$2a$10$El15cYbFFN.XlWt72yEZN.D3JdkJ0UC9vtNeZ59/LpJITMgzLeSIu', '0654111444', 199998, 'walid', 6),
(20, 'dd@dd.com', 'dddd', 'ddjira', 'ddd', '$2a$10$suDwhYyhFWMHnKPgAESi8uwjfNHhHzO2nLJAu3C51aetex1.bNj.y', '0655555555', 191991, 'ddd', 6),
(21, 'qqq@qq', 'qqq', 'qqq', 'qqq', '$2a$10$cZ4LRNYOsH8ryra9RlgcJObt4r9P9StVs9CqrQOgI6B0I6EOAd0hu', '0255555555', 5551110, 'qqq', 6),
(22, 'pppp@ppp', 'ppp', 'ppp', 'ppp', '$2a$10$OCyEgIvjeGAuEVgWJMPXQO6gdoca/yYtMVo1jT5VXovlkofF1b6l.', '0222222221', 777333, 'ppp', 6),
(23, 'ooo@oooo.com', 'ooo', 'ooo', 'ooo', '$2a$10$A0S34yhBeKRr/6fa0d9Qd.X2lLmbs28PxruuuNFDF4sp8M.3/nYBq', '0666666666', 55446633, 'ooo', 6),
(24, 'kkk@kkk.com', 'kkk', 'kkk', 'kkk', '$2a$10$7KRcpSQSaZix3iOv3RcBieyattF4EpWJhZdWyMNQDM/7XXLzy0ANK', '0665565656', 141235, 'kkk', 6),
(25, 'ccc@ccc', 'ccc', 'ccc', 'ccc', '$2a$10$CS/aVaoFUb28QZQBEMJ3RePgUyV3tX8r6gLx0ZWR2swivzBaEi2UC', '0655555555', 5541369, 'ccc', 6),
(26, 'upd4@gmail.com', 'upd4', 'upd4', 'upd4', '$2a$10$gw3KdV5mab1oIK16XfvEb.Y.dQhTPNEoBt25WH3KdlLMcXOikmru2', '0614141414', 66666, 'upd4', 5),
(27, 'fff@fff', 'fff', 'fff', 'fff', '$2a$10$nvJpiDA7pM2jBwNmB7IoS./okoJdFXm.DTidftlhQO8tHGk/E0Q.i', '0655256566', 2140032, 'fff', 5),
(28, 'uuu@uuu', 'uuu', 'uuu', 'uuu', '$2a$10$t6Z0QgDsSW8937wAXfERBuQRi3zanGzAI4zwwJNg/4pjHGOT8tJnm', '0555555555', 1400225, 'uuu', 6),
(29, 'rrr@rrr', 'rrr', 'rrr', 'rrr', '$2a$10$Q04.xzSd/Rvjw1OoTUxbeuMQn3zNn2lEKvRbHNzPIRTBnuRr8kh1m', '0655555555', 250112, 'rrr', 5);

-- --------------------------------------------------------



INSERT INTO `user_projects` (`user_id`, `project_id`) VALUES
(3, 1),
(3, 2),
(4, 1),
(5, 1),
(8, 4),
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
(29, 1);

