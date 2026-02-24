--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: hsdm; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA hsdm;


ALTER SCHEMA hsdm OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: almacenes; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.almacenes (
    codigo_almacen character varying(20) NOT NULL,
    hospital_id integer,
    denominacion character varying(100) NOT NULL,
    ubicacion character varying(100),
    seccion_id integer,
    es_principal boolean DEFAULT false,
    alias character varying(10)
);


ALTER TABLE hsdm.almacenes OWNER TO postgres;

--
-- Name: articulos; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.articulos (
    id integer NOT NULL,
    codigo_barra character varying(50),
    nombre character varying(250) NOT NULL,
    unidad_id integer,
    grupo_cod character varying(2),
    subgrupo_cod character varying(2),
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE hsdm.articulos OWNER TO postgres;

--
-- Name: articulos_id_seq; Type: SEQUENCE; Schema: hsdm; Owner: postgres
--

CREATE SEQUENCE hsdm.articulos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE hsdm.articulos_id_seq OWNER TO postgres;

--
-- Name: articulos_id_seq; Type: SEQUENCE OWNED BY; Schema: hsdm; Owner: postgres
--

ALTER SEQUENCE hsdm.articulos_id_seq OWNED BY hsdm.articulos.id;


--
-- Name: cargos; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.cargos (
    id integer NOT NULL,
    hospital_id integer,
    descripcion character varying(100) NOT NULL,
    cedula_firmante character varying(20) DEFAULT 'NO ASIGNADO'::character varying,
    seccion_id integer
);


ALTER TABLE hsdm.cargos OWNER TO postgres;

--
-- Name: cargos_id_seq; Type: SEQUENCE; Schema: hsdm; Owner: postgres
--

CREATE SEQUENCE hsdm.cargos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE hsdm.cargos_id_seq OWNER TO postgres;

--
-- Name: cargos_id_seq; Type: SEQUENCE OWNED BY; Schema: hsdm; Owner: postgres
--

ALTER SEQUENCE hsdm.cargos_id_seq OWNED BY hsdm.cargos.id;


--
-- Name: conceptos; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.conceptos (
    codigo integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    tipo character varying(1) NOT NULL
);


ALTER TABLE hsdm.conceptos OWNER TO postgres;

--
-- Name: documentos; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.documentos (
    id integer NOT NULL,
    hospital_id integer,
    seccion_id integer,
    tipo character varying(10) NOT NULL,
    numero_provisional character varying(50),
    correlativo_legal integer,
    mes_legal integer,
    anio_legal integer,
    fecha_emision date NOT NULL,
    fecha_registro timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    estado integer DEFAULT 0,
    observaciones text,
    valor_total numeric(20,4) DEFAULT 0
);


ALTER TABLE hsdm.documentos OWNER TO postgres;

--
-- Name: documentos_id_seq; Type: SEQUENCE; Schema: hsdm; Owner: postgres
--

CREATE SEQUENCE hsdm.documentos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE hsdm.documentos_id_seq OWNER TO postgres;

--
-- Name: documentos_id_seq; Type: SEQUENCE OWNED BY; Schema: hsdm; Owner: postgres
--

ALTER SEQUENCE hsdm.documentos_id_seq OWNED BY hsdm.documentos.id;


--
-- Name: flyway_schema_history; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE hsdm.flyway_schema_history OWNER TO postgres;

--
-- Name: grupos; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.grupos (
    codigo character varying(2) NOT NULL,
    descripcion character varying(200) NOT NULL
);


ALTER TABLE hsdm.grupos OWNER TO postgres;

--
-- Name: hospitales; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.hospitales (
    id integer NOT NULL,
    rif character varying(20) NOT NULL,
    nombre character varying(250) NOT NULL,
    direccion text
);


ALTER TABLE hsdm.hospitales OWNER TO postgres;

--
-- Name: hospitales_id_seq; Type: SEQUENCE; Schema: hsdm; Owner: postgres
--

CREATE SEQUENCE hsdm.hospitales_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE hsdm.hospitales_id_seq OWNER TO postgres;

--
-- Name: hospitales_id_seq; Type: SEQUENCE OWNED BY; Schema: hsdm; Owner: postgres
--

ALTER SEQUENCE hsdm.hospitales_id_seq OWNED BY hsdm.hospitales.id;


--
-- Name: inicios; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.inicios (
    id integer NOT NULL,
    estado integer DEFAULT 0 NOT NULL,
    fecha_ultimo_acceso timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE hsdm.inicios OWNER TO postgres;

--
-- Name: inicios_id_seq; Type: SEQUENCE; Schema: hsdm; Owner: postgres
--

CREATE SEQUENCE hsdm.inicios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE hsdm.inicios_id_seq OWNER TO postgres;

--
-- Name: inicios_id_seq; Type: SEQUENCE OWNED BY; Schema: hsdm; Owner: postgres
--

ALTER SEQUENCE hsdm.inicios_id_seq OWNED BY hsdm.inicios.id;


--
-- Name: kardex; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.kardex (
    id integer NOT NULL,
    documento_id integer,
    articulo_id integer,
    seccion_id integer,
    cantidad numeric(20,8) NOT NULL,
    costo_unitario numeric(20,10) NOT NULL,
    saldo_cantidad_post numeric(20,8),
    saldo_costo_prom_post numeric(20,10)
);


ALTER TABLE hsdm.kardex OWNER TO postgres;

--
-- Name: kardex_id_seq; Type: SEQUENCE; Schema: hsdm; Owner: postgres
--

CREATE SEQUENCE hsdm.kardex_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE hsdm.kardex_id_seq OWNER TO postgres;

--
-- Name: kardex_id_seq; Type: SEQUENCE OWNED BY; Schema: hsdm; Owner: postgres
--

ALTER SEQUENCE hsdm.kardex_id_seq OWNED BY hsdm.kardex.id;


--
-- Name: proveedores; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.proveedores (
    rif character varying(20) NOT NULL,
    nombre character varying(250) NOT NULL,
    direccion text,
    telefono character varying(50)
);


ALTER TABLE hsdm.proveedores OWNER TO postgres;

--
-- Name: saldos; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.saldos (
    articulo_id integer NOT NULL,
    seccion_id integer NOT NULL,
    hospital_id integer,
    stock_actual numeric(20,8) DEFAULT 0,
    costo_promedio numeric(20,10) DEFAULT 0
);


ALTER TABLE hsdm.saldos OWNER TO postgres;

--
-- Name: TABLE saldos; Type: COMMENT; Schema: hsdm; Owner: postgres
--

COMMENT ON TABLE hsdm.saldos IS 'Tabla unificada de existencias y costos por sección';


--
-- Name: secciones; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.secciones (
    id integer NOT NULL,
    hospital_id integer,
    descripcion character varying(200) NOT NULL,
    seleccionada boolean DEFAULT false,
    estado boolean DEFAULT true
);


ALTER TABLE hsdm.secciones OWNER TO postgres;

--
-- Name: secciones_id_seq; Type: SEQUENCE; Schema: hsdm; Owner: postgres
--

CREATE SEQUENCE hsdm.secciones_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE hsdm.secciones_id_seq OWNER TO postgres;

--
-- Name: secciones_id_seq; Type: SEQUENCE OWNED BY; Schema: hsdm; Owner: postgres
--

ALTER SEQUENCE hsdm.secciones_id_seq OWNED BY hsdm.secciones.id;


--
-- Name: servicios; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.servicios (
    id integer NOT NULL,
    hospital_id integer,
    nombre_servicio character varying(200) NOT NULL,
    cedula_firmante character varying(20) DEFAULT 'NO ASIGNADO'::character varying,
    seccion_id integer
);


ALTER TABLE hsdm.servicios OWNER TO postgres;

--
-- Name: servicios_id_seq; Type: SEQUENCE; Schema: hsdm; Owner: postgres
--

CREATE SEQUENCE hsdm.servicios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE hsdm.servicios_id_seq OWNER TO postgres;

--
-- Name: servicios_id_seq; Type: SEQUENCE OWNED BY; Schema: hsdm; Owner: postgres
--

ALTER SEQUENCE hsdm.servicios_id_seq OWNED BY hsdm.servicios.id;


--
-- Name: subgrupos; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.subgrupos (
    grupo_codigo character varying(2) NOT NULL,
    codigo character varying(4) NOT NULL,
    descripcion character varying(200) NOT NULL
);


ALTER TABLE hsdm.subgrupos OWNER TO postgres;

--
-- Name: unidades; Type: TABLE; Schema: hsdm; Owner: postgres
--

CREATE TABLE hsdm.unidades (
    id integer NOT NULL,
    nombre character varying(50) NOT NULL,
    abreviatura character varying(10) NOT NULL
);


ALTER TABLE hsdm.unidades OWNER TO postgres;

--
-- Name: unidades_id_seq; Type: SEQUENCE; Schema: hsdm; Owner: postgres
--

CREATE SEQUENCE hsdm.unidades_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE hsdm.unidades_id_seq OWNER TO postgres;

--
-- Name: unidades_id_seq; Type: SEQUENCE OWNED BY; Schema: hsdm; Owner: postgres
--

ALTER SEQUENCE hsdm.unidades_id_seq OWNED BY hsdm.unidades.id;


--
-- Name: almacenes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.almacenes (
    codigo_almacen character varying(20) NOT NULL,
    denominacion character varying(100) NOT NULL,
    ubicacion character varying(100) NOT NULL,
    seccion integer NOT NULL,
    tipo integer NOT NULL,
    alias character varying(10) NOT NULL,
    fecha_creacion date NOT NULL,
    principal integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.almacenes OWNER TO postgres;

--
-- Name: articulos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.articulos (
    codigo integer NOT NULL,
    nombre character varying(200) NOT NULL,
    fecha_creacion date NOT NULL,
    unidad_medida integer,
    id_grupo integer,
    id_subgrupo character varying(10) NOT NULL
);


ALTER TABLE public.articulos OWNER TO postgres;

--
-- Name: articulos_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.articulos_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.articulos_codigo_seq OWNER TO postgres;

--
-- Name: articulos_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.articulos_codigo_seq OWNED BY public.articulos.codigo;


--
-- Name: cargos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cargos (
    codigo integer NOT NULL,
    descripcion character varying(50) NOT NULL,
    cedula_firmante character varying(20),
    seccion integer NOT NULL
);


ALTER TABLE public.cargos OWNER TO postgres;

--
-- Name: cargos_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cargos_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cargos_codigo_seq OWNER TO postgres;

--
-- Name: cargos_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cargos_codigo_seq OWNED BY public.cargos.codigo;


--
-- Name: conceptos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conceptos (
    codigo integer NOT NULL,
    descripcion character varying(60) NOT NULL,
    tipo character varying(1) NOT NULL
);


ALTER TABLE public.conceptos OWNER TO postgres;

--
-- Name: costos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.costos (
    clave integer NOT NULL,
    codigo_articulo integer NOT NULL,
    numero_documento character varying(30) NOT NULL,
    estado integer NOT NULL,
    costo double precision NOT NULL,
    fecha_precio date,
    seccion integer
);


ALTER TABLE public.costos OWNER TO postgres;

--
-- Name: costos_clave_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.costos_clave_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.costos_clave_seq OWNER TO postgres;

--
-- Name: costos_clave_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.costos_clave_seq OWNED BY public.costos.clave;


--
-- Name: datosreportes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.datosreportes (
    id integer NOT NULL,
    seccion integer NOT NULL,
    mesinicio integer NOT NULL,
    mesfin integer NOT NULL,
    anio integer NOT NULL,
    concepto integer NOT NULL
);


ALTER TABLE public.datosreportes OWNER TO postgres;

--
-- Name: datosreportes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.datosreportes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.datosreportes_id_seq OWNER TO postgres;

--
-- Name: datosreportes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.datosreportes_id_seq OWNED BY public.datosreportes.id;


--
-- Name: decimales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.decimales (
    cod_seccion integer NOT NULL,
    campo integer NOT NULL,
    total integer NOT NULL
);


ALTER TABLE public.decimales OWNER TO postgres;

--
-- Name: decimales_cod_seccion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.decimales_cod_seccion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.decimales_cod_seccion_seq OWNER TO postgres;

--
-- Name: decimales_cod_seccion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.decimales_cod_seccion_seq OWNED BY public.decimales.cod_seccion;


--
-- Name: doc_entradas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doc_entradas (
    id integer NOT NULL,
    fecha_documento date NOT NULL,
    fecha_operacion date NOT NULL,
    numero_doc character varying(30),
    seccion integer NOT NULL,
    num_articulos integer NOT NULL,
    concepto_entrada integer NOT NULL,
    consecutivo integer NOT NULL,
    cod_proveedor character varying(15),
    valor_operacion double precision DEFAULT 0.0,
    observaciones character varying(300) DEFAULT 'N/A'::character varying NOT NULL,
    codigo_almacen_despacho character varying(50) DEFAULT 'N/A'::character varying NOT NULL,
    codigo_almacen_destino character varying(50) DEFAULT 'N/A'::character varying NOT NULL
);


ALTER TABLE public.doc_entradas OWNER TO postgres;

--
-- Name: doc_entradas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.doc_entradas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.doc_entradas_id_seq OWNER TO postgres;

--
-- Name: doc_entradas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.doc_entradas_id_seq OWNED BY public.doc_entradas.id;


--
-- Name: doc_salidas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doc_salidas (
    id integer NOT NULL,
    fecha_documento date NOT NULL,
    servicio integer NOT NULL,
    num_articulos double precision NOT NULL,
    concepto_salidas integer NOT NULL,
    secciones integer NOT NULL,
    consecutivo integer NOT NULL,
    valor_operacion double precision DEFAULT 0.0,
    asentado integer NOT NULL,
    fecha_pedido date,
    observaciones character varying(300),
    codigo_almacen_despacho character varying(50),
    codigo_almacen_destino character varying(50)
);


ALTER TABLE public.doc_salidas OWNER TO postgres;

--
-- Name: doc_salidas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.doc_salidas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.doc_salidas_id_seq OWNER TO postgres;

--
-- Name: doc_salidas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.doc_salidas_id_seq OWNED BY public.doc_salidas.id;


--
-- Name: empresas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empresas (
    cod_empresas integer NOT NULL,
    descripcion character varying(300),
    firma1 character varying(12),
    firma2 character varying(12),
    firma3 character varying(12),
    firma4 character varying(12),
    seleccionado integer NOT NULL
);


ALTER TABLE public.empresas OWNER TO postgres;

--
-- Name: empresas_cod_empresas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empresas_cod_empresas_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.empresas_cod_empresas_seq OWNER TO postgres;

--
-- Name: empresas_cod_empresas_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empresas_cod_empresas_seq OWNED BY public.empresas.cod_empresas;


--
-- Name: existencias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.existencias (
    id integer NOT NULL,
    codigo integer NOT NULL,
    existencias double precision NOT NULL,
    fecha date NOT NULL,
    numero_dco character varying(30),
    seccion integer NOT NULL
);


ALTER TABLE public.existencias OWNER TO postgres;

--
-- Name: existencias_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.existencias_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.existencias_id_seq OWNER TO postgres;

--
-- Name: existencias_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.existencias_id_seq OWNED BY public.existencias.id;


--
-- Name: firmas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.firmas (
    cedula character varying(20) NOT NULL,
    nombre character varying(100),
    apellido character varying(100),
    seccion integer NOT NULL
);


ALTER TABLE public.firmas OWNER TO postgres;

--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- Name: grupos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grupos (
    codigo_sub character varying(4) NOT NULL,
    descripcion character varying(100) NOT NULL,
    codigo_grupo integer NOT NULL
);


ALTER TABLE public.grupos OWNER TO postgres;

--
-- Name: historiales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historiales (
    id integer NOT NULL,
    fecha date NOT NULL,
    cod_articulo integer NOT NULL,
    valor_entrada double precision NOT NULL,
    valor_salida double precision NOT NULL,
    seccion integer NOT NULL,
    precio double precision NOT NULL,
    numero_doc character varying(30),
    valor_pedido double precision,
    concepto_entrada integer,
    concepto_salida integer
);


ALTER TABLE public.historiales OWNER TO postgres;

--
-- Name: historiales_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.historiales_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.historiales_id_seq OWNER TO postgres;

--
-- Name: historiales_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.historiales_id_seq OWNED BY public.historiales.id;


--
-- Name: inicios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inicios (
    consecutivo integer NOT NULL,
    estado integer NOT NULL
);


ALTER TABLE public.inicios OWNER TO postgres;

--
-- Name: inicios_consecutivo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inicios_consecutivo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.inicios_consecutivo_seq OWNER TO postgres;

--
-- Name: inicios_consecutivo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inicios_consecutivo_seq OWNED BY public.inicios.consecutivo;


--
-- Name: movimientos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movimientos (
    id integer NOT NULL,
    id_documento integer NOT NULL,
    tipo integer NOT NULL,
    cod_articulo integer NOT NULL,
    cantidad integer NOT NULL
);


ALTER TABLE public.movimientos OWNER TO postgres;

--
-- Name: movimientos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movimientos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.movimientos_id_seq OWNER TO postgres;

--
-- Name: movimientos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movimientos_id_seq OWNED BY public.movimientos.id;


--
-- Name: proveedores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.proveedores (
    nombre character varying(300) NOT NULL,
    direccion character varying(500) NOT NULL,
    telefono character varying(24),
    rif_proveedor character varying(30) NOT NULL
);


ALTER TABLE public.proveedores OWNER TO postgres;

--
-- Name: servicios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.servicios (
    cod_servicio integer NOT NULL,
    nombre_servicio character varying(200) NOT NULL,
    cedula_firmante character varying(20),
    seccion integer NOT NULL
);


ALTER TABLE public.servicios OWNER TO postgres;

--
-- Name: servicios_cod_servicio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.servicios_cod_servicio_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.servicios_cod_servicio_seq OWNER TO postgres;

--
-- Name: servicios_cod_servicio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.servicios_cod_servicio_seq OWNED BY public.servicios.cod_servicio;


--
-- Name: temporal_articulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.temporal_articulo (
    id integer NOT NULL,
    cod_articulos integer NOT NULL,
    costo_articulos double precision NOT NULL,
    seccion integer NOT NULL,
    documento character varying(30) NOT NULL,
    cantidad_entrada double precision NOT NULL,
    cantidad_salida double precision NOT NULL,
    cantidad_pedido double precision
);


ALTER TABLE public.temporal_articulo OWNER TO postgres;

--
-- Name: temporal_articulo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.temporal_articulo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.temporal_articulo_id_seq OWNER TO postgres;

--
-- Name: temporal_articulo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.temporal_articulo_id_seq OWNED BY public.temporal_articulo.id;


--
-- Name: temporal_doc_entrada; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.temporal_doc_entrada (
    id integer NOT NULL,
    fecha_documentos date NOT NULL,
    fecha_operaciones date NOT NULL,
    seccion integer NOT NULL,
    proveedores character varying(30) NOT NULL,
    conceptos integer NOT NULL,
    cantidad_articulos double precision NOT NULL,
    valor_operacion double precision NOT NULL,
    doc_entrada character varying(30) NOT NULL,
    consecutivo integer NOT NULL,
    observaciones character varying(300),
    codigo_almacen_despacho character varying(10),
    codigo_almacen_destino character varying(10)
);


ALTER TABLE public.temporal_doc_entrada OWNER TO postgres;

--
-- Name: temporal_doc_entrada_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.temporal_doc_entrada_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.temporal_doc_entrada_id_seq OWNER TO postgres;

--
-- Name: temporal_doc_entrada_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.temporal_doc_entrada_id_seq OWNED BY public.temporal_doc_entrada.id;


--
-- Name: temporal_doc_salida; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.temporal_doc_salida (
    id integer NOT NULL,
    numero_documentos integer NOT NULL,
    fecha_documento date NOT NULL,
    fecha_operacion date NOT NULL,
    seccion integer NOT NULL,
    servicios integer NOT NULL,
    conceptos integer NOT NULL,
    cantidad_articulos double precision NOT NULL,
    valor_operacion double precision NOT NULL,
    consecutivo integer NOT NULL,
    observaciones character varying(300),
    codigo_almacen_despacho character varying(10),
    codigo_almacen_destino character varying(10),
    fecha_pedido date
);


ALTER TABLE public.temporal_doc_salida OWNER TO postgres;

--
-- Name: temporal_doc_salida_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.temporal_doc_salida_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.temporal_doc_salida_id_seq OWNER TO postgres;

--
-- Name: temporal_doc_salida_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.temporal_doc_salida_id_seq OWNED BY public.temporal_doc_salida.id;


--
-- Name: unidades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.unidades (
    cod_unidad integer NOT NULL,
    nombre character varying(20) NOT NULL,
    abreviatura character varying(5) NOT NULL
);


ALTER TABLE public.unidades OWNER TO postgres;

--
-- Name: unidades_cod_unidad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.unidades_cod_unidad_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.unidades_cod_unidad_seq OWNER TO postgres;

--
-- Name: unidades_cod_unidad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.unidades_cod_unidad_seq OWNED BY public.unidades.cod_unidad;


--
-- Name: articulos id; Type: DEFAULT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.articulos ALTER COLUMN id SET DEFAULT nextval('hsdm.articulos_id_seq'::regclass);


--
-- Name: cargos id; Type: DEFAULT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.cargos ALTER COLUMN id SET DEFAULT nextval('hsdm.cargos_id_seq'::regclass);


--
-- Name: documentos id; Type: DEFAULT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.documentos ALTER COLUMN id SET DEFAULT nextval('hsdm.documentos_id_seq'::regclass);


--
-- Name: hospitales id; Type: DEFAULT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.hospitales ALTER COLUMN id SET DEFAULT nextval('hsdm.hospitales_id_seq'::regclass);


--
-- Name: inicios id; Type: DEFAULT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.inicios ALTER COLUMN id SET DEFAULT nextval('hsdm.inicios_id_seq'::regclass);


--
-- Name: kardex id; Type: DEFAULT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.kardex ALTER COLUMN id SET DEFAULT nextval('hsdm.kardex_id_seq'::regclass);


--
-- Name: secciones id; Type: DEFAULT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.secciones ALTER COLUMN id SET DEFAULT nextval('hsdm.secciones_id_seq'::regclass);


--
-- Name: servicios id; Type: DEFAULT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.servicios ALTER COLUMN id SET DEFAULT nextval('hsdm.servicios_id_seq'::regclass);


--
-- Name: unidades id; Type: DEFAULT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.unidades ALTER COLUMN id SET DEFAULT nextval('hsdm.unidades_id_seq'::regclass);


--
-- Name: articulos codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articulos ALTER COLUMN codigo SET DEFAULT nextval('public.articulos_codigo_seq'::regclass);


--
-- Name: cargos codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargos ALTER COLUMN codigo SET DEFAULT nextval('public.cargos_codigo_seq'::regclass);


--
-- Name: costos clave; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.costos ALTER COLUMN clave SET DEFAULT nextval('public.costos_clave_seq'::regclass);


--
-- Name: datosreportes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.datosreportes ALTER COLUMN id SET DEFAULT nextval('public.datosreportes_id_seq'::regclass);


--
-- Name: decimales cod_seccion; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.decimales ALTER COLUMN cod_seccion SET DEFAULT nextval('public.decimales_cod_seccion_seq'::regclass);


--
-- Name: doc_entradas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc_entradas ALTER COLUMN id SET DEFAULT nextval('public.doc_entradas_id_seq'::regclass);


--
-- Name: doc_salidas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc_salidas ALTER COLUMN id SET DEFAULT nextval('public.doc_salidas_id_seq'::regclass);


--
-- Name: empresas cod_empresas; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresas ALTER COLUMN cod_empresas SET DEFAULT nextval('public.empresas_cod_empresas_seq'::regclass);


--
-- Name: existencias id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.existencias ALTER COLUMN id SET DEFAULT nextval('public.existencias_id_seq'::regclass);


--
-- Name: historiales id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historiales ALTER COLUMN id SET DEFAULT nextval('public.historiales_id_seq'::regclass);


--
-- Name: inicios consecutivo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inicios ALTER COLUMN consecutivo SET DEFAULT nextval('public.inicios_consecutivo_seq'::regclass);


--
-- Name: movimientos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimientos ALTER COLUMN id SET DEFAULT nextval('public.movimientos_id_seq'::regclass);


--
-- Name: servicios cod_servicio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicios ALTER COLUMN cod_servicio SET DEFAULT nextval('public.servicios_cod_servicio_seq'::regclass);


--
-- Name: temporal_articulo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.temporal_articulo ALTER COLUMN id SET DEFAULT nextval('public.temporal_articulo_id_seq'::regclass);


--
-- Name: temporal_doc_entrada id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.temporal_doc_entrada ALTER COLUMN id SET DEFAULT nextval('public.temporal_doc_entrada_id_seq'::regclass);


--
-- Name: temporal_doc_salida id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.temporal_doc_salida ALTER COLUMN id SET DEFAULT nextval('public.temporal_doc_salida_id_seq'::regclass);


--
-- Name: unidades cod_unidad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.unidades ALTER COLUMN cod_unidad SET DEFAULT nextval('public.unidades_cod_unidad_seq'::regclass);


--
-- Data for Name: almacenes; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.almacenes (codigo_almacen, hospital_id, denominacion, ubicacion, seccion_id, es_principal, alias) FROM stdin;
\.


--
-- Data for Name: articulos; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.articulos (id, codigo_barra, nombre, unidad_id, grupo_cod, subgrupo_cod, fecha_creacion) FROM stdin;
\.


--
-- Data for Name: cargos; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.cargos (id, hospital_id, descripcion, cedula_firmante, seccion_id) FROM stdin;
\.


--
-- Data for Name: conceptos; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.conceptos (codigo, descripcion, tipo) FROM stdin;
\.


--
-- Data for Name: documentos; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.documentos (id, hospital_id, seccion_id, tipo, numero_provisional, correlativo_legal, mes_legal, anio_legal, fecha_emision, fecha_registro, estado, observaciones, valor_total) FROM stdin;
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	Esquema Maestro Inventario	SQL	V1__Esquema_Maestro_Inventario.sql	-1316697988	postgres	2026-02-24 10:18:43.824809	88	t
2	2	Tablas Operativas y Control	SQL	V2__Tablas_Operativas_y_Control.sql	1192497805	postgres	2026-02-24 10:30:10.269074	86	t
\.


--
-- Data for Name: grupos; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.grupos (codigo, descripcion) FROM stdin;
\.


--
-- Data for Name: hospitales; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.hospitales (id, rif, nombre, direccion) FROM stdin;
\.


--
-- Data for Name: inicios; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.inicios (id, estado, fecha_ultimo_acceso) FROM stdin;
1	0	2026-02-24 10:30:10.300421
\.


--
-- Data for Name: kardex; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.kardex (id, documento_id, articulo_id, seccion_id, cantidad, costo_unitario, saldo_cantidad_post, saldo_costo_prom_post) FROM stdin;
\.


--
-- Data for Name: proveedores; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.proveedores (rif, nombre, direccion, telefono) FROM stdin;
\.


--
-- Data for Name: saldos; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.saldos (articulo_id, seccion_id, hospital_id, stock_actual, costo_promedio) FROM stdin;
\.


--
-- Data for Name: secciones; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.secciones (id, hospital_id, descripcion, seleccionada, estado) FROM stdin;
\.


--
-- Data for Name: servicios; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.servicios (id, hospital_id, nombre_servicio, cedula_firmante, seccion_id) FROM stdin;
\.


--
-- Data for Name: subgrupos; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.subgrupos (grupo_codigo, codigo, descripcion) FROM stdin;
\.


--
-- Data for Name: unidades; Type: TABLE DATA; Schema: hsdm; Owner: postgres
--

COPY hsdm.unidades (id, nombre, abreviatura) FROM stdin;
\.


--
-- Data for Name: almacenes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.almacenes (codigo_almacen, denominacion, ubicacion, seccion, tipo, alias, fecha_creacion, principal) FROM stdin;
306-59B	HOSPITAL DR SAMUEL DARIO MALDONADO	SAN ANTONIO DEL TACHIRA	2	1	PRINCIPAL	2025-08-12	1
\.


--
-- Data for Name: articulos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.articulos (codigo, nombre, fecha_creacion, unidad_medida, id_grupo, id_subgrupo) FROM stdin;
1	ARTICULO1	2024-10-07	1	4	22-A
2	ARTICULO02	2024-10-12	1	4	22-A
3	ARTICULO03	2024-10-12	1	4	22-A
4	ARTICULO04	2024-10-12	1	4	22-A
5	ARTICULO05	2024-10-12	1	4	22-A
6	ARTICULO09	2024-10-12	1	3	01
7	ARTICULO10	2024-10-12	1	3	01
8	ARTICULO11	2024-10-12	1	3	01
9	ARTICULO12	2024-10-12	1	3	01
10	ARTICULO13	2024-10-12	1	3	01
11	ARTICULOA	2024-10-12	1	3	03-1
12	ARTICULOB	2024-10-12	1	3	03-1
13	ARTICULOC	2024-10-12	1	3	03-1
14	ARTICULOD	2024-10-12	1	3	03-1
15	ARTICULO50	2024-10-12	1	3	07
16	ARTICULO51	2024-10-12	1	3	07
17	ARTICULO52	2024-10-12	1	3	07
18	ARTICULO53	2024-10-12	1	3	07
19	ARTICULO54	2024-10-12	1	3	07
20	ARTICULO55	2024-10-12	1	3	07
21	ARTICULO56	2024-10-12	1	3	07
22	ARTICULO57	2024-10-12	1	3	07
23	ARTICULO58	2024-10-12	1	3	07
24	ARTICULO59	2024-10-12	1	3	07
25	ARTICULO60	2024-10-12	1	3	07
26	ARTICULO61	2024-10-12	1	3	07
27	ARTICULO62	2024-10-12	1	3	07
28	ARTICULO63	2024-10-12	1	3	07
29	ARTICULO64	2024-10-12	1	3	07
30	ARTICULO65	2024-10-12	1	3	07
31	ARTICULO66	2024-10-12	1	3	07
32	ARTICULO67	2024-10-12	1	3	07
33	ARTICULO68	2024-10-12	1	3	07
34	ARTICULO69	2024-10-12	1	3	07
35	ARTICULO70	2024-10-12	1	3	07
36	ARTICULO70	2024-10-12	1	3	07
37	ARTICULO71	2024-10-12	1	3	07
38	ARTICULO72	2024-10-12	1	3	07
39	ARTICULO73	2024-10-12	1	3	07
40	ARTICULO74	2024-10-12	1	3	07
41	ARTICULO75	2024-10-12	1	3	07
42	ARTICULO76	2024-10-12	1	3	07
43	ARTICULO77	2024-10-12	1	3	07
44	ARTICULO78	2024-10-12	1	3	07
45	ARTICULO79	2024-10-12	1	3	07
46	ARTICULO80	2024-10-12	1	3	07
47	ARTICULO81	2024-10-12	1	3	07
48	ARTICULO82	2024-10-12	1	3	07
49	ARTICULO83	2024-10-12	1	3	07
50	ARTICULO84	2024-10-12	1	3	07
51	ARTICULO85	2024-10-12	1	3	07
52	ARTICULO86	2024-10-12	1	3	07
53	ARTICULO87	2024-10-12	1	3	07
54	ARTICULO88	2024-10-12	1	3	07
55	ARTICULO89	2024-10-12	1	3	07
56	ARTICULO90	2024-10-12	1	3	07
57	ARTICULO91	2024-10-12	1	3	07
58	ARTICULO92	2024-10-12	1	3	07
59	ARTICULO93	2024-10-12	1	3	07
60	ARTICULO94	2024-10-12	1	3	07
61	ARTICULO95	2024-10-12	1	3	07
62	ARTICULO96	2024-10-12	1	3	07
63	ARTICULO97	2024-10-12	1	3	07
64	ARTICULO98	2024-10-12	1	3	07
65	ARTICULO99	2024-10-12	1	3	07
66	ARTICULO100	2024-10-12	1	3	07
67	ARTICULO101	2024-10-12	1	3	07
68	ARTICULO102	2024-10-12	1	3	07
69	ARTICULO103	2024-10-12	1	3	07
70	ARTICULO104	2024-10-12	1	3	07
71	ARTICULO105	2024-10-12	1	3	07
72	ARTICULO106	2024-10-12	1	3	07
73	ARTICULO107	2024-10-12	1	3	07
74	ARTICULO108	2024-10-12	1	3	07
75	ARTICULO109	2024-10-12	1	3	07
76	ARTICULO110	2024-10-12	1	3	07
77	ARTICULO111	2024-10-12	1	3	07
78	ARTICULO112	2024-10-12	1	3	07
79	ARTICULO113	2024-10-12	1	3	07
80	ARTICULO114	2024-10-12	1	3	07
81	ARTICULO115	2024-10-12	1	3	07
82	ARTICULO116	2024-10-12	1	3	07
83	ARTICULO117	2024-10-12	1	3	07
84	ARTICULO118	2024-10-12	1	3	07
85	ARTICULO119	2024-10-12	1	3	07
86	ARTICULO120	2024-10-12	1	3	07
87	DROGA01	2024-10-12	1	4	22-E
88	DROGA02	2024-10-12	1	4	22-E
89	DROGA03	2024-10-12	1	4	22-E
90	DROGA04	2024-10-12	1	4	22-E
91	DROGA05	2024-10-12	1	4	22-E
92	DROGA06	2024-10-12	1	4	22-E
93	DROGA05	2024-10-12	1	4	22-E
94	DROGA06	2024-10-12	1	4	22-E
95	DROGA07	2024-10-12	1	4	22-E
96	DROGA08	2024-10-12	1	4	22-E
97	DROGA09	2024-10-12	1	4	22-E
98	DROGA10	2024-10-12	1	4	22-E
99	ARTICULOZ001	2024-10-12	1	3	03-1
100	ARTICULOZ002	2024-10-12	1	3	03-1
101	ARTICULOZ003	2024-10-12	1	3	03-1
102	ARTICULOZ004	2024-10-12	1	3	03-1
103	ARTICULOZ005	2024-10-12	1	3	03-1
104	ARTICULOZ006	2024-10-12	1	3	03-1
105	ARTICULOZ007	2024-10-12	1	3	03-1
106	ARTICULOZ008	2024-10-12	1	3	03-1
107	ARTICULOZ009	2024-10-12	1	3	03-1
108	ARTICULOZ010	2024-10-12	1	3	03-1
109	ARTICULOZ011	2024-10-12	1	3	03-1
110	ARTICULOZ012	2024-10-12	1	3	03-1
111	ARTICULOZ013	2024-10-12	1	3	03-1
112	ARTICULOZ014	2024-10-12	1	3	03-1
113	ARTICULOZ015	2024-10-12	1	3	03-1
114	ARTICULOZ016	2024-10-12	1	3	03-1
115	ARTICULOZ017	2024-10-12	1	3	03-1
116	ARTICULOZ018	2024-10-12	1	3	03-1
117	ARTICULOZ019	2024-10-12	1	3	03-1
118	ARTICULOZ020	2024-10-12	1	3	03-1
119	ARTICULOZ021	2024-10-12	1	3	03-1
120	ARTICULOZ022	2024-10-12	1	3	03-1
121	TECNO01	2024-10-12	1	3	05
122	TECNO02	2024-10-12	1	3	05
123	TECNO03	2024-10-12	1	3	05
124	TECNO04	2024-10-12	1	3	05
125	TECNO05	2024-10-12	1	3	05
126	TECNO06	2024-10-12	1	3	05
127	TECNO07	2024-10-12	1	3	05
128	TECNO08	2024-10-12	1	3	05
129	TECNO09	2024-10-12	1	3	05
130	TECNO10	2024-10-12	1	3	05
131	Z001	2024-10-13	1	3	01
132	Z002	2024-10-13	1	3	01
133	Z003	2024-10-13	1	3	01
134	Z004	2024-10-13	1	3	01
135	Z005	2024-10-13	1	3	01
136	PONDERADO	2024-10-13	1	3	03-1
137	PONDERADO2	2024-10-13	1	3	01
138	PONDERADOS	2024-10-13	1	3	07
139	PND	2024-10-13	1	3	03-5
140	CUADERNO	2024-10-13	1	3	01
141	LAPIZ	2024-10-13	1	3	01
142	T001	2024-10-13	1	3	03-1
143	BORRADOR	2024-10-13	1	3	11
144	VASO	2024-10-13	1	3	01
145	UNO	2024-10-13	1	3	01
146	PEPE	2024-10-13	1	3	01
147	TEC01	2024-10-13	1	3	01
148	LLAMADA	2024-10-13	1	3	01
149	CABLE	2024-10-13	1	3	01
150	SACA	2024-10-13	1	3	01
151	MONITOR	2024-10-13	1	3	01
152	DISCO	2024-10-13	1	3	01
153	CD	2024-10-13	1	3	01
154	cancion	2024-10-13	1	3	01
155	PUERTA	2024-10-13	1	3	01
156	ELNUEVO	2024-10-21	1	3	01
157	NUEVO2	2024-10-21	1	3	01
\.


--
-- Data for Name: cargos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cargos (codigo, descripcion, cedula_firmante, seccion) FROM stdin;
4	KARDEX	123	2
5	SUB DIRECTOR	123	2
1	DIRECTOR	18353858	2
2	ADMINISTRADOR	9132386	2
3	ALMACEN	9132386	2
\.


--
-- Data for Name: conceptos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.conceptos (codigo, descripcion, tipo) FROM stdin;
1	Inventario Inicial	E
2	Incorporacion por traspaso	E
3	Compras	E
4	Construccion de inmuebles	E
5	Adiciones y mejoras	E
6	Produccion de elementos (muebles)	E
7	Suministro de bienes de otras entidades	E
8	Devolucion de bienes prestados a contratistas	E
9	Incorporacion de semovientes	E
10	Reconstruccion de equiposl	E
11	Incorporacion por donacion	E
12	Incorporacion por permuta	E
13	Adscripcion por bienes inmuebles	E
14	Omision en inventario	E
16	Incorporacion por cambio de subgrupo	E
17	Correccion de desincorporaciones	E
18	Incorporacion por otros conceptos	E
19	Incorporacion de muebles procedentes de los almacenes	E
20	Herencias vacantes	E
51	Desincorporacion por traspaso	S
52	Venta	S
53	Prestamos de bienes a contratistas	S
54	Suministro de bienes a otras entidades	S
55	Desarme	S
56	Inservibilidad	S
57	Deterioro	S
58	Demolicion	S
59	Desincorporacion de semovientes	S
60	Faltantes por investigar	S
61	Desincorporacion por permuta	S
62	Desincorporacion por donacion	S
63	Desincorporacion por adscripcion de bienes imuebles	S
65	Desincorporacion por cambio de subgrupo	S
66	Correcciones de incorporaciones	S
67	Desincorporacion por otros conceptos	S
\.


--
-- Data for Name: costos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.costos (clave, codigo_articulo, numero_documento, estado, costo, fecha_precio, seccion) FROM stdin;
38	38	005	1	5	2024-10-15	2
30	30	005	1	6.55	2024-10-15	2
34	34	005	1	10.12	2024-10-15	2
20	20	005	1	5.64	2024-10-15	2
47	47	005	1	5.81	2024-10-15	2
35	35	005	1	6.05	2024-10-15	2
37	37	005	1	34.46	2024-10-15	2
57	57	005	1	6.25	2024-10-15	2
49	49	005	1	2.71	2024-10-15	2
50	50	005	1	3.54	2024-10-15	2
51	51	005	1	3.19	2024-10-15	2
52	52	005	1	2.16	2024-10-15	2
59	59	005	1	2.05	2024-10-15	2
67	67	005	1	4.45	2024-10-15	2
70	70	005	1	11.13	2024-10-15	2
53	53	005	1	4.82	2024-10-15	2
71	71	005	1	5.92	2024-10-15	2
72	72	005	1	5.2	2024-10-15	2
74	74	005	1	2	2024-10-15	2
75	75	005	1	5.21	2024-10-15	2
76	76	005	1	1.19	2024-10-15	2
78	78	005	1	2.25	2024-10-15	2
79	79	005	1	3.86	2024-10-15	2
17	17	005	1	6	2024-10-15	2
21	21	005	1	2	2024-10-15	2
33	33	L002	1	2.59	2024-10-15	2
36	36	005	1	3	2024-10-15	2
22	22	005	1	5.58	2024-10-15	2
39	39	005	1	6	2024-10-15	2
46	46	005	1	5.51	2024-10-15	2
41	41	005	1	5	2024-10-15	2
42	42	005	1	2	2024-10-15	2
43	43	005	1	2	2024-10-15	2
44	44	005	1	2	2024-10-15	2
45	45	005	1	2	2024-10-15	2
11	11	005	1	1.08	2024-10-15	2
80	80	FACT241018	1	2	2024-10-07	2
81	81	FACT241018	1	2	2024-10-07	2
62	62	FACT241018	1	2.5	2024-10-07	2
12	12	005	1	2.07	2024-10-15	2
13	13	005	1	1.72	2024-10-15	2
89	89	005	1	1.21	2024-10-15	2
90	90	005	1	1.57	2024-10-15	2
93	93	005	1	3.5	2024-10-15	2
92	92	005	1	2	2024-10-15	2
99	99	005	1	1	2024-10-15	2
100	100	005	1	2	2024-10-15	2
101	101	005	1	1	2024-10-15	2
32	32	005	1	2.39	2024-10-15	2
105	105	005	1	2	2024-10-15	2
106	106	005	1	1	2024-10-15	2
107	107	005	1	2	2024-10-15	2
95	95	FACT241018	1	2	2024-10-07	2
96	96	FACT241018	1	2	2024-10-07	2
97	97	FACT241018	1	1	2024-10-07	2
98	98	FACT241018	1	2	2024-10-07	2
1	1	001	1	16.21	2024-10-12	2
28	28	002	1	3.2	2024-10-12	2
103	103	N/A	0	0	2024-10-12	2
10	10	FACT241018	1	3.5	2024-10-07	2
91	91	N/A	0	0	2024-10-12	2
94	94	N/A	0	0	2024-10-12	2
9	9	005	1	1	2024-10-15	2
102	102	005	1	1	2024-10-15	2
104	104	005	1	1	2024-10-15	2
14	14	005	1	2.22	2024-10-15	2
15	15	FACT241018	1	2.3	2024-10-07	2
29	29	FACT241018	1	1.4	2024-10-07	2
16	16	FACT241018	1	3.6	2024-10-07	2
18	18	FACT241018	1	3.8	2024-10-07	2
54	54	FACT241018	1	3.65	2024-10-07	2
55	55	FACT241018	1	3	2024-10-07	2
60	60	005	1	3.92	2024-10-15	2
2	2	FACT241018	1	2.3	2024-10-07	2
3	3	FACT241018	1	3.9	2024-10-07	2
4	4	FACT241018	1	1	2024-10-07	2
5	5	FACT241018	1	3.6	2024-10-07	2
19	19	FACT241018	1	3.6	2024-10-07	2
7	7	FACT241018	1	3.6	2024-10-07	2
6	6	FACT241018	1	3.6	2024-10-07	2
117	117	N/A	0	0	2024-10-12	2
118	118	N/A	0	0	2024-10-12	2
66	66	005	1	4.45	2024-10-15	2
48	48	005	1	4	2024-10-15	2
58	58	005	1	2	2024-10-15	2
68	68	005	1	2	2024-10-15	2
69	69	005	1	1	2024-10-15	2
73	73	005	1	1	2024-10-15	2
61	61	005	1	1.91	2024-10-15	2
63	63	005	1	1.28	2024-10-15	2
64	64	005	1	1.21	2024-10-15	2
65	65	005	1	6.62	2024-10-15	2
77	77	005	1	3.91	2024-10-15	2
82	82	005	1	2.68	2024-10-15	2
83	83	005	1	2	2024-10-15	2
84	84	005	1	1	2024-10-15	2
85	85	005	1	1.88	2024-10-15	2
86	86	005	1	3.45	2024-10-15	2
87	87	005	1	4.62	2024-10-15	2
88	88	005	1	1.42	2024-10-15	2
108	108	005	1	1	2024-10-15	2
109	109	005	1	2	2024-10-15	2
110	110	005	1	1	2024-10-15	2
111	111	005	1	1	2024-10-15	2
112	112	005	1	1	2024-10-15	2
113	113	005	1	1	2024-10-15	2
114	114	005	1	2	2024-10-15	2
115	115	005	1	2	2024-10-15	2
116	116	005	1	2	2024-10-15	2
23	23	005	1	5	2024-10-15	2
8	8	005	1	3.6	2024-10-15	2
24	24	005	1	3.27	2024-10-15	2
56	56	005	1	2.23	2024-10-15	2
25	25	005	1	1.35	2024-10-15	2
26	26	005	1	3.68	2024-10-15	2
31	31	005	1	3.58	2024-10-15	2
153	153	CD03	1	3	2024-10-13	2
155	155	PU02	1	2.97	2024-10-13	2
40	40	005	1	10	2024-10-15	2
157	157	NUEVO201	1	2.85	2024-10-21	2
27	27	L002	1	9.83	2024-10-15	2
119	119	N/A	0	0	2024-10-12	2
120	120	N/A	0	0	2024-10-12	2
152	152	DISQ02	1	3.71	2024-10-13	2
154	154	j0023	1	4.6	2024-10-13	2
136	136	PONDERADO2	1	3.2	2024-10-13	2
137	137	POND2	1	3.67	2024-10-13	2
138	138	P002	1	5.33	2024-10-13	2
139	139	PND02	1	4.6	2024-10-13	2
140	140	C002	1	4.33	2024-10-13	2
142	142	T0100	1	2.2	2024-10-13	2
143	143	d004	1	1.75	2024-10-13	2
144	144	V002	1	2	2024-10-13	2
145	145	UNO02	1	2	2024-10-13	2
146	146	PEPE02	1	2	2024-10-13	2
147	147	TEC002	1	3	2024-10-13	2
148	148	P003	1	2	2024-10-13	2
149	149	CABLE02	1	2	2024-10-13	2
150	150	SACA02	1	2	2024-10-13	2
151	151	MONIT69	1	5	2024-10-13	2
121	121	005	1	1	2024-10-15	2
122	122	005	1	2	2024-10-15	2
123	123	005	1	1	2024-10-15	2
124	124	005	1	1	2024-10-15	2
125	125	005	1	1	2024-10-15	2
126	126	005	1	1	2024-10-15	2
127	127	005	1	1	2024-10-15	2
128	128	005	1	2	2024-10-15	2
129	129	005	1	1	2024-10-15	2
130	130	005	1	1	2024-10-15	2
131	131	005	1	0	2024-10-15	2
132	132	005	1	0	2024-10-15	2
133	133	005	1	0	2024-10-15	2
134	134	005	1	0	2024-10-15	2
135	135	005	1	0	2024-10-15	2
156	156	NUEVO01	1	0	2024-10-21	2
141	141	L002	1	2.72	2024-10-15	2
\.


--
-- Data for Name: datosreportes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.datosreportes (id, seccion, mesinicio, mesfin, anio, concepto) FROM stdin;
20	2	1	1	2025	0
\.


--
-- Data for Name: decimales; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.decimales (cod_seccion, campo, total) FROM stdin;
2	6	2
3	4	6
\.


--
-- Data for Name: doc_entradas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doc_entradas (id, fecha_documento, fecha_operacion, numero_doc, seccion, num_articulos, concepto_entrada, consecutivo, cod_proveedor, valor_operacion, observaciones, codigo_almacen_despacho, codigo_almacen_destino) FROM stdin;
18	2024-10-12	2024-10-12	00100	2	1	1	5	J123456789	2	N/A	N/A	N/A
20	2024-10-12	2024-10-12	001	2	1	1	7	J123456789	2	N/A	N/A	N/A
23	2024-10-12	2024-10-12	002	2	1	4	1	J123456789	4	N/A	N/A	N/A
24	2024-10-07	2024-10-12	FACT241018	2	100	1	8	J123456789	5662.37	N/A	N/A	N/A
27	2024-10-12	2024-10-12	003	2	2	1	9	J123456789	11.2	N/A	N/A	N/A
34	2024-10-12	2024-10-12	004	2	1	3	3	J123456789	3.3	N/A	N/A	N/A
38	2024-10-12	2024-10-12	fact22	2	2	3	4	J123456789	16	N/A	N/A	N/A
43	2024-10-13	2024-10-13	PONDERADO1	2	1	3	6	J123456789	6	N/A	N/A	N/A
45	2024-10-13	2024-10-13	PONDERADO2	2	1	3	7	J123456789	10	N/A	N/A	N/A
46	2024-10-13	2024-10-13	PONDERADO3	2	1	3	8	J123456789	10	N/A	N/A	N/A
47	2024-10-13	2024-10-13	POND1	2	1	3	9	J123456789	6	N/A	N/A	N/A
50	2024-10-13	2024-10-13	POND2	2	1	3	10	J123456789	5	N/A	N/A	N/A
51	2024-10-13	2024-10-13	P001	2	1	3	11	J123456789	6	N/A	N/A	N/A
56	2024-10-13	2024-10-13	P002	2	1	3	12	J123456789	10	N/A	N/A	N/A
57	2024-10-13	2024-10-13	PND01	2	1	3	13	J123456789	6	N/A	N/A	N/A
59	2024-10-13	2024-10-13	PND02	2	1	3	14	J123456789	10	N/A	N/A	N/A
60	2024-10-13	2024-10-13	C001	2	1	3	15	J123456789	6	N/A	N/A	N/A
62	2024-10-13	2024-10-13	C002	2	1	3	16	J123456789	10	N/A	N/A	N/A
63	2024-10-13	2024-10-13	L001	2	1	3	17	J123456789	6	N/A	N/A	N/A
65	2024-10-13	2024-10-13	l003	2	1	3	18	J123456789	20	N/A	N/A	N/A
66	2024-10-13	2024-10-13	T010	2	1	3	19	J123456789	6	N/A	N/A	N/A
67	2024-10-13	2024-10-13	T00203	2	1	3	20	J123456789	12	N/A	N/A	N/A
68	2024-10-13	2024-10-13	T23	2	1	3	21	J123456789	18	N/A	N/A	N/A
69	2024-10-13	2024-10-13	T0100	2	1	3	22	J123456789	5	N/A	N/A	N/A
70	2024-10-13	2024-10-13	D001	2	1	3	23	J123456789	6	N/A	N/A	N/A
71	2024-10-13	2024-10-13	D003	2	1	3	24	J123456789	12	N/A	N/A	N/A
72	2024-10-13	2024-10-13	d004	2	1	3	25	J123456789	1	N/A	N/A	N/A
73	2024-10-13	2024-10-13	V001	2	1	3	26	J123456789	6	N/A	N/A	N/A
74	2024-10-13	2024-10-13	V002	2	1	3	27	J123456789	12	N/A	N/A	N/A
75	2024-10-13	2024-10-13	UNO01	2	1	3	28	J123456789	6	N/A	N/A	N/A
76	2024-10-13	2024-10-13	UNO02	2	1	3	29	J123456789	12	N/A	N/A	N/A
77	2024-10-13	2024-10-13	PEPE01	2	1	3	30	J123456789	6	N/A	N/A	N/A
78	2024-10-13	2024-10-13	PEPE02	2	1	3	31	J123456789	12	N/A	N/A	N/A
79	2024-10-13	2024-10-13	TEC001	2	1	3	32	J123456789	6	N/A	N/A	N/A
80	2024-10-13	2024-10-13	TEC002	2	1	3	33	J123456789	12	N/A	N/A	N/A
81	2024-10-13	2024-10-13	P200	2	1	3	34	J123456789	6	N/A	N/A	N/A
82	2024-10-13	2024-10-13	P003	2	1	3	35	J123456789	12	N/A	N/A	N/A
83	2024-10-13	2024-10-13	CABLE01	2	1	3	36	J123456789	6	N/A	N/A	N/A
84	2024-10-13	2024-10-13	CABLE02	2	1	3	37	J123456789	10	N/A	N/A	N/A
85	2024-10-13	2024-10-13	SACA01	2	1	3	38	J123456789	6	N/A	N/A	N/A
86	2024-10-13	2024-10-13	SACA02	2	1	3	39	J123456789	10	N/A	N/A	N/A
87	2024-10-13	2024-10-13	MONITOR01	2	1	1	10	J123456789	10	N/A	N/A	N/A
88	2024-10-13	2024-10-13	MONIT69	2	1	3	40	J123456789	12	N/A	N/A	N/A
89	2024-10-13	2024-10-13	DISQ01	2	1	3	41	J123456789	6	N/A	N/A	N/A
90	2024-10-13	2024-10-13	DISQ02	2	1	3	42	J123456789	20	N/A	N/A	N/A
91	2024-10-13	2024-10-13	CD01	2	1	3	43	J123456789	6	N/A	N/A	N/A
92	2024-10-13	2024-10-13	CD023	2	1	3	44	J123456789	10	N/A	N/A	N/A
93	2024-10-13	2024-10-13	CD03	2	1	3	45	J123456789	2	N/A	N/A	N/A
94	2024-10-13	2024-10-13	J001	2	1	3	46	J123456789	6	N/A	N/A	N/A
96	2024-10-13	2024-10-13	j0023	2	1	3	47	J123456789	10	N/A	N/A	N/A
97	2024-10-13	2024-10-13	PU001	2	1	3	48	J123456789	6	N/A	N/A	N/A
100	2024-10-13	2024-10-13	PUERTA	2	1	3	50	J123456789	1.8	N/A	N/A	N/A
104	2024-10-15	2024-10-15	005	2	106	3	53	J123456789	2403.84	N/A	N/A	N/A
105	2024-10-13	2024-10-15	PU02	2	1	3	54	J123456789	10	N/A	N/A	N/A
110	2024-10-21	2024-10-21	NUEVO01	2	1	3	56	J123456789	7.2	N/A	N/A	N/A
114	2024-10-15	2024-10-21	L002	2	3	3	58	J123456789	13	N/A	N/A	N/A
119	2024-10-21	2024-10-21	NUEVO201	2	1	3	59	J123456789	8.55	N/A	N/A	N/A
\.


--
-- Data for Name: doc_salidas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doc_salidas (id, fecha_documento, servicio, num_articulos, concepto_salidas, secciones, consecutivo, valor_operacion, asentado, fecha_pedido, observaciones, codigo_almacen_despacho, codigo_almacen_destino) FROM stdin;
1	2024-10-15	1	2	51	2	1	16.28	1	\N	\N	\N	\N
2	2024-10-15	1	1	51	2	2	5	1	\N	\N	\N	\N
4	2024-10-15	1	1	51	2	4	39.3	1	\N	\N	\N	\N
3	2024-10-15	1	1	51	2	3	45.85	1	\N	\N	\N	\N
5	2024-10-15	1	2	51	2	5	28.1	1	\N	\N	\N	\N
\.


--
-- Data for Name: empresas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.empresas (cod_empresas, descripcion, firma1, firma2, firma3, firma4, seleccionado) FROM stdin;
1	inicial	\N	\N	\N	\N	0
3	HOSPITAL2026	\N	\N	\N	\N	0
2	HOSPITAL2024	\N	\N	\N	\N	1
\.


--
-- Data for Name: existencias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.existencias (id, codigo, existencias, fecha, numero_dco, seccion) FROM stdin;
38	38	62	2024-10-15	SALIDA	2
30	30	78	2024-10-15	SALIDA	2
34	34	22	2024-10-15	005	2
20	20	31	2024-10-15	SALIDA	2
47	47	30	2024-10-15	005	2
35	35	27	2024-10-15	005	2
37	37	126	2024-10-15	005	2
57	57	110	2024-10-15	005	2
49	49	110	2024-10-15	005	2
50	50	123	2024-10-15	005	2
51	51	60	2024-10-15	005	2
52	52	83	2024-10-15	005	2
59	59	17	2024-10-15	005	2
67	67	33	2024-10-15	005	2
70	70	70	2024-10-15	005	2
53	53	102	2024-10-15	005	2
71	71	99	2024-10-15	005	2
72	72	30	2024-10-15	005	2
74	74	38	2024-10-15	005	2
75	75	19	2024-10-15	005	2
76	76	30	2024-10-15	005	2
78	78	38	2024-10-15	005	2
79	79	70	2024-10-15	005	2
17	17	96	2024-10-15	005	2
21	21	64	2024-10-15	005	2
33	33	125	2024-10-15	L002	2
36	36	64	2024-10-15	005	2
22	22	57	2024-10-15	005	2
39	39	24	2024-10-15	005	2
46	46	115	2024-10-15	005	2
41	41	40	2024-10-15	005	2
42	42	24	2024-10-15	005	2
43	43	32	2024-10-15	005	2
44	44	80	2024-10-15	005	2
45	45	24	2024-10-15	005	2
11	11	64	2024-10-15	005	2
80	80	6	2024-10-07	FACT241018	2
81	81	6	2024-10-07	FACT241018	2
62	62	9	2024-10-07	FACT241018	2
12	12	58	2024-10-15	005	2
13	13	27	2024-10-15	005	2
89	89	19	2024-10-15	005	2
90	90	14	2024-10-15	005	2
93	93	16	2024-10-15	005	2
92	92	10	2024-10-15	005	2
99	99	50	2024-10-15	005	2
100	100	10	2024-10-15	005	2
101	101	15	2024-10-15	005	2
32	32	123	2024-10-15	005	2
105	105	15	2024-10-15	005	2
106	106	10	2024-10-15	005	2
107	107	15	2024-10-15	005	2
95	95	4	2024-10-07	FACT241018	2
96	96	6	2024-10-07	FACT241018	2
97	97	2	2024-10-07	FACT241018	2
98	98	4	2024-10-07	FACT241018	2
1	1	19	2024-10-12	001	2
28	28	8	2024-10-12	002	2
103	103	0	2024-10-12	N/A	2
10	10	10	2024-10-07	FACT241018	2
91	91	0	2024-10-12	N/A	2
94	94	0	2024-10-12	N/A	2
9	9	96	2024-10-15	005	2
102	102	50	2024-10-15	005	2
104	104	25	2024-10-15	005	2
14	14	58	2024-10-15	005	2
15	15	3	2024-10-07	FACT241018	2
29	29	9	2024-10-07	FACT241018	2
16	16	6	2024-10-07	FACT241018	2
18	18	6	2024-10-07	FACT241018	2
54	54	3	2024-10-07	FACT241018	2
55	55	6	2024-10-07	FACT241018	2
60	60	38	2024-10-15	005	2
2	2	25	2024-10-07	FACT241018	2
3	3	15	2024-10-07	FACT241018	2
4	4	5	2024-10-07	FACT241018	2
5	5	25	2024-10-07	FACT241018	2
19	19	6	2024-10-07	FACT241018	2
7	7	10	2024-10-07	FACT241018	2
6	6	10	2024-10-07	FACT241018	2
117	117	0	2024-10-12	N/A	2
118	118	0	2024-10-12	N/A	2
66	66	22	2024-10-15	005	2
48	48	32	2024-10-15	005	2
58	58	24	2024-10-15	005	2
68	68	32	2024-10-15	005	2
69	69	8	2024-10-15	005	2
73	73	48	2024-10-15	005	2
61	61	35	2024-10-15	005	2
63	63	22	2024-10-15	005	2
64	64	54	2024-10-15	005	2
65	65	78	2024-10-15	005	2
77	77	33	2024-10-15	005	2
82	82	19	2024-10-15	005	2
83	83	11	2024-10-15	005	2
84	84	19	2024-10-15	005	2
85	85	27	2024-10-15	005	2
86	86	11	2024-10-15	005	2
87	87	16	2024-10-15	005	2
88	88	25	2024-10-15	005	2
108	108	10	2024-10-15	005	2
109	109	15	2024-10-15	005	2
110	110	15	2024-10-15	005	2
111	111	10	2024-10-15	005	2
112	112	10	2024-10-15	005	2
113	113	10	2024-10-15	005	2
114	114	15	2024-10-15	005	2
115	115	10	2024-10-15	005	2
116	116	10	2024-10-15	005	2
23	23	34	2024-10-15	005	2
8	8	120	2024-10-15	005	2
24	24	28	2024-10-15	005	2
56	56	99	2024-10-15	005	2
25	25	50	2024-10-15	005	2
26	26	102	2024-10-15	005	2
31	31	67	2024-10-15	005	2
153	153	6	2024-10-13	CD03	2
155	155	6	2024-10-13	PU02	2
40	40	32	2024-10-15	005	2
157	157	3	2024-10-21	NUEVO201	2
27	27	106	2024-10-15	L002	2
119	119	0	2024-10-12	N/A	2
120	120	0	2024-10-12	N/A	2
152	152	7	2024-10-13	DISQ02	2
154	154	5	2024-10-13	j0023	2
136	136	5	2024-10-13	PONDERADO2	2
137	137	6	2024-10-13	POND2	2
138	138	3	2024-10-13	P002	2
139	139	5	2024-10-13	PND02	2
140	140	3	2024-10-13	C002	2
142	142	5	2024-10-13	T0100	2
143	143	4	2024-10-13	d004	2
144	144	3	2024-10-13	V002	2
145	145	3	2024-10-13	UNO02	2
146	146	3	2024-10-13	PEPE02	2
147	147	2	2024-10-13	TEC002	2
148	148	3	2024-10-13	P003	2
149	149	3	2024-10-13	CABLE02	2
150	150	3	2024-10-13	SACA02	2
151	151	2	2024-10-13	MONIT69	2
121	121	20	2024-10-15	005	2
122	122	4	2024-10-15	005	2
123	123	6	2024-10-15	005	2
124	124	4	2024-10-15	005	2
125	125	4	2024-10-15	005	2
126	126	4	2024-10-15	005	2
127	127	6	2024-10-15	005	2
128	128	4	2024-10-15	005	2
129	129	4	2024-10-15	005	2
130	130	6	2024-10-15	005	2
131	131	2	2024-10-15	005	2
132	132	3	2024-10-15	005	2
133	133	2	2024-10-15	005	2
134	134	2	2024-10-15	005	2
135	135	3	2024-10-15	005	2
156	156	2	2024-10-21	NUEVO01	2
141	141	14	2024-10-15	L002	2
\.


--
-- Data for Name: firmas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.firmas (cedula, nombre, apellido, seccion) FROM stdin;
18353858	SANDER	CUELLAR	2
18353859	HIRNEY	AGELVIS	2
9132386	JOSE	CUELLAR	2
3062380	JOSE	CUELLAR	2
123	NO ASIGNADO	NO ASIGNADO	2
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	<< Flyway Baseline >>	BASELINE	<< Flyway Baseline >>	\N	postgres	2026-02-21 18:35:33.439966	0	t
\.


--
-- Data for Name: grupos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.grupos (codigo_sub, descripcion, codigo_grupo) FROM stdin;
01	MAQUINAS MUEBLES Y DEMAS EQUIPOS DE OFICINA	3
02	MOBILIARIO Y ENSERES DE ALOJAMIENTO	3
03	MAQUINARIA Y DEMAS EQUIPOS DE CONSTRUCCION CAMPO INDUSTRIA Y TALLER	3
03-1	Equipo de taller y uso general	3
03-2	Maquinaria y equipo de construccion y conservacion	3
03-3	Maquinaria y equipo para mantenimiento de automotores	3
03-4	Maquinaria y equipo agricola y pecuario	3
03-5	Maquinaria y equipo de artes graficas	3
03-6	Maquinaria industrial	3
04	EQUIPO DE TRANSPORTE	3
04-1	Vehiculos automotores terrestres	3
04-2	Otros vehiculos terrestres	3
05	EQUIPOS DE TELECOMUNICACIONES	3
06	EQUIPOS MEDICO-QUIRURGICOS DENTALES Y VETERINARIOS	3
06-1	Equipos medicos-quirurgicos  y de veterinaria	3
06-2	Equipos dentales	3
07	EQUIPOS CIENTIFICOS Y DE ENSENANZA	3
07-1	Equipos cientificos y de laboratorio	3
07-2	Equipos de ense;anza deporte y recreacion	3
07-3	Elementos de culto	3
08	COLECCIONES CULTURALES ARTISTICAS E HISTORICAS	3
08-1	Libros	3
08-2	Colecciones cientificas	3
08-3	Colecciones artisticas y ornamentales	3
09	ARMAMENTO Y EQUIPO DE DEFENSA	3
11	OTROS BIENES MUEBLES EN DEPOSITO	3
20	ALIMENTOS Y BEBIDAS	4
21	MATERIALES AGRICOLAS Y PECUARIOS	4
21-A	ABONOS	4
21-B	ALIMENTOS PARA ANIMALES	4
21-C	INSECTICIDAS	4
21-D	SEMILLAS	4
22	DROGAS MEDICINAS MATERIALES ODONTOLOGICOS, DE LABORATORIO DE SANIDAD Y SIMILARES	4
22-A	DROGAS MEDICINAS Y ELEMNTOS DE CURACION PARA PACIENTES	4
22-B	MATERIALES DE ODONTOLOGIA	4
22-C	MATERIALES PARA LABORATORIO	4
22-D	SUMINISTROS MENORES ORTOPEDICOS	4
22-E	DROGAS MED. Y ELEMENTOS DE C.(PTES)	4
22-F	DROGAS MED. Y ELEMENTOS DE C.(OBRE.)	4
22-G	SUSTANCIAS PARA LABORATORIO	4
22-H	MATERIALES PARA RAYOS X	4
22-I	SUMINISTROS MENORES OPTICOS	4
22-J	MATERIALES MEDICO QUIRURGICO	4
4-23	MATERIALES DE CONSTRUCCION	4
23-A	MATERIALES BASICOS Y ESTRUCTURALES	4
23-B	MATERIALES Y UTILES PARA INSTITUCIONES SANITARIAS	4
23-C	MATERIALES PARA INSTALACIONES ELECTRICAS Y DE TELECOMUNICACIONES	4
23-D	MATERIALES DE PINTURA Y DECORACION	4
4-24	MATERIALES PARA INDUSTRIA Y TALLER	4
24-A	MATERIALES DE IMP Y ARTES GRAFICAS	4
24-D	MATERIALES PARA LA CONFECCION	4
24-E	MATERIALES DE TRANSFORMACION	4
24-F	ENVASES EMP. Y MATERIALES DE EMBALAJE	4
4-25	REPUESTOS ACCESORIOS Y HERRAMIENTAS MENORES	4
25-C	REPUESTOS ACCESORIOS PARA MAQUINA INDUSTRIAL	4
25-D	REPUESTOS ACCESORIOS EQUIPOS DE TRNSPORTE	4
25-E	REPUESTOS ACCESORIOS EQUIPOS DE OFICINA	4
25-F	REPUESTOS ACCESORIOS EQUIPOS DE ALOJAMIENTO	4
25-G	REPUESTOS ACCESORIOS PARA TELECOMUNICACIONES	4
25-H	HERRAMIENTAS MENORES	4
26-A	LIBROS REVISTAS Y PERIODICOS	4
26-B	MATERIAL DE INSTRUCCION	4
26-C	UTILES DEPORTIVOS	4
26-D	MATERIALES PARA FINES ART Y RECRE.	4
26-E	MAT. PARA EL CULTO RELIGIOSO	4
26-F	MAT PARA LAB FOTOGRAFICO EDUCACIONAL	4
27	UTILES DE ESCRITORIO Y OFICINA	4
28	MATERIALES DE USO PERSONAL DE ALOJAMIENTO Y DE LIMPIEZA	4
28-A	ARTICULOS DE SEGURIDAD EN EL TRABAJO	4
28-C	VESTUARIO PARA EMPLEADOS	4
28-D	ELEMENTOS DE ASEO PERSONAL	4
28-E	MENAJE Y VAJILLA DE COCINA IND	4
28-F	ENSERES DE DORMITORIO	4
28-G	ENSERES PARA OTRAS DEPENDENDCIAS	4
28-H	UTILES Y MATERIALES DE ASEO	4
28-I	UTILES Y MATERIALES DE LAVANDERIA	4
28-J	VESTUARIO Y ZAPATOS PARA OBREROS	4
28-K	VESTUARIO PARA PACIENTES	4
29	MATERIALES DE DEFENSA Y SEFURIDAD PUBLICA	4
30	OTROS MATERIALES DE CONSUMO	4
30-A	COMBUSTIBLE	4
30-B	ACEITES Y GRASAS LUBRICANTES	4
30-D	GAS COMBUSTIBLE	4
\.


--
-- Data for Name: historiales; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.historiales (id, fecha, cod_articulo, valor_entrada, valor_salida, seccion, precio, numero_doc, valor_pedido, concepto_entrada, concepto_salida) FROM stdin;
268	2024-10-12	1	1	0	2	2	001	\N	\N	\N
270	2024-10-12	1	1	0	2	20	FACT241018	\N	\N	\N
272	2024-10-12	3	3	0	2	3.9	FACT241018	\N	\N	\N
274	2024-10-12	5	5	0	2	3.6	FACT241018	\N	\N	\N
276	2024-10-12	7	2	0	2	3.6	FACT241018	\N	\N	\N
278	2024-10-12	10	2	0	2	3.5	FACT241018	\N	\N	\N
280	2024-10-12	12	2	0	2	2.5	FACT241018	\N	\N	\N
282	2024-10-12	14	2	0	2	3.6	FACT241018	\N	\N	\N
284	2024-10-12	16	2	0	2	3.6	FACT241018	\N	\N	\N
286	2024-10-12	19	2	0	2	3.6	FACT241018	\N	\N	\N
288	2024-10-12	20	2	0	2	3.6	FACT241018	\N	\N	\N
290	2024-10-12	23	1	0	2	21	FACT241018	\N	\N	\N
292	2024-10-12	25	2	0	2	3.6	FACT241018	\N	\N	\N
294	2024-10-12	28	2	0	2	1.6	FACT241018	\N	\N	\N
296	2024-10-12	28	2	0	2	3.6	FACT241018	\N	\N	\N
298	2024-10-12	31	1	0	2	3.2	FACT241018	\N	\N	\N
300	2024-10-12	33	6	0	2	3.2	FACT241018	\N	\N	\N
302	2024-10-12	35	1	0	2	3.5	FACT241018	\N	\N	\N
304	2024-10-12	29	3	0	2	1.4	FACT241018	\N	\N	\N
306	2024-10-12	47	2	0	2	3.6	FACT241018	\N	\N	\N
308	2024-10-12	50	1	0	2	1	FACT241018	\N	\N	\N
310	2024-10-12	52	1	0	2	6.32	FACT241018	\N	\N	\N
312	2024-10-12	54	1	0	2	3.65	FACT241018	\N	\N	\N
314	2024-10-12	56	1	0	2	1.5	FACT241018	\N	\N	\N
316	2024-10-12	59	3	0	2	3	FACT241018	\N	\N	\N
318	2024-10-12	61	1	0	2	1	FACT241018	\N	\N	\N
320	2024-10-12	63	2	0	2	2	FACT241018	\N	\N	\N
322	2024-10-12	65	2	0	2	2	FACT241018	\N	\N	\N
324	2024-10-12	67	3	0	2	3	FACT241018	\N	\N	\N
326	2024-10-12	71	1	0	2	3.6	FACT241018	\N	\N	\N
328	2024-10-12	74	2	0	2	2	FACT241018	\N	\N	\N
330	2024-10-12	76	2	0	2	2	FACT241018	\N	\N	\N
332	2024-10-12	78	2	0	2	3.6	FACT241018	\N	\N	\N
334	2024-10-12	80	2	0	2	2	FACT241018	\N	\N	\N
336	2024-10-12	82	1	0	2	1	FACT241018	\N	\N	\N
338	2024-10-12	84	1	0	2	1	FACT241018	\N	\N	\N
340	2024-10-12	86	1	0	2	10	FACT241018	\N	\N	\N
342	2024-10-12	88	2	0	2	3.6	FACT241018	\N	\N	\N
344	2024-10-12	90	2	0	2	3	FACT241018	\N	\N	\N
346	2024-10-12	95	2	0	2	2	FACT241018	\N	\N	\N
348	2024-10-12	97	1	0	2	1	FACT241018	\N	\N	\N
350	2024-10-12	106	1	0	2	2.5	FACT241018	\N	\N	\N
352	2024-10-12	109	2	0	2	2	FACT241018	\N	\N	\N
354	2024-10-12	111	3	0	2	2.5	FACT241018	\N	\N	\N
356	2024-10-12	115	2	0	2	3.65	FACT241018	\N	\N	\N
358	2024-10-12	117	3	0	2	2.6	FACT241018	\N	\N	\N
360	2024-10-12	119	2	0	2	13.6	FACT241018	\N	\N	\N
362	2024-10-12	119	1	0	2	6.9	FACT241018	\N	\N	\N
364	2024-10-12	99	1	0	2	3.65	FACT241018	\N	\N	\N
366	2024-10-12	102	2	0	2	3.65	FACT241018	\N	\N	\N
1226	2024-10-15	23	2	0	2	3.6	005	\N	\N	\N
1227	2024-10-15	22	5	0	2	6.1	005	\N	\N	\N
1228	2024-10-15	24	2	0	2	3.5	005	\N	\N	\N
1229	2024-10-15	20	4	0	2	6.3	005	\N	\N	\N
1230	2024-10-15	56	10	0	2	2.36	005	\N	\N	\N
1231	2024-10-15	25	3	0	2	0.25	005	\N	\N	\N
1232	2024-10-15	26	12	0	2	3.698	005	\N	\N	\N
1233	2024-10-15	30	13	0	2	6.65	005	\N	\N	\N
1234	2024-10-15	31	8	0	2	3.6	005	\N	\N	\N
1235	2024-10-15	32	15	0	2	2.36	005	\N	\N	\N
1236	2024-10-15	33	13	0	2	2.5	005	\N	\N	\N
1237	2024-10-15	34	2	0	2	12.6	005	\N	\N	\N
1238	2024-10-15	8	15	0	2	3.6	005	\N	\N	\N
1239	2024-10-15	9	12	0	2	1	005	\N	\N	\N
1240	2024-10-15	46	14	0	2	5.6	005	\N	\N	\N
1241	2024-10-15	47	3	0	2	6.36	005	\N	\N	\N
1242	2024-10-15	35	3	0	2	6.36	005	\N	\N	\N
1243	2024-10-15	37	15	0	2	36	005	\N	\N	\N
1244	2024-10-15	57	13	0	2	6.5	005	\N	\N	\N
1245	2024-10-15	49	13	0	2	2.6	005	\N	\N	\N
1246	2024-10-15	50	15	0	2	3.6	005	\N	\N	\N
1247	2024-10-15	51	6	0	2	3	005	\N	\N	\N
1248	2024-10-15	52	10	0	2	2	005	\N	\N	\N
1249	2024-10-15	59	1	0	2	1	005	\N	\N	\N
1250	2024-10-15	60	4	0	2	4	005	\N	\N	\N
1251	2024-10-15	66	2	0	2	5	005	\N	\N	\N
1252	2024-10-15	67	3	0	2	5	005	\N	\N	\N
1253	2024-10-15	70	8	0	2	12	005	\N	\N	\N
1254	2024-10-15	53	12	0	2	5	005	\N	\N	\N
187	2024-10-12	1	1	0	2	2	00100	\N	\N	\N
1255	2024-10-15	71	12	0	2	6	005	\N	\N	\N
1256	2024-10-15	72	3	0	2	6	005	\N	\N	\N
1257	2024-10-15	74	4	0	2	2	005	\N	\N	\N
1258	2024-10-15	75	2	0	2	6	005	\N	\N	\N
1259	2024-10-15	76	3	0	2	1	005	\N	\N	\N
1260	2024-10-15	78	4	0	2	2	005	\N	\N	\N
1261	2024-10-15	79	5	0	2	6	005	\N	\N	\N
1262	2024-10-15	17	12	0	2	6	005	\N	\N	\N
1263	2024-10-15	21	8	0	2	2	005	\N	\N	\N
271	2024-10-12	2	5	0	2	2.3	FACT241018	\N	\N	\N
273	2024-10-12	4	1	0	2	1	FACT241018	\N	\N	\N
275	2024-10-12	6	2	0	2	3.6	FACT241018	\N	\N	\N
277	2024-10-12	6	2	0	2	3.6	FACT241018	\N	\N	\N
279	2024-10-12	11	1	0	2	2.3	FACT241018	\N	\N	\N
281	2024-10-12	13	3	0	2	2.6	FACT241018	\N	\N	\N
283	2024-10-12	15	1	0	2	2.3	FACT241018	\N	\N	\N
285	2024-10-12	18	2	0	2	3.8	FACT241018	\N	\N	\N
287	2024-10-12	20	2	0	2	3.6	FACT241018	\N	\N	\N
289	2024-10-12	22	2	0	2	3.6	FACT241018	\N	\N	\N
291	2024-10-12	24	2	0	2	3.6	FACT241018	\N	\N	\N
293	2024-10-12	26	2	0	2	3.5	FACT241018	\N	\N	\N
295	2024-10-12	30	1	0	2	3.2	FACT241018	\N	\N	\N
297	2024-10-12	30	1	0	2	3.2	FACT241018	\N	\N	\N
299	2024-10-12	32	1	0	2	3.6	FACT241018	\N	\N	\N
301	2024-10-12	34	2	0	2	3.5	FACT241018	\N	\N	\N
303	2024-10-12	37	2	0	2	3.65	FACT241018	\N	\N	\N
305	2024-10-12	46	1	0	2	2.3	FACT241018	\N	\N	\N
307	2024-10-12	49	2	0	2	4.5	FACT241018	\N	\N	\N
309	2024-10-12	51	4	0	2	4	FACT241018	\N	\N	\N
311	2024-10-12	53	2	0	2	2	FACT241018	\N	\N	\N
313	2024-10-12	55	2	0	2	3	FACT241018	\N	\N	\N
315	2024-10-12	57	2	0	2	2	FACT241018	\N	\N	\N
317	2024-10-12	60	2	0	2	3.5	FACT241018	\N	\N	\N
319	2024-10-12	62	3	0	2	2.5	FACT241018	\N	\N	\N
321	2024-10-12	64	2	0	2	3	FACT241018	\N	\N	\N
323	2024-10-12	66	2	0	2	3	FACT241018	\N	\N	\N
325	2024-10-12	70	2	0	2	2	FACT241018	\N	\N	\N
327	2024-10-12	72	2	0	2	2	FACT241018	\N	\N	\N
329	2024-10-12	75	1	0	2	1	FACT241018	\N	\N	\N
331	2024-10-12	77	3	0	2	1	FACT241018	\N	\N	\N
333	2024-10-12	79	10	0	2	1	FACT241018	\N	\N	\N
335	2024-10-12	81	2	0	2	2	FACT241018	\N	\N	\N
337	2024-10-12	83	1	0	2	2	FACT241018	\N	\N	\N
339	2024-10-12	85	1	0	2	1	FACT241018	\N	\N	\N
341	2024-10-12	87	1	0	2	2	FACT241018	\N	\N	\N
343	2024-10-12	89	2	0	2	2	FACT241018	\N	\N	\N
345	2024-10-12	93	3	0	2	6	FACT241018	\N	\N	\N
347	2024-10-12	96	3	0	2	2	FACT241018	\N	\N	\N
349	2024-10-12	98	2	0	2	2	FACT241018	\N	\N	\N
351	2024-10-12	107	3	0	2	2.6	FACT241018	\N	\N	\N
353	2024-10-12	110	3	0	2	2.6	FACT241018	\N	\N	\N
355	2024-10-12	113	2	0	2	3.65	FACT241018	\N	\N	\N
357	2024-10-12	116	3	0	2	3.69	FACT241018	\N	\N	\N
359	2024-10-12	119	2	0	2	3.65	FACT241018	\N	\N	\N
361	2024-10-12	118	2	0	2	2.5	FACT241018	\N	\N	\N
363	2024-10-12	120	3	0	2	2.6	FACT241018	\N	\N	\N
365	2024-10-12	100	2	0	2	2.3	FACT241018	\N	\N	\N
1264	2024-10-15	27	13	0	2	10	005	\N	\N	\N
369	2024-10-12	23	2	0	2	2	003	\N	\N	\N
1265	2024-10-15	36	8	0	2	3	005	\N	\N	\N
1266	2024-10-15	38	10	0	2	5	005	\N	\N	\N
1267	2024-10-15	39	3	0	2	6	005	\N	\N	\N
1268	2024-10-15	40	4	0	2	10	005	\N	\N	\N
1269	2024-10-15	41	5	0	2	5	005	\N	\N	\N
1270	2024-10-15	42	3	0	2	2	005	\N	\N	\N
1271	2024-10-15	43	4	0	2	2	005	\N	\N	\N
1272	2024-10-15	44	10	0	2	2	005	\N	\N	\N
1273	2024-10-15	45	3	0	2	2	005	\N	\N	\N
1274	2024-10-15	48	4	0	2	4	005	\N	\N	\N
1275	2024-10-15	58	3	0	2	2	005	\N	\N	\N
1276	2024-10-15	68	4	0	2	2	005	\N	\N	\N
1277	2024-10-15	69	1	0	2	1	005	\N	\N	\N
1278	2024-10-15	73	6	0	2	1	005	\N	\N	\N
1279	2024-10-15	61	4	0	2	2	005	\N	\N	\N
1280	2024-10-15	63	2	0	2	1	005	\N	\N	\N
1281	2024-10-15	64	6	0	2	1	005	\N	\N	\N
1282	2024-10-15	65	9	0	2	7	005	\N	\N	\N
1283	2024-10-15	77	3	0	2	5	005	\N	\N	\N
1284	2024-10-15	82	2	0	2	3	005	\N	\N	\N
1285	2024-10-15	83	1	0	2	2	005	\N	\N	\N
1286	2024-10-15	84	2	0	2	1	005	\N	\N	\N
1287	2024-10-15	85	3	0	2	2	005	\N	\N	\N
1288	2024-10-15	86	1	0	2	1	005	\N	\N	\N
269	2024-10-12	28	2	0	2	2	002	\N	\N	\N
1289	2024-10-15	87	2	0	2	5	005	\N	\N	\N
1290	2024-10-15	88	3	0	2	1	005	\N	\N	\N
1291	2024-10-15	11	10	0	2	1	005	\N	\N	\N
1292	2024-10-15	12	10	0	2	2	005	\N	\N	\N
1293	2024-10-15	13	3	0	2	1	005	\N	\N	\N
1294	2024-10-15	14	2	0	2	1	005	\N	\N	\N
1295	2024-10-15	14	10	0	2	2	005	\N	\N	\N
1296	2024-10-15	89	3	0	2	1	005	\N	\N	\N
1297	2024-10-15	90	2	0	2	1	005	\N	\N	\N
1298	2024-10-15	93	2	0	2	2	005	\N	\N	\N
1299	2024-10-15	92	2	0	2	2	005	\N	\N	\N
1300	2024-10-15	99	10	0	2	1	005	\N	\N	\N
1301	2024-10-15	100	2	0	2	2	005	\N	\N	\N
1302	2024-10-15	101	3	0	2	1	005	\N	\N	\N
1303	2024-10-15	102	10	0	2	1	005	\N	\N	\N
1304	2024-10-15	104	5	0	2	1	005	\N	\N	\N
1305	2024-10-15	105	3	0	2	2	005	\N	\N	\N
1306	2024-10-15	106	2	0	2	1	005	\N	\N	\N
1307	2024-10-15	107	3	0	2	2	005	\N	\N	\N
1308	2024-10-15	108	2	0	2	1	005	\N	\N	\N
1309	2024-10-15	109	3	0	2	2	005	\N	\N	\N
1310	2024-10-15	110	3	0	2	1	005	\N	\N	\N
1311	2024-10-15	111	2	0	2	1	005	\N	\N	\N
1312	2024-10-15	112	2	0	2	1	005	\N	\N	\N
1313	2024-10-15	113	2	0	2	1	005	\N	\N	\N
1314	2024-10-15	114	3	0	2	2	005	\N	\N	\N
658	2024-10-12	23	3	0	2	1.1	004	\N	\N	\N
1315	2024-10-15	115	2	0	2	2	005	\N	\N	\N
1316	2024-10-15	116	2	0	2	2	005	\N	\N	\N
1317	2024-10-15	121	10	0	2	1	005	\N	\N	\N
1318	2024-10-15	122	2	0	2	2	005	\N	\N	\N
1319	2024-10-15	123	3	0	2	1	005	\N	\N	\N
1320	2024-10-15	124	2	0	2	1	005	\N	\N	\N
665	2024-10-12	25	5	0	2	2	fact22	\N	\N	\N
666	2024-10-12	56	3	0	2	2	fact22	\N	\N	\N
1321	2024-10-15	125	2	0	2	1	005	\N	\N	\N
1322	2024-10-15	126	2	0	2	1	005	\N	\N	\N
1323	2024-10-15	127	3	0	2	1	005	\N	\N	\N
1324	2024-10-15	128	2	0	2	2	005	\N	\N	\N
1325	2024-10-15	129	2	0	2	1	005	\N	\N	\N
1326	2024-10-15	130	3	0	2	1	005	\N	\N	\N
1327	2024-10-15	131	2	0	2	1	005	\N	\N	\N
1328	2024-10-15	132	3	0	2	1	005	\N	\N	\N
1056	2024-10-13	136	3	0	2	2	PONDERADO1	\N	\N	\N
1329	2024-10-15	133	2	0	2	2	005	\N	\N	\N
1058	2024-10-13	136	2	0	2	5	PONDERADO2	\N	\N	\N
1059	2024-10-13	136	2	0	2	5	PONDERADO3	\N	\N	\N
1060	2024-10-13	137	3	0	2	2	POND1	\N	\N	\N
1330	2024-10-15	134	2	0	2	2	005	\N	\N	\N
1331	2024-10-15	135	3	0	2	2	005	\N	\N	\N
1063	2024-10-13	137	1	0	2	5	POND2	\N	\N	\N
1064	2024-10-13	138	3	0	2	2	P001	\N	\N	\N
1069	2024-10-13	138	2	0	2	5	P002	\N	\N	\N
1070	2024-10-13	139	3	0	2	2	PND01	\N	\N	\N
1072	2024-10-13	139	2	0	2	5	PND02	\N	\N	\N
1073	2024-10-13	140	3	0	2	2	C001	\N	\N	\N
1075	2024-10-13	140	2	0	2	5	C002	\N	\N	\N
1076	2024-10-13	141	3	0	2	2	L001	\N	\N	\N
1078	2024-10-13	141	10	0	2	2	l003	\N	\N	\N
1079	2024-10-13	142	3	0	2	2	T010	\N	\N	\N
1080	2024-10-13	142	2	0	2	5	T00203	\N	\N	\N
1081	2024-10-13	142	1	0	2	1	T23	\N	\N	\N
1082	2024-10-13	142	2	0	2	2.5	T0100	\N	\N	\N
1083	2024-10-13	143	3	0	2	2	D001	\N	\N	\N
1084	2024-10-13	143	2	0	2	5	D003	\N	\N	\N
1085	2024-10-13	143	1	0	2	1	d004	\N	\N	\N
1086	2024-10-13	144	3	0	2	2	V001	\N	\N	\N
1087	2024-10-13	144	2	0	2	5	V002	\N	\N	\N
1088	2024-10-13	145	3	0	2	2	UNO01	\N	\N	\N
1089	2024-10-13	145	2	0	2	5	UNO02	\N	\N	\N
1090	2024-10-13	146	3	0	2	2	PEPE01	\N	\N	\N
1091	2024-10-13	146	2	0	2	5	PEPE02	\N	\N	\N
1092	2024-10-13	147	2	0	2	3	TEC001	\N	\N	\N
1093	2024-10-13	147	2	0	2	3	TEC002	\N	\N	\N
1094	2024-10-13	148	3	0	2	2	P200	\N	\N	\N
1095	2024-10-13	148	2	0	2	5	P003	\N	\N	\N
1096	2024-10-13	149	3	0	2	2	CABLE01	\N	\N	\N
1097	2024-10-13	149	2	0	2	5	CABLE02	\N	\N	\N
1098	2024-10-13	150	3	0	2	2	SACA01	\N	\N	\N
1099	2024-10-13	150	2	0	2	5	SACA02	\N	\N	\N
1100	2024-10-13	151	2	0	2	5	MONITOR01	\N	\N	\N
1101	2024-10-13	151	3	0	2	4	MONIT69	\N	\N	\N
1102	2024-10-13	152	3	0	2	2	DISQ01	\N	\N	\N
1103	2024-10-13	152	4	0	2	5	DISQ02	\N	\N	\N
1104	2024-10-13	153	3	0	2	2	CD01	\N	\N	\N
1105	2024-10-13	153	2	0	2	5	CD023	\N	\N	\N
1106	2024-10-13	153	1	0	2	2	CD03	\N	\N	\N
1107	2024-10-13	154	3	0	2	2	J001	\N	\N	\N
1109	2024-10-13	154	2	0	2	5	j0023	\N	\N	\N
1110	2024-10-13	155	3	0	2	2	PU001	\N	\N	\N
1113	2024-10-13	155	1	0	2	1.8	PUERTA	\N	\N	\N
1343	2024-10-15	38	0	1	2	5	2	\N	\N	\N
1344	2024-10-15	20	0	2	2	5.64	2	\N	\N	\N
1345	2024-10-15	38	0	1	2	5	2	\N	\N	\N
1350	2024-10-15	30	0	6	2	6.55	4	\N	\N	\N
1351	2024-10-15	30	0	7	2	6.55	4	\N	\N	\N
1354	2024-10-15	38	0	3	2	5	5	\N	\N	\N
1355	2024-10-15	30	0	2	2	6.55	5	\N	\N	\N
1356	2024-10-15	155	2	0	2	5	PU02	\N	\N	\N
1367	2024-10-21	156	2	0	2	3.6	NUEVO01	\N	\N	\N
1371	2024-10-21	141	1	0	2	5	L002	\N	\N	\N
1372	2024-10-21	33	3	0	2	2	L002	\N	\N	\N
1373	2024-10-21	27	2	0	2	1	L002	\N	\N	\N
1378	2024-10-21	157	3	0	2	2.85	NUEVO201	\N	\N	\N
\.


--
-- Data for Name: inicios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inicios (consecutivo, estado) FROM stdin;
1	1
\.


--
-- Data for Name: movimientos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movimientos (id, id_documento, tipo, cod_articulo, cantidad) FROM stdin;
\.


--
-- Data for Name: proveedores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.proveedores (nombre, direccion, telefono, rif_proveedor) FROM stdin;
PROVEEDORES	SAN ANTONIO	02767717107	J123456789
\.


--
-- Data for Name: servicios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.servicios (cod_servicio, nombre_servicio, cedula_firmante, seccion) FROM stdin;
1	EMERGENCIA	123	2
2	FARMACIA	18353859	2
\.


--
-- Data for Name: temporal_articulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.temporal_articulo (id, cod_articulos, costo_articulos, seccion, documento, cantidad_entrada, cantidad_salida, cantidad_pedido) FROM stdin;
18	98	2	2	FACT241018	2	0	\N
20	20	2.3	2	INICIAL	2	0	\N
\.


--
-- Data for Name: temporal_doc_entrada; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.temporal_doc_entrada (id, fecha_documentos, fecha_operaciones, seccion, proveedores, conceptos, cantidad_articulos, valor_operacion, doc_entrada, consecutivo, observaciones, codigo_almacen_despacho, codigo_almacen_destino) FROM stdin;
\.


--
-- Data for Name: temporal_doc_salida; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.temporal_doc_salida (id, numero_documentos, fecha_documento, fecha_operacion, seccion, servicios, conceptos, cantidad_articulos, valor_operacion, consecutivo, observaciones, codigo_almacen_despacho, codigo_almacen_destino, fecha_pedido) FROM stdin;
\.


--
-- Data for Name: unidades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.unidades (cod_unidad, nombre, abreviatura) FROM stdin;
1	UNIDAD	UND
\.


--
-- Name: articulos_id_seq; Type: SEQUENCE SET; Schema: hsdm; Owner: postgres
--

SELECT pg_catalog.setval('hsdm.articulos_id_seq', 1, false);


--
-- Name: cargos_id_seq; Type: SEQUENCE SET; Schema: hsdm; Owner: postgres
--

SELECT pg_catalog.setval('hsdm.cargos_id_seq', 1, false);


--
-- Name: documentos_id_seq; Type: SEQUENCE SET; Schema: hsdm; Owner: postgres
--

SELECT pg_catalog.setval('hsdm.documentos_id_seq', 1, false);


--
-- Name: hospitales_id_seq; Type: SEQUENCE SET; Schema: hsdm; Owner: postgres
--

SELECT pg_catalog.setval('hsdm.hospitales_id_seq', 1, false);


--
-- Name: inicios_id_seq; Type: SEQUENCE SET; Schema: hsdm; Owner: postgres
--

SELECT pg_catalog.setval('hsdm.inicios_id_seq', 1, true);


--
-- Name: kardex_id_seq; Type: SEQUENCE SET; Schema: hsdm; Owner: postgres
--

SELECT pg_catalog.setval('hsdm.kardex_id_seq', 1, false);


--
-- Name: secciones_id_seq; Type: SEQUENCE SET; Schema: hsdm; Owner: postgres
--

SELECT pg_catalog.setval('hsdm.secciones_id_seq', 1, false);


--
-- Name: servicios_id_seq; Type: SEQUENCE SET; Schema: hsdm; Owner: postgres
--

SELECT pg_catalog.setval('hsdm.servicios_id_seq', 1, false);


--
-- Name: unidades_id_seq; Type: SEQUENCE SET; Schema: hsdm; Owner: postgres
--

SELECT pg_catalog.setval('hsdm.unidades_id_seq', 1, false);


--
-- Name: articulos_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.articulos_codigo_seq', 157, true);


--
-- Name: cargos_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cargos_codigo_seq', 5, true);


--
-- Name: costos_clave_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.costos_clave_seq', 157, true);


--
-- Name: datosreportes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.datosreportes_id_seq', 20, true);


--
-- Name: decimales_cod_seccion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.decimales_cod_seccion_seq', 1, false);


--
-- Name: doc_entradas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doc_entradas_id_seq', 119, true);


--
-- Name: doc_salidas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.doc_salidas_id_seq', 5, true);


--
-- Name: empresas_cod_empresas_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empresas_cod_empresas_seq', 3, true);


--
-- Name: existencias_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.existencias_id_seq', 157, true);


--
-- Name: historiales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.historiales_id_seq', 1378, true);


--
-- Name: inicios_consecutivo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inicios_consecutivo_seq', 1, true);


--
-- Name: movimientos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movimientos_id_seq', 1, false);


--
-- Name: servicios_cod_servicio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.servicios_cod_servicio_seq', 2, true);


--
-- Name: temporal_articulo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.temporal_articulo_id_seq', 672, true);


--
-- Name: temporal_doc_entrada_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.temporal_doc_entrada_id_seq', 60, true);


--
-- Name: temporal_doc_salida_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.temporal_doc_salida_id_seq', 13, true);


--
-- Name: unidades_cod_unidad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.unidades_cod_unidad_seq', 1, true);


--
-- Name: almacenes almacenes_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.almacenes
    ADD CONSTRAINT almacenes_pkey PRIMARY KEY (codigo_almacen);


--
-- Name: articulos articulos_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.articulos
    ADD CONSTRAINT articulos_pkey PRIMARY KEY (id);


--
-- Name: cargos cargos_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.cargos
    ADD CONSTRAINT cargos_pkey PRIMARY KEY (id);


--
-- Name: conceptos conceptos_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.conceptos
    ADD CONSTRAINT conceptos_pkey PRIMARY KEY (codigo);


--
-- Name: documentos documentos_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.documentos
    ADD CONSTRAINT documentos_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: grupos grupos_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.grupos
    ADD CONSTRAINT grupos_pkey PRIMARY KEY (codigo);


--
-- Name: hospitales hospitales_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.hospitales
    ADD CONSTRAINT hospitales_pkey PRIMARY KEY (id);


--
-- Name: hospitales hospitales_rif_key; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.hospitales
    ADD CONSTRAINT hospitales_rif_key UNIQUE (rif);


--
-- Name: inicios inicios_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.inicios
    ADD CONSTRAINT inicios_pkey PRIMARY KEY (id);


--
-- Name: kardex kardex_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.kardex
    ADD CONSTRAINT kardex_pkey PRIMARY KEY (id);


--
-- Name: proveedores proveedores_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.proveedores
    ADD CONSTRAINT proveedores_pkey PRIMARY KEY (rif);


--
-- Name: saldos saldos_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.saldos
    ADD CONSTRAINT saldos_pkey PRIMARY KEY (articulo_id, seccion_id);


--
-- Name: secciones secciones_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.secciones
    ADD CONSTRAINT secciones_pkey PRIMARY KEY (id);


--
-- Name: servicios servicios_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.servicios
    ADD CONSTRAINT servicios_pkey PRIMARY KEY (id);


--
-- Name: subgrupos subgrupos_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.subgrupos
    ADD CONSTRAINT subgrupos_pkey PRIMARY KEY (grupo_codigo, codigo);


--
-- Name: unidades unidades_pkey; Type: CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.unidades
    ADD CONSTRAINT unidades_pkey PRIMARY KEY (id);


--
-- Name: almacenes almacenes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.almacenes
    ADD CONSTRAINT almacenes_pkey PRIMARY KEY (codigo_almacen);


--
-- Name: articulos articulos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articulos
    ADD CONSTRAINT articulos_pkey PRIMARY KEY (codigo);


--
-- Name: cargos cargos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cargos
    ADD CONSTRAINT cargos_pkey PRIMARY KEY (codigo);


--
-- Name: conceptos conceptos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conceptos
    ADD CONSTRAINT conceptos_pkey PRIMARY KEY (codigo);


--
-- Name: costos costos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.costos
    ADD CONSTRAINT costos_pkey PRIMARY KEY (clave);


--
-- Name: datosreportes datosreportes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.datosreportes
    ADD CONSTRAINT datosreportes_pkey PRIMARY KEY (id);


--
-- Name: decimales decimales_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.decimales
    ADD CONSTRAINT decimales_pkey PRIMARY KEY (cod_seccion);


--
-- Name: doc_entradas doc_entradas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc_entradas
    ADD CONSTRAINT doc_entradas_pkey PRIMARY KEY (id);


--
-- Name: doc_salidas doc_salidas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc_salidas
    ADD CONSTRAINT doc_salidas_pkey PRIMARY KEY (id);


--
-- Name: temporal_doc_entrada doc_unico_entrada; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.temporal_doc_entrada
    ADD CONSTRAINT doc_unico_entrada UNIQUE (doc_entrada);


--
-- Name: doc_entradas documento_unico; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doc_entradas
    ADD CONSTRAINT documento_unico UNIQUE (numero_doc);


--
-- Name: temporal_doc_salida documento_unico_salida; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.temporal_doc_salida
    ADD CONSTRAINT documento_unico_salida UNIQUE (numero_documentos);


--
-- Name: empresas empresas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empresas
    ADD CONSTRAINT empresas_pkey PRIMARY KEY (cod_empresas);


--
-- Name: existencias existencias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.existencias
    ADD CONSTRAINT existencias_pkey PRIMARY KEY (id);


--
-- Name: firmas firmas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.firmas
    ADD CONSTRAINT firmas_pkey PRIMARY KEY (cedula);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: grupos grupos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grupos
    ADD CONSTRAINT grupos_pkey PRIMARY KEY (codigo_sub);


--
-- Name: historiales historiales_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historiales
    ADD CONSTRAINT historiales_pkey PRIMARY KEY (id);


--
-- Name: inicios inicios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inicios
    ADD CONSTRAINT inicios_pkey PRIMARY KEY (consecutivo);


--
-- Name: movimientos movimientos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimientos
    ADD CONSTRAINT movimientos_pkey PRIMARY KEY (id);


--
-- Name: proveedores proveedores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proveedores
    ADD CONSTRAINT proveedores_pkey PRIMARY KEY (rif_proveedor);


--
-- Name: servicios servicios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.servicios
    ADD CONSTRAINT servicios_pkey PRIMARY KEY (cod_servicio);


--
-- Name: temporal_articulo temporal_articulo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.temporal_articulo
    ADD CONSTRAINT temporal_articulo_pkey PRIMARY KEY (id);


--
-- Name: temporal_doc_entrada temporal_doc_entrada_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.temporal_doc_entrada
    ADD CONSTRAINT temporal_doc_entrada_pkey PRIMARY KEY (id);


--
-- Name: temporal_doc_salida temporal_doc_salida_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.temporal_doc_salida
    ADD CONSTRAINT temporal_doc_salida_pkey PRIMARY KEY (id);


--
-- Name: unidades unidades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.unidades
    ADD CONSTRAINT unidades_pkey PRIMARY KEY (cod_unidad);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: hsdm; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON hsdm.flyway_schema_history USING btree (success);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: almacenes almacenes_hospital_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.almacenes
    ADD CONSTRAINT almacenes_hospital_id_fkey FOREIGN KEY (hospital_id) REFERENCES hsdm.hospitales(id);


--
-- Name: almacenes almacenes_seccion_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.almacenes
    ADD CONSTRAINT almacenes_seccion_id_fkey FOREIGN KEY (seccion_id) REFERENCES hsdm.secciones(id);


--
-- Name: articulos articulos_grupo_cod_subgrupo_cod_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.articulos
    ADD CONSTRAINT articulos_grupo_cod_subgrupo_cod_fkey FOREIGN KEY (grupo_cod, subgrupo_cod) REFERENCES hsdm.subgrupos(grupo_codigo, codigo);


--
-- Name: articulos articulos_unidad_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.articulos
    ADD CONSTRAINT articulos_unidad_id_fkey FOREIGN KEY (unidad_id) REFERENCES hsdm.unidades(id);


--
-- Name: cargos cargos_hospital_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.cargos
    ADD CONSTRAINT cargos_hospital_id_fkey FOREIGN KEY (hospital_id) REFERENCES hsdm.hospitales(id);


--
-- Name: cargos cargos_seccion_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.cargos
    ADD CONSTRAINT cargos_seccion_id_fkey FOREIGN KEY (seccion_id) REFERENCES hsdm.secciones(id);


--
-- Name: documentos documentos_hospital_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.documentos
    ADD CONSTRAINT documentos_hospital_id_fkey FOREIGN KEY (hospital_id) REFERENCES hsdm.hospitales(id);


--
-- Name: documentos documentos_seccion_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.documentos
    ADD CONSTRAINT documentos_seccion_id_fkey FOREIGN KEY (seccion_id) REFERENCES hsdm.secciones(id);


--
-- Name: kardex kardex_articulo_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.kardex
    ADD CONSTRAINT kardex_articulo_id_fkey FOREIGN KEY (articulo_id) REFERENCES hsdm.articulos(id);


--
-- Name: kardex kardex_documento_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.kardex
    ADD CONSTRAINT kardex_documento_id_fkey FOREIGN KEY (documento_id) REFERENCES hsdm.documentos(id);


--
-- Name: kardex kardex_seccion_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.kardex
    ADD CONSTRAINT kardex_seccion_id_fkey FOREIGN KEY (seccion_id) REFERENCES hsdm.secciones(id);


--
-- Name: saldos saldos_articulo_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.saldos
    ADD CONSTRAINT saldos_articulo_id_fkey FOREIGN KEY (articulo_id) REFERENCES hsdm.articulos(id);


--
-- Name: saldos saldos_hospital_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.saldos
    ADD CONSTRAINT saldos_hospital_id_fkey FOREIGN KEY (hospital_id) REFERENCES hsdm.hospitales(id);


--
-- Name: saldos saldos_seccion_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.saldos
    ADD CONSTRAINT saldos_seccion_id_fkey FOREIGN KEY (seccion_id) REFERENCES hsdm.secciones(id);


--
-- Name: secciones secciones_hospital_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.secciones
    ADD CONSTRAINT secciones_hospital_id_fkey FOREIGN KEY (hospital_id) REFERENCES hsdm.hospitales(id);


--
-- Name: servicios servicios_hospital_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.servicios
    ADD CONSTRAINT servicios_hospital_id_fkey FOREIGN KEY (hospital_id) REFERENCES hsdm.hospitales(id);


--
-- Name: servicios servicios_seccion_id_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.servicios
    ADD CONSTRAINT servicios_seccion_id_fkey FOREIGN KEY (seccion_id) REFERENCES hsdm.secciones(id);


--
-- Name: subgrupos subgrupos_grupo_codigo_fkey; Type: FK CONSTRAINT; Schema: hsdm; Owner: postgres
--

ALTER TABLE ONLY hsdm.subgrupos
    ADD CONSTRAINT subgrupos_grupo_codigo_fkey FOREIGN KEY (grupo_codigo) REFERENCES hsdm.grupos(codigo);


--
-- PostgreSQL database dump complete
--

