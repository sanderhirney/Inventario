-- =============================================================================
-- 1. ORGANIZACIÓN BASE (PRIMERO LAS TABLAS DE REFERENCIA)
-- =============================================================================
CREATE TABLE hospitales (
    id SERIAL PRIMARY KEY,
    rif VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(250) NOT NULL,
    direccion TEXT,
    estado BOOLEAN DEFAULT TRUE 
);

CREATE TABLE secciones (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    descripcion VARCHAR(200) NOT NULL, 
    seleccionada BOOLEAN DEFAULT FALSE,
    estado BOOLEAN DEFAULT TRUE
);

-- =============================================================================
-- 2. ESTRUCTURA DE CATALOGACIÓN
-- =============================================================================
CREATE TABLE grupos (
    hospital_id INTEGER NOT NULL,
    codigo VARCHAR(2) NOT NULL, 
    descripcion VARCHAR(200) NOT NULL,
    PRIMARY KEY (hospital_id, codigo),
    FOREIGN KEY (hospital_id) REFERENCES hospitales(id)
);

CREATE TABLE subgrupos (
    hospital_id INTEGER NOT NULL,
    grupo_codigo VARCHAR(2) NOT NULL,
    codigo VARCHAR(20) NOT NULL, 
    descripcion VARCHAR(200) NOT NULL,
    PRIMARY KEY (hospital_id, grupo_codigo, codigo),
    FOREIGN KEY (hospital_id, grupo_codigo) REFERENCES grupos(hospital_id, codigo)
);

CREATE TABLE conceptos (
    codigo INTEGER PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    descripcion VARCHAR(100) NOT NULL,
    tipo VARCHAR(1) NOT NULL -- 'E' entrada, 'S' salida
);

-- =============================================================================
-- 3. TABLAS MAESTRAS (CATÁLOGOS)
-- =============================================================================
CREATE TABLE unidades (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    nombre VARCHAR(50) NOT NULL,
    abreviatura VARCHAR(10) NOT NULL
);

CREATE TABLE proveedores (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    rif VARCHAR(20) NOT NULL,
    nombre VARCHAR(250) NOT NULL,
    direccion TEXT,
    telefono VARCHAR(50)
);

CREATE TABLE articulos (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    codigo_barra VARCHAR(50),
    nombre VARCHAR(250) NOT NULL,
    unidad_id INTEGER REFERENCES unidades(id),
    grupo_cod VARCHAR(2),
    subgrupo_cod VARCHAR(20),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (hospital_id, grupo_cod, subgrupo_cod) REFERENCES subgrupos(hospital_id, grupo_codigo, codigo)
);

-- =============================================================================
-- 4. MOVIMIENTOS Y DOCUMENTOS
-- =============================================================================
CREATE TABLE documentos (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    seccion_id INTEGER REFERENCES secciones(id),
    concepto_id INTEGER REFERENCES conceptos(codigo),
    tipo VARCHAR(10) NOT NULL, 
    numero_provisional VARCHAR(50), 
    correlativo_legal INTEGER,    
    mes_legal INTEGER,
    anio_legal INTEGER,
    fecha_emision DATE NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado INTEGER DEFAULT 0, 
    observaciones TEXT,
    valor_total NUMERIC(20,4) DEFAULT 0
);

-- =============================================================================
-- 5. KARDEX Y SALDOS
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
    hospital_id INTEGER REFERENCES hospitales(id),
    documento_id INTEGER REFERENCES documentos(id),
    articulo_id INTEGER REFERENCES articulos(id),
    seccion_id INTEGER REFERENCES secciones(id),
    cantidad NUMERIC(20,8) NOT NULL,
    costo_unitario NUMERIC(20,10) NOT NULL,
    saldo_cantidad_post NUMERIC(20,8),
    saldo_costo_prom_post NUMERIC(20,10)
);

-- =============================================================================
-- 6. TABLAS OPERATIVAS ADICIONALES
-- =============================================================================
CREATE TABLE cargos (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    descripcion VARCHAR(100) NOT NULL, 
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
    codigo_almacen VARCHAR(20) PRIMARY KEY, 
    hospital_id INTEGER REFERENCES hospitales(id),
    denominacion VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(100),
    seccion_id INTEGER REFERENCES secciones(id),
    es_principal BOOLEAN DEFAULT FALSE,
    es_despacho BOOLEAN DEFAULT TRUE,
    es_destino BOOLEAN DEFAULT TRUE,
    alias VARCHAR(10),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE inicios (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    estado INTEGER NOT NULL DEFAULT 0, 
    fecha_ultimo_acceso TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE configuraciones (
    id SERIAL PRIMARY KEY,
    hospital_id INTEGER REFERENCES hospitales(id),
    seccion_id INTEGER REFERENCES secciones(id),
    decimales_costos INTEGER DEFAULT 4, -- Para montos monetarios
    decimales_cantidades INTEGER DEFAULT 2, -- Para stock/kardex
    moneda_simbolo VARCHAR(5) DEFAULT '$',
    permite_stock_negativo BOOLEAN DEFAULT FALSE,
    UNIQUE (hospital_id, seccion_id) -- Solo una configuración por sección
);

-- =============================================================================
-- 7. ÍNDICES ÚNICOS (MULTITENANCY)
-- =============================================================================

CREATE UNIQUE INDEX idx_secciones_hosp_desc ON secciones (hospital_id, LOWER(descripcion));
CREATE UNIQUE INDEX idx_cargos_hosp_sec_desc ON cargos (hospital_id,  LOWER(descripcion));
CREATE UNIQUE INDEX idx_servicios_hosp_sec_desc ON servicios (hospital_id,  LOWER(nombre_servicio));
CREATE UNIQUE INDEX idx_proveedores_hosp_rif ON proveedores (hospital_id, rif);
CREATE UNIQUE INDEX idx_unidades_hosp_nombre ON unidades (hospital_id, LOWER(nombre));
CREATE UNIQUE INDEX idx_articulos_hosp_nombre on articulos(hospital_id, LOWER(nombre));

-- RESET MENSUAL PUBLICACIÓN 15
CREATE UNIQUE INDEX idx_documentos_salida_mensual 
ON documentos (hospital_id, seccion_id, concepto_id, mes_legal, anio_legal, correlativo_legal)
WHERE tipo = 'SALIDA';

CREATE UNIQUE INDEX idx_documentos_entrada_prov 
ON documentos (hospital_id, seccion_id, numero_provisional)
WHERE tipo = 'ENTRADA';

-- =============================================================================
-- 8. BOOTSTRAP (INICIO AUTOMÁTICO)
-- =============================================================================

-- 1. Insertar el Hospital Dueño
INSERT INTO hospitales (id, rif, nombre, direccion, estado) 
VALUES (1, 'G-00000000-0', 'HOSPITAL DE CONFIGURACION', 'DIRECCION', TRUE);

-- 2. Reiniciar el contador para que el siguiente hospital sea el 2
SELECT setval(pg_get_serial_sequence('hospitales', 'id'), 1);

-- 3. Insertar la Sección donde aterrizará el sistema
INSERT INTO secciones (hospital_id, descripcion, seleccionada, estado) 
VALUES (1, 'AÑO FISCAL ', TRUE, TRUE);


-- 4.- Insertamos la configuración para el Hospital 1 y la Sección 1
INSERT INTO configuraciones (hospital_id, seccion_id, decimales_costos, decimales_cantidades) 
VALUES (1, 1, 4, 2);

-- =============================================================================
-- 8. CONCEPTOS
-- =============================================================================
-- CONCEPTOS DE ENTRADA (E)
INSERT INTO conceptos (codigo, hospital_id, descripcion, tipo) VALUES  
(01, 1, 'Inventario Inicial', 'E'), 
(02, 1, 'Entradas por traspasos de otros almacenes', 'E'), 
(03, 1, 'Compras', 'E'), 
(06, 1, 'Produccion y transformacion de materiales', 'E'), 
(07, 1, 'Suministro de otras entidades', 'E'), 
(08, 1, 'Reintegro o devoluciones', 'E'), 
(10, 1, 'Reconstruccion de equipos', 'E'), 
(11, 1, 'Entradas por donacion', 'E'), 
(12, 1, 'Entradas por permuta', 'E'), 
(14, 1, 'Omision en inventarios y sobrantes', 'E'),  
(17, 1, 'Incorporacion para corregir registros anteriores', 'E'), 
(19, 1, 'Entradas o incorporaciones por otros conceptos', 'E');

-- CONCEPTOS DE SALIDA (S)
INSERT INTO conceptos (codigo, hospital_id, descripcion, tipo) VALUES  
(51, 1, 'Salidas por traspasos a otros almacenes', 'S'), 
(52, 1, 'Venta', 'S'), 
(53, 1, 'Entregas de bienes muebles en deposito', 'S'), 
(54, 1, 'Suministro de materiales de consumo', 'S'), 
(55, 1, 'Desarme', 'S'), 
(56, 1, 'Desincorporacion por inservibilidad', 'S'), 
(57, 1, 'Desincorporacion por deterioro', 'S'), 
(58, 1, 'Desincorporacion por mermas', 'S'), 
(60, 1, 'Faltantes por investigar', 'S'), 
(61, 1, 'Salidas por permuta', 'S'), 
(62, 1, 'Salidas por donacion', 'S'), 
(63, 1, 'Prestamos autorizados', 'S'), 
(66, 1, 'Desincorporacion para corregir registros anteriores', 'S'), 
(69, 1, 'Salidas o desincorporacion por otros conceptos', 'S');
-- =============================================================================
-- 9. GRUPOS Y SUB GRUPOS
-- =============================================================================
DO $$
DECLARE
    -- Cambiados a VARCHAR para coincidir con tu definición de tabla grupos(codigo)
    g3_cod VARCHAR(2) := '3'; 
    g4_cod VARCHAR(2) := '4';    
BEGIN
    -- Crear Grupos vinculados al Hospital 1
    INSERT INTO grupos (hospital_id, codigo, descripcion) VALUES (1, g3_cod, 'Bienes Muebles en Depósito');
    INSERT INTO grupos (hospital_id, codigo, descripcion) VALUES (1, g4_cod, 'Materiales de Consumo');

    -- ==========================================
    -- SUBGRUPOS DEL GRUPO 3
    -- ==========================================
    INSERT INTO subgrupos (hospital_id, grupo_codigo, codigo, descripcion) VALUES  
    (1, g3_cod, '01', 'Maquinas, muebles y demas equipos de oficina'),
    (1, g3_cod, '02', 'Mobiliario y enseres de alojamiento'),
    (1, g3_cod, '03', 'Maquinaria y demas equipos de construcción, campo, industria y taller'),
    (1, g3_cod, '03-01', 'Equipos de taller de uso general'),
    (1, g3_cod, '03-02', 'Maquinaria y equipo de construccion y conservacion'),
    (1, g3_cod, '03-03', 'Maquinaria y equipo para mantenimiento de automotores'),
    (1, g3_cod, '03-04', 'Maquinaria y equipo agricola y pecuario'),
    (1, g3_cod, '03-05', 'Maquinaria y equipo de artes graficas'),
    (1, g3_cod, '03-06', 'Maquinaria industrial'),
    (1, g3_cod, '04', 'Equipos de transporte'),
    (1, g3_cod, '04-01', 'Vehiculos automotores terrestres'),
    (1, g3_cod, '04-02', 'Otros vehiculos terrestres'),
    (1, g3_cod, '05', 'Equipos de telecomunicaciones'),
    (1, g3_cod, '06', 'Equipos Medico-Quirurgicos, dentales y veterinarios'),
    (1, g3_cod, '06-01', 'Equipos Medico-Quirurgicos y de veterinaria'),
    (1, g3_cod, '06-02', 'Equipos dentales'),
    (1, g3_cod, '07', 'Equipos cientificos y de enseñanza'),
    (1, g3_cod, '07-01', 'Equipos cientificos y de laboratorio'),
    (1, g3_cod, '07-02', 'Equipos de enseñanza, deporte y recreacion'),
    (1, g3_cod, '07-03', 'Elementos de culto'),
    (1, g3_cod, '08', 'Colecciones culturales, artisticas e historicas'),
    (1, g3_cod, '08-01', 'Libros'),
    (1, g3_cod, '08-02', 'Colecciones cientificas'),
    (1, g3_cod, '08-03', 'Colecciones artisticas y ornamentales'),
    (1, g3_cod, '09', 'Armamento y equipo de defensa'),
    (1, g3_cod, '11', 'Otros bienes muebles en deposito');

    -- ==========================================
    -- SUBGRUPOS DEL GRUPO 4
    -- ==========================================
    INSERT INTO subgrupos (hospital_id, grupo_codigo, codigo, descripcion) VALUES  
    (1, g4_cod, '20', 'Alimentos y bebidas'),
    (1, g4_cod, '21', 'Materiales agrícolas y pecuarios'),
    (1, g4_cod, '21-A', 'Abonos'),
    (1, g4_cod, '21-B', 'Alimentos para animales'),
    (1, g4_cod, '21-C', 'Insecticidas'),
    (1, g4_cod, '21-D', 'Semillas'),
    (1, g4_cod, '22', 'Drogas medicinas materiales odontologicas, de laboratorio de sanidad y similares'),
    (1, g4_cod, '22-A', 'Drogas medicinas y elementos de curacion para pacientes'),
    (1, g4_cod, '22-B', 'Materiales de odontologia'),
    (1, g4_cod, '22-C', 'Materiales para laboratorio'),
    (1, g4_cod, '22-D', 'Suministros menores ortopedicos'),
    (1, g4_cod, '22-E', 'Drogas, Med., y elementos de C.(PTES)'),
    (1, g4_cod, '22-G', 'Sustancias para laboratorio'),
    (1, g4_cod, '22-H', 'Materiales para rayos X'),
    (1, g4_cod, '22-J', 'Materiales Médicos Quirurgicos'),
    (1, g4_cod, '23', 'Materiales de construccion'),
    (1, g4_cod, '23-A', 'Materiales basicos y estructurales'),
    (1, g4_cod, '23-B', 'Materiales y utiles para instituciones sanitarias'),
    (1, g4_cod, '24', 'Materiales para industria y taller'),
    (1, g4_cod, '25', 'Repuestos accesorios y herramientas menores'),
    (1, g4_cod, '25-H', 'Herramientas menores'),
    (1, g4_cod, '27', 'Utiles de escritorio y oficina'),
    (1, g4_cod, '28', 'Materiales de uso personal de alojamiento y de limpieza'),
    (1, g4_cod, '28-H', 'Utiles y materiales de aseo'),
    (1, g4_cod, '28-K', 'Vestuario para pacientes'),
    (1, g4_cod, '30-A', 'Combustible'),
    (1, g4_cod, '30-B', 'Aceites y grasas lubricantes'),
    (1, g4_cod, '30-D', 'Gas combustible');

END $$;

-- =============================================================================
-- 10. CONTROL DE INICIO
-- =============================================================================
INSERT INTO inicios (hospital_id, estado, fecha_ultimo_acceso) 
VALUES (1, 1, CURRENT_TIMESTAMP);

