-- 1. Creamos la función que ejecutará la lógica
CREATE OR REPLACE FUNCTION fn_inicializar_articulo_en_saldos()
RETURNS TRIGGER AS $$
BEGIN
    -- Insertamos el registro de saldo inicial para cada sección del hospital
    INSERT INTO saldos (articulo_id, seccion_id, hospital_id, stock_actual, costo_promedio)
    SELECT 
        NEW.id,           -- El ID del artículo recién creado
        s.id,             -- El ID de cada sección encontrada
        NEW.hospital_id,  -- El ID del hospital del artículo
        0,                -- Stock inicial en cero
        0                 -- Costo inicial en cero
    FROM secciones s
    WHERE s.hospital_id = NEW.hospital_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- 2. Creamos el disparador (Trigger) vinculado a la tabla articulos
CREATE TRIGGER trg_despues_crear_articulo
AFTER INSERT ON articulos
FOR EACH ROW
EXECUTE FUNCTION fn_inicializar_articulo_en_saldos();