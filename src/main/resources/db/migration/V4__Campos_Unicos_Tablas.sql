-- =============================================================================
-- CAMPOS UNICOS 
-- =============================================================================

CREATE UNIQUE INDEX nombre_cargos_unique 
ON hsdm.cargos (LOWER(descripcion));

CREATE UNIQUE INDEX nombre_proveedores_unique 
ON hsdm.proveedores (LOWER(nombre));

CREATE UNIQUE INDEX nombre_secciones_unique 
ON hsdm.secciones (LOWER(descripcion));

CREATE UNIQUE INDEX nombre_servicios_unique 
ON hsdm.servicios (LOWER(nombre_servicio));

CREATE UNIQUE INDEX nombre_unidades_unique 
ON hsdm.unidades (LOWER(nombre));

