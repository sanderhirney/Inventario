-- 1. TABLA DE HOSPITALES (La configuración física)
CREATE TABLE public.config_hospitales (
    id_hospital SERIAL PRIMARY KEY,
    nombre_hospital VARCHAR(250) NOT NULL,
    rif VARCHAR(20) UNIQUE NOT NULL,
    direccion TEXT,
    nombre_esquema VARCHAR(50) UNIQUE NOT NULL, -- Ej: 'hosp_central'
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. TABLA DE USUARIOS (El control de acceso)
CREATE TABLE public.usuarios_sistema (
    id_usuario SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(20) CHECK (rol IN ('ADMIN', 'USER')),
    id_hospital INTEGER REFERENCES public.config_hospitales(id_hospital), 
    estado BOOLEAN DEFAULT TRUE
);

-- Insertamos el primer admin para poder entrar al sistema
INSERT INTO usuarios_sistema (username, password, rol, id_hospital)
VALUES ('admin', 'Hefestos123', 'ADMIN', NULL); 
-- El NULL es porque el admin no pertenece a un hospital específico, es global.