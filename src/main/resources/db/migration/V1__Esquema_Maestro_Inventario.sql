-- =============================================================================
-- 1. ESTRUCTURA DE CATALOGACIÓN (PUBLICACIÓN 15 - CONTRALORÍA)
-- =============================================================================

CREATE TABLE grupos (
    codigo VARCHAR(2) PRIMARY KEY, -- Ej: '01'
    descripcion VARCHAR(200) NOT NULL
);

CREATE TABLE subgrupos (
    grupo_codigo VARCHAR(2) REFERENCES grupos(codigo),
    codigo VARCHAR(20) NOT NULL, -- Ej: '01'
    descripcion VARCHAR(200) NOT NULL,
    PRIMARY KEY (grupo_codigo, codigo)
);

CREATE TABLE conceptos (
    codigo INTEGER PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    tipo VARCHAR(1) NOT NULL -- 'E' para Entrada, 'S' para Salida
);

-- =============================================================================
-- 2. TABLAS MAESTRAS (CATÁLOGOS)
-- =============================================================================

CREATE TABLE unidades (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    abreviatura VARCHAR(10) NOT NULL
);

CREATE TABLE proveedores (
    rif VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(250) NOT NULL,
    direccion TEXT,
    telefono VARCHAR(50)
);

CREATE TABLE articulos (
    id SERIAL PRIMARY KEY,
    codigo_barra VARCHAR(50),
    nombre VARCHAR(250) NOT NULL,
    unidad_id INTEGER REFERENCES unidades(id),
    grupo_cod VARCHAR(2),
    subgrupo_cod VARCHAR(20), -- Ajustado a 20 para coincidir con subgrupos.codigo
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (grupo_cod, subgrupo_cod) REFERENCES subgrupos(grupo_codigo, codigo)
);

-- =============================================================================
-- 3. ORGANIZACIÓN (HOSPITALES Y SECCIONES)
-- =============================================================================

CREATE TABLE hospitales (
    id SERIAL PRIMARY KEY,
    rif VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(250) NOT NULL,
    direccion TEXT,
    estado BOOLEAN DEFAULT TRUE -- Recuperado para que V5 no falle
);

CREATE TABLE secciones (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    descripcion VARCHAR(200) NOT NULL, -- Ej: 'ONG', 'Almacén Central'
    seleccionada BOOLEAN DEFAULT FALSE,
    estado BOOLEAN DEFAULT TRUE
);

-- =============================================================================
-- 4. MOVIMIENTOS Y DOCUMENTOS
-- =============================================================================

CREATE TABLE documentos (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    seccion_id INTEGER REFERENCES secciones(id),
    concepto_id INTEGER REFERENCES conceptos(codigo),
    tipo VARCHAR(10) NOT NULL, -- 'ENTRADA' o 'SALIDA'
    numero_provisional VARCHAR(50), -- Para el modo "Borrador"
    correlativo_legal INTEGER,    -- Reinicia cada mes (Pub. 15)
    mes_legal INTEGER,
    anio_legal INTEGER,
    fecha_emision DATE NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado INTEGER DEFAULT 0, -- 0: Borrador, 1: Asentado, 2: Anulado
    observaciones TEXT,
    valor_total NUMERIC(20,4) DEFAULT 0
);

-- =============================================================================
-- 5. KARDEX Y SALDOS UNIFICADOS
-- =============================================================================

CREATE TABLE saldos (
    articulo_id INTEGER REFERENCES articulos(id),
    seccion_id INTEGER REFERENCES secciones(id),
    hospital_id INTEGER REFERENCES hospitales(id),
    stock_actual NUMERIC(20,8) DEFAULT 0,
    costo_promedio NUMERIC(20,10) DEFAULT 0,
    PRIMARY KEY (articulo_id, seccion_id)
);

CREATE TABLE kardex (
    id SERIAL PRIMARY KEY,
    documento_id INTEGER REFERENCES documentos(id),
    articulo_id INTEGER REFERENCES articulos(id),
    seccion_id INTEGER REFERENCES secciones(id),
    cantidad NUMERIC(20,8) NOT NULL,
    costo_unitario NUMERIC(20,10) NOT NULL,
    saldo_cantidad_post NUMERIC(20,8),
    saldo_costo_prom_post NUMERIC(20,10)
);

COMMENT ON TABLE saldos IS 'Tabla unificada de existencias y costos por sección';

-- =============================================================================
-- 6. TABLAS OPERATIVAS ADICIONALES
-- =============================================================================

CREATE TABLE cargos (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    descripcion VARCHAR(100) NOT NULL, -- Director, Administrador...
    cedula_firmante VARCHAR(20) DEFAULT 'NO ASIGNADO',
    seccion_id INTEGER REFERENCES secciones(id)
);

CREATE TABLE servicios (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    nombre_servicio VARCHAR(200) NOT NULL,
    cedula_firmante VARCHAR(20) DEFAULT 'NO ASIGNADO',
    seccion_id INTEGER REFERENCES secciones(id)
);

CREATE TABLE almacenes (
    codigo_almacen VARCHAR(20) PRIMARY KEY, -- El código único del Ministerio
    hospital_id INTEGER REFERENCES hospitales(id),
    denominacion VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(100),
    seccion_id INTEGER REFERENCES secciones(id),
    es_principal BOOLEAN DEFAULT FALSE,
    alias VARCHAR(10)
);

CREATE TABLE inicios (
    id SERIAL PRIMARY KEY,
    estado INTEGER NOT NULL DEFAULT 0, -- 0: Cerrado, 1: Abierto
    fecha_ultimo_acceso TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Registro inicial de control
INSERT INTO inicios (estado) VALUES (0);