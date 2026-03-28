-- 1. Eliminamos el índice del V1 que solo servía para Entradas
DROP INDEX IF EXISTS idx_documentos_entrada_prov;

-- 2. Creamos la restricción de UNICIDAD DE ACERO
-- Explicación: hospital + seccion + tipo + numero (sin importar MAYÚSCULAS)
-- El WHERE (estado != 2) es MAGIA: permite que si anulas el doc, el número quede "libre" para el nuevo.
CREATE UNIQUE INDEX idx_documentos_unicidad_negocio 
ON documentos (hospital_id, seccion_id, tipo, UPPER(numero_provisional))
WHERE (estado != 2);

-- 3. Índice para rapidez en consultas de Auditoría/Clonación
CREATE INDEX idx_documentos_origen_id ON documentos(documento_origen_id);

-- 4. Comentario para que cualquier DBA sepa qué hicimos
COMMENT ON INDEX idx_documentos_unicidad_negocio IS 'Garantiza números de documento únicos por hospital y sección, permitiendo reuso tras anulación';