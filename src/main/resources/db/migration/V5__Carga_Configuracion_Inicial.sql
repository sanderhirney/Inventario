-- V7__Carga_Configuracion_Inicial.sql

-- 1. Insertamos el Hospital (Empresa) para que el software tenga donde "anclarse"
INSERT INTO hsdm.hospitales (nombre, rif, direccion, estado) 
VALUES ('HOSPITAL CENTRAL DE PRUEBA', 'G-00000000-0', 'DIRECCION GENERAL', TRUE);

-- 2. Insertamos la Sección Inicial vinculada al hospital que acabamos de crear
-- Usamos SELECT id para no adivinar el número que le puso Postgres
INSERT INTO hsdm.secciones (hospital_id, descripcion, seleccionada, estado) 
SELECT id, 'ALMACÉN CENTRAL / DEPÓSITO', TRUE, TRUE 
FROM hsdm.hospitales 
WHERE rif = 'G-00000000-0' 
LIMIT 1;