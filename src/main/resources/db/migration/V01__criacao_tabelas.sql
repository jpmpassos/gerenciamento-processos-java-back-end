-- SQL Manager for PostgreSQL 5.4.0.42613
-- ---------------------------------------
-- Host      : localhost
-- Database  : db_gerenc_proces
-- Version   : PostgreSQL 9.6.3, compiled by Visual C++ build 1800, 64-bit



SET check_function_bodies = false;
--
-- Structure for table usuario (OID = 50872) : 
--
SET search_path = public, pg_catalog;

DROP TABLE IF EXISTS public.usuario;

CREATE TABLE public.usuario (
  usuario_id SERIAL,
  nome TEXT NOT NULL,
  admin BOOLEAN NOT NULL,
  triador BOOLEAN NOT NULL,
  finalizador BOOLEAN NOT NULL,
  login TEXT NOT NULL,
  senha TEXT NOT NULL,
  CONSTRAINT usuario_login_key UNIQUE(login)
) 
WITH (oids = false);

ALTER TABLE public.usuario
  ALTER COLUMN finalizador SET STATISTICS 0;

ALTER TABLE public.usuario
  ALTER COLUMN login SET STATISTICS 0;

ALTER TABLE public.usuario
  ALTER COLUMN senha SET STATISTICS 0;
--
-- Structure for table processo (OID = 50883) : 
--

DROP TABLE IF EXISTS public.processo;

CREATE TABLE public.processo (
    processo_id serial NOT NULL,
    titulo text NOT NULL,
    descricao text
)
WITH (oids = false);
ALTER TABLE ONLY public.processo ALTER COLUMN descricao SET STATISTICS 0;
--
-- Structure for table parecer (OID = 50894) : 
--

DROP TABLE IF EXISTS public.parecer;

CREATE TABLE public.parecer (
    parecer_id serial NOT NULL,
    descricao text,
    status text NOT NULL,
    usuario_id integer NOT NULL,
    processo_id integer NOT NULL
)
WITH (oids = false);
--
-- Definition for index usuario_pkey (OID = 50879) : 
--
ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey
    PRIMARY KEY (usuario_id);
--
-- Definition for index processo_pkey (OID = 50890) : 
--
ALTER TABLE ONLY processo
    ADD CONSTRAINT processo_pkey
    PRIMARY KEY (processo_id);
--
-- Definition for index parecer_pkey (OID = 50901) : 
--
ALTER TABLE ONLY parecer
    ADD CONSTRAINT parecer_pkey
    PRIMARY KEY (parecer_id);
--
-- Definition for index parecer_fk (OID = 50903) : 
--
ALTER TABLE ONLY parecer
    ADD CONSTRAINT parecer_fk
    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id) ON UPDATE CASCADE ON DELETE RESTRICT;
--
-- Definition for index parecer_fk1 (OID = 50908) : 
--
ALTER TABLE ONLY parecer
    ADD CONSTRAINT parecer_fk1
    FOREIGN KEY (processo_id) REFERENCES processo(processo_id) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Definition for CONSTRAINT parecer_idx : 
--    
ALTER TABLE public.parecer
  ADD CONSTRAINT parecer_idx 
    UNIQUE ("usuario_id", "processo_id");    
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
