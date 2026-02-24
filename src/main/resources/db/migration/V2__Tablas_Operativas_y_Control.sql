-- 1. CONCEPTOS (Motivos de entrada/salida según Contraloría)
CREATE TABLE conceptos (
    codigo INTEGER PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    tipo VARCHAR(1) NOT NULL -- 'E' para Entrada, 'S' para Salida
);

-- 2. CARGOS (Para firmas en reportes)
CREATE TABLE cargos (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    descripcion VARCHAR(100) NOT NULL, -- Director, Administrador...
    cedula_firmante VARCHAR(20) DEFAULT 'NO ASIGNADO',
    seccion_id INTEGER REFERENCES secciones(id)
);

-- 3. SERVICIOS (Destinos de las salidas)
CREATE TABLE servicios (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    nombre_servicio VARCHAR(200) NOT NULL,
    cedula_firmante VARCHAR(20) DEFAULT 'NO ASIGNADO',
    seccion_id INTEGER REFERENCES secciones(id)
);

-- 4. ALMACENES (Gestión de depósitos y códigos ministeriales)
CREATE TABLE almacenes (
    codigo_almacen VARCHAR(20) PRIMARY KEY, -- El código único del Ministerio
    hospital_id INTEGER REFERENCES hospitales(id),
    denominacion VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(100),
    seccion_id INTEGER REFERENCES secciones(id),
    es_principal BOOLEAN DEFAULT FALSE,
    alias VARCHAR(10)
);

-- 5. CONTROL DE INICIO (Sesión única)
CREATE TABLE inicios (
    id SERIAL PRIMARY KEY,
    estado INTEGER NOT NULL DEFAULT 0, -- 0: Cerrado, 1: Abierto
    fecha_ultimo_acceso TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insertamos el registro inicial de control
INSERT INTO inicios (estado) VALUES (0);

-- Vincular el documento con su concepto legal
ALTER TABLE documentos 
ADD COLUMN concepto_id INTEGER REFERENCES conceptos(codigo);

-- Comentario para el programador
COMMENT ON COLUMN documentos.concepto_id IS 'Relación con el catálogo de motivos legales de la Pub. 15';