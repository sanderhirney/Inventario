-- =============================================================================
-- 1. ESTRUCTURA DE CATALOGACIÓN (PUBLICACIÓN 15 - CONTRALORÍA)
-- =============================================================================

CREATE TABLE grupos (
    codigo VARCHAR(2) PRIMARY KEY, -- Ej: '01'
    descripcion VARCHAR(200) NOT NULL
);

CREATE TABLE subgrupos (
    grupo_codigo VARCHAR(2) REFERENCES grupos(codigo),
    codigo VARCHAR(4) NOT NULL, -- Ej: '01'
    descripcion VARCHAR(200) NOT NULL,
    PRIMARY KEY (grupo_codigo, codigo)
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
    subgrupo_cod VARCHAR(2),
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
    direccion TEXT
);

CREATE TABLE secciones (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    descripcion VARCHAR(200) NOT NULL, -- Ej: 'ONG', 'Almacén Central'
    seleccionada BOOLEAN DEFAULT FALSE,
    estado BOOLEAN DEFAULT TRUE
);

-- =============================================================================
-- 4. MOVIMIENTOS Y DOCUMENTOS (SIN TABLAS TEMPORALES)
-- =============================================================================

CREATE TABLE documentos (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    seccion_id INTEGER REFERENCES secciones(id),
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
-- 5. KARDEX Y SALDOS UNIFICADOS (EL CORAZÓN DEL COSTO PROMEDIO)
-- =============================================================================

CREATE TABLE saldos (
    articulo_id INTEGER REFERENCES articulos(id),
    seccion_id INTEGER REFERENCES secciones(id),
    hospital_id INTEGER REFERENCES hospitales(id),
    stock_actual NUMERIC(20,8) DEFAULT 0,
    costo_promedio NUMERIC(20,10) DEFAULT 0, -- Máxima precisión para devaluación
    PRIMARY KEY (articulo_id, seccion_id)
);

CREATE TABLE kardex (
    id SERIAL PRIMARY KEY,
    documento_id INTEGER REFERENCES documentos(id),
    articulo_id INTEGER REFERENCES articulos(id),
    seccion_id INTEGER REFERENCES secciones(id),
    cantidad NUMERIC(20,8) NOT NULL,
    costo_unitario NUMERIC(20,10) NOT NULL,
    -- Saldo resultante después de este movimiento (para reportes históricos)
    saldo_cantidad_post NUMERIC(20,8),
    saldo_costo_prom_post NUMERIC(20,10)
);

-- Comentario de auditoría para Flyway
COMMENT ON TABLE saldos IS 'Tabla unificada de existencias y costos por sección';