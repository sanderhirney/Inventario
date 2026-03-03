-- V6__Carga_Catalogo_Pub15.sql
DO $$
DECLARE
    g3_id INT;
    g4_id INT;    
BEGIN
  -- Crear Grupos Principales y capturar sus IDs
    INSERT INTO hsdm.grupos (codigo, descripcion) VALUES ('3', 'Bienes Muebles en Depósito') RETURNING codigo INTO g3_id;
    INSERT INTO hsdm.grupos (codigo, descripcion) VALUES ('4', 'Materiales de Consumo') RETURNING codigo INTO g4_id;

    -- ==========================================
    -- SUBGRUPOS DEL GRUPO 3
    -- ==========================================
    INSERT INTO hsdm.subgrupos (grupo_codigo, codigo, descripcion) VALUES 
    (g3_id, '01', 'Maquinas, muebles y demas equipos de oficina'),
    (g3_id, '02', 'Mobiliario y enseres de alojamiento'),
    (g3_id, '03', 'Maquinaria y demas equipos de construcción, campo, industria y taller'),
    (g3_id, '03-01', 'Equipos de taller de uso general'),
    (g3_id, '03-02', 'Maquinaria y equipo de construccion y conservacion'),
    (g3_id, '03-03', 'Maquinaria y equipo para mantenimiento de automotores'),
    (g3_id, '03-04', 'Maquinaria y equipo agricola y pecuario'),
    (g3_id, '03-05', 'Maquinaria y equipo de artes graficas'),
    (g3_id, '03-06', 'Maquinaria industrial'),
    (g3_id, '04', 'Equipos de transporte'),
    (g3_id, '04-01', 'Vehiculos automotores terrestres'),
    (g3_id, '04-02', 'Otros vehiculos terrestres'),
    (g3_id, '05', 'Equipos de telecomunicaciones'),
    (g3_id, '06', 'Equipos Medico-Quirurgicos, dentales y veterinarios'),
    (g3_id, '06-01', 'Equipos Medico-Quirurgicos y de veterinaria'),
    (g3_id, '06-02', 'Equipos dentales'),
    (g3_id, '07', 'Equipos cientificos y de enseñanza'),
    (g3_id, '07-01', 'Equipos cientificos y de laboratorio'),
    (g3_id, '07-02', 'Equipos de enseñanza, deporte y recreacion'),
    (g3_id, '07-03', 'Elementos de culto'),
    (g3_id, '08', 'Colecciones culturales, artisticas e historicas'),
    (g3_id, '08-01', 'Libros'),
    (g3_id, '08-02', 'Colecciones cientificas'),
    (g3_id, '08-03', 'Colecciones artisticas y ornamentales'),
    (g3_id, '09', 'Armamento y equipo de defensa'),
    (g3_id, '11', 'Otros bienes muebles en deposito');

    -- ==========================================
    -- SUBGRUPOS DEL GRUPO 4
    -- ==========================================
    INSERT INTO hsdm.subgrupos (grupo_codigo, codigo, descripcion) VALUES 
    (g4_id, '20', 'Alimentos y bebidas'),
    (g4_id, '21', 'Materiales agrícolas y pecuarios'),
    (g4_id, '21-A', 'Abonos'),
    (g4_id, '21-B', 'Alimentos para animales'),
    (g4_id, '21-C', 'Insecticidas'),
    (g4_id, '21-D', 'Semillas'),
    (g4_id, '22', 'Drogas medicinas materiales odontologicas, de laboratorio de sanidad y similares'),
    (g4_id, '22-A', 'Drogas medicinas y elementos de curacion para pacientes'),
    (g4_id, '22-B', 'Materiales de odontologia'),
    (g4_id, '22-C', 'Materiales para laboratorio'),
    (g4_id, '22-D', 'Suministros menores ortopedicos'),
    (g4_id, '22-E', 'Drogas, Med., y elementos de C.(PTES)'),
    (g4_id, '22-G', 'Sustancias para laboratorio'),
    (g4_id, '22-H', 'Materiales para rayos X'),
    (g4_id, '22-J', 'Materiales Médicos Quirurgicos'),
    (g4_id, '23', 'Materiales de construccion'),
    (g4_id, '23-A', 'Materiales basicos y estructurales'),
    (g4_id, '23-B', 'Materiales y utiles para instituciones sanitarias'),
    (g4_id, '24', 'Materiales para industria y taller'),
    (g4_id, '25', 'Repuestos accesorios y herramientas menores'),
    (g4_id, '25-H', 'Herramientas menores'),
    (g4_id, '27', 'Utiles de escritorio y oficina'),
    (g4_id, '28', 'Materiales de uso personal de alojamiento y de limpieza'),
    (g4_id, '28-H', 'Utiles y materiales de aseo'),
    (g4_id, '28-K', 'Vestuario para pacientes'),
    (g4_id, '30-A', 'Combustible'),
    (g4_id, '30-B', 'Aceites y grasas lubricantes'),
    (g4_id, '30-D', 'Gas combustible');

END $$;