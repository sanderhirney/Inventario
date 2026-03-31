-- 1. Añadimos las columnas permitiendo NULL (para no romper registros existentes si los hay)
ALTER TABLE documentos 
ADD COLUMN proveedor_id INTEGER,
ADD COLUMN servicio_id INTEGER;

-- 2. Creamos las llaves foraneas (Asegúrate de que los nombres de tabla sean correctos)
ALTER TABLE documentos 
ADD CONSTRAINT fk_documento_proveedor 
    FOREIGN KEY (proveedor_id) REFERENCES proveedores(id),
ADD CONSTRAINT fk_documento_servicio 
    FOREIGN KEY (servicio_id) REFERENCES servicios(id);

-- 3. Comentario para auditoría de base de datos
COMMENT ON COLUMN documentos.proveedor_id IS 'ID del proveedor para entradas por compra';
COMMENT ON COLUMN documentos.servicio_id IS 'ID del servicio/unidad para salidas por despacho';