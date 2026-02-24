-- V3__Carga_Conceptos_Oficiales.sql

-- Limpieza preventiva (opcional si quieres asegurar que no haya duplicados)
DELETE FROM conceptos;

-- CONCEPTOS DE ENTRADA (E)
INSERT INTO conceptos (codigo, descripcion, tipo) VALUES 
(01, 'Inventario Inicial', 'E'), 
(02, 'Entradas por traspasos de otros almacenes', 'E'), 
(03, 'Compras', 'E'), 
(06, 'Produccion y transformacion de materiales', 'E'), 
(07, 'Suministro de otras entidades', 'E'), 
(08, 'Reintegro o devoluciones', 'E'), 
(10, 'Reconstruccion de equipos', 'E'), 
(11, 'Entradas por donacion', 'E'), 
(12, 'Entradas por permuta', 'E'), 
(14, 'Omision en inventarios y sobrantes', 'E'),  
(17, 'Incorporacion para corregir registros anteriores', 'E'), 
(19, 'Entradas o incorporaciones por otros conceptos', 'E');

-- CONCEPTOS DE SALIDA (S)
INSERT INTO conceptos (codigo, descripcion, tipo) VALUES 
(51, 'Salidas por traspasos a otros almacenes', 'S'), 
(52, 'Venta', 'S'), 
(53, 'Entregas de bienes muebles en deposito', 'S'), 
(54, 'Suministro de materiales de consumo', 'S'), 
(55, 'Desarme', 'S'), 
(56, 'Desincorporacion por inservibilidad', 'S'), 
(57, 'Desincorporacion por deterioro', 'S'), 
(58, 'Desincorporacion por mermas', 'S'), 
(60, 'Faltantes por investigar', 'S'), 
(61, 'Salidas por permuta', 'S'), 
(62, 'Salidas por donacion', 'S'), 
(63, 'Prestamos autorizados', 'S'), 
(66, 'Desincorporacion para corregir registros anteriores', 'S'), 
(69, 'Salidas o desincorporacion por otros conceptos', 'S');