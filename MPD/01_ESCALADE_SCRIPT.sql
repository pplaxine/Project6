CREATE SEQUENCE public.compte_utilisateur_id_seq;

CREATE TABLE public.compte_utilisateur (
                id INTEGER NOT NULL DEFAULT nextval('public.compte_utilisateur_id_seq'),
                nom VARCHAR(70) NOT NULL,
                prenom VARCHAR(70) NOT NULL,
                pseudo VARCHAR(50) NOT NULL,
                email VARCHAR(50) NOT NULL,
                mdp VARCHAR NOT NULL,
                acces VARCHAR NOT NULL,
                CONSTRAINT compte_utilisateur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.compte_utilisateur_id_seq OWNED BY public.compte_utilisateur.id;

CREATE TABLE public.cotation (
                id INTEGER NOT NULL,
                note VARCHAR(3) NOT NULL,
                CONSTRAINT cotation_pk PRIMARY KEY (id)
);


CREATE TABLE public.dept (
                id INTEGER NOT NULL,
                nom VARCHAR(80) NOT NULL,
                CONSTRAINT dept_pk PRIMARY KEY (id)
);


CREATE SEQUENCE public.topo_id_seq;

CREATE TABLE public.topo (
                id INTEGER NOT NULL DEFAULT nextval('public.topo_id_seq'),
                nom VARCHAR(80) NOT NULL,
                disponible BOOLEAN NOT NULL,
                presentation VARCHAR(1000),
                date_debut_location TIMESTAMP,
                date_fin_location TIMESTAMP,
                preteur_id INTEGER NOT NULL,
                emprunteur_id INTEGER,
                CONSTRAINT topo_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.topo_id_seq OWNED BY public.topo.id;

CREATE SEQUENCE public.site_id_seq;

CREATE TABLE public.site (
                id INTEGER NOT NULL DEFAULT nextval('public.site_id_seq'),
                nom VARCHAR(80) NOT NULL,
                lieu VARCHAR(300) NOT NULL,
                description VARCHAR(1000),
                date_creation TIMESTAMP NOT NULL,
                dept_id INTEGER NOT NULL,
                compte_utilisateur_id INTEGER NOT NULL,
                topo_id INTEGER,
                CONSTRAINT site_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.site_id_seq OWNED BY public.site.id;

CREATE SEQUENCE public.commentaire_id_seq;

CREATE TABLE public.commentaire (
                id INTEGER NOT NULL DEFAULT nextval('public.commentaire_id_seq'),
                date_creation TIMESTAMP NOT NULL,
                contenu VARCHAR(5000) NOT NULL,
                site_id INTEGER NOT NULL,
                compte_utilisateur_id INTEGER NOT NULL,
                CONSTRAINT commentaire_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.commentaire_id_seq OWNED BY public.commentaire.id;

CREATE TABLE public.commentaire_associe (
                commentaire_id INTEGER NOT NULL,
                commentaire_associe_id INTEGER NOT NULL,
                CONSTRAINT commentaire_associe_pk PRIMARY KEY (commentaire_id, commentaire_associe_id)
);


CREATE SEQUENCE public.secteur_id_seq;

CREATE TABLE public.secteur (
                id INTEGER NOT NULL DEFAULT nextval('public.secteur_id_seq'),
                nom VARCHAR(80),
                site_id INTEGER NOT NULL,
                CONSTRAINT secteur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.secteur_id_seq OWNED BY public.secteur.id;

CREATE SEQUENCE public.voie_id_seq;

CREATE TABLE public.voie (
                id INTEGER NOT NULL DEFAULT nextval('public.voie_id_seq'),
                nom VARCHAR(80) NOT NULL,
                hauteur NUMERIC(7,2) NOT NULL,
                nombre_points INTEGER,
                nombre_longueurs INTEGER,
                cotation_id INTEGER NOT NULL,
                secteur_id INTEGER NOT NULL,
                CONSTRAINT voie_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.voie_id_seq OWNED BY public.voie.id;

ALTER TABLE public.site ADD CONSTRAINT compte_utilisateur_site_fk
FOREIGN KEY (compte_utilisateur_id)
REFERENCES public.compte_utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire ADD CONSTRAINT compte_utilisateur_commentaire_fk
FOREIGN KEY (compte_utilisateur_id)
REFERENCES public.compte_utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT compte_utilisateur_topo_fk
FOREIGN KEY (preteur_id)
REFERENCES public.compte_utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT compte_utilisateur_topo_fk1
FOREIGN KEY (emprunteur_id)
REFERENCES public.compte_utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.voie ADD CONSTRAINT cotation_voie_fk
FOREIGN KEY (cotation_id)
REFERENCES public.cotation (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.site ADD CONSTRAINT dept_site_fk
FOREIGN KEY (dept_id)
REFERENCES public.dept (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.site ADD CONSTRAINT topo_site_fk
FOREIGN KEY (topo_id)
REFERENCES public.topo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.secteur ADD CONSTRAINT site_secteur_fk
FOREIGN KEY (site_id)
REFERENCES public.site (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire ADD CONSTRAINT site_commentaire_fk
FOREIGN KEY (site_id)
REFERENCES public.site (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire_associe ADD CONSTRAINT commentaire_commentaire_associe_fk
FOREIGN KEY (commentaire_id)
REFERENCES public.commentaire (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire_associe ADD CONSTRAINT commentaire_commentaire_associe_fk1
FOREIGN KEY (commentaire_associe_id)
REFERENCES public.commentaire (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.voie ADD CONSTRAINT secteur_voie_fk
FOREIGN KEY (secteur_id)
REFERENCES public.secteur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;