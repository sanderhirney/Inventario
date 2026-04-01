-- 1. Estandarizar TOTAL en Documentos (de 4 a 10)
ALTER TABLE documentos 
    ALTER COLUMN valor_total TYPE NUMERIC(20,10);

-- 2. Estandarizar CANTIDADES en Saldos (de 8 a 10 para mayor precisión)
ALTER TABLE saldos 
    ALTER COLUMN stock_actual TYPE NUMERIC(20,10);

-- 3. Estandarizar CANTIDADES y SALDOS en Kardex (de 8 a 10)
ALTER TABLE kardex 
    ALTER COLUMN cantidad TYPE NUMERIC(20,10),
    ALTER COLUMN saldo_cantidad_post TYPE NUMERIC(20,10);