-- Vincular el documento con su concepto legal
ALTER TABLE documentos 
ADD COLUMN concepto_id INTEGER REFERENCES conceptos(codigo);

-- Comentario para el programador
COMMENT ON COLUMN documentos.concepto_id IS 'Relación con el catálogo de motivos legales de la Pub. 15';