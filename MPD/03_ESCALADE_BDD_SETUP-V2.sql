BEGIN TRANSACTION;

/*DROP ALL TABLES*/
DROP TABLE IF EXISTS compte_utilisateur CASCADE;
DROP TABLE IF EXISTS cotation CASCADE;
DROP TABLE IF EXISTS dept CASCADE;
DROP TABLE IF EXISTS topo CASCADE;
DROP TABLE IF EXISTS site CASCADE;
DROP TABLE IF EXISTS commentaire CASCADE;
DROP TABLE IF EXISTS commentaire_associe CASCADE;
DROP TABLE IF EXISTS secteur CASCADE;
DROP TABLE IF EXISTS voie CASCADE;
DROP TABLE IF EXISTS location_topo CASCADE;



/*INSERT ALL TABLE*/
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
                note VARCHAR(30) NOT NULL,
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
                presentation VARCHAR(1000),
                createur_id INTEGER NOT NULL,
                CONSTRAINT topo_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.topo_id_seq OWNED BY public.topo.id;

CREATE SEQUENCE public.location_topo_id_seq;

CREATE TABLE public.location_topo (
                id INTEGER NOT NULL DEFAULT nextval('public.location_topo_id_seq'),
                date_debut_location TIMESTAMP,
                date_fin_location TIMESTAMP,
                topo_id INTEGER NOT NULL,
                emprunteur_id INTEGER NOT NULL,
                accepte BOOLEAN,
                CONSTRAINT location_topo_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.location_topo_id_seq OWNED BY public.location_topo.id;

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
                site_id INTEGER,
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
                site_id INTEGER,
                secteur_id INTEGER,
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
FOREIGN KEY (createur_id)
REFERENCES public.compte_utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.location_topo ADD CONSTRAINT compte_utilisateur_location_topo_fk
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

ALTER TABLE public.location_topo ADD CONSTRAINT topo_location_topo_fk
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

ALTER TABLE public.voie ADD CONSTRAINT site_voie_fk
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

/*DATA INJECTION*/
INSERT INTO public.dept (id,nom) VALUES (1,'AIN');
INSERT INTO public.dept (id,nom) VALUES (2,'AISNE');
INSERT INTO public.dept (id,nom) VALUES (3,'ALLIER');
INSERT INTO public.dept (id,nom) VALUES (4,'ALPES_DE_HAUTE_PROVENCE'); 
INSERT INTO public.dept (id,nom) VALUES (5,'HAUTES_ALPES');
INSERT INTO public.dept (id,nom) VALUES (6,'ALPES_MARITIMES');	
INSERT INTO public.dept (id,nom) VALUES (7,'ARDECHE');
INSERT INTO public.dept (id,nom) VALUES (8,'ARDENNES');
INSERT INTO public.dept (id,nom) VALUES (9,'ARIEGE');
INSERT INTO public.dept (id,nom) VALUES (10,'AUBE');
INSERT INTO public.dept (id,nom) VALUES (11,'AUDE');
INSERT INTO public.dept (id,nom) VALUES (12,'AVEYRON');
INSERT INTO public.dept (id,nom) VALUES (13,'BOUCHE_DU_RHONE');
INSERT INTO public.dept (id,nom) VALUES (14,'CALVADOS');
INSERT INTO public.dept (id,nom) VALUES (15,'CANTAL');
INSERT INTO public.dept (id,nom) VALUES (16,'CHARENTE');
INSERT INTO public.dept (id,nom) VALUES (17,'CHARENTE_MARITIME');
INSERT INTO public.dept (id,nom) VALUES (18,'CHER');
INSERT INTO public.dept (id,nom) VALUES (19,'CORREZE');
INSERT INTO public.dept (id,nom) VALUES (20,'CORSE_DU_SUD');
INSERT INTO public.dept (id,nom) VALUES (21,'HAUTE_CORSE');
INSERT INTO public.dept (id,nom) VALUES (22,'COTE_D_OR');
INSERT INTO public.dept (id,nom) VALUES (23,'COTE_D_ARMOR');
INSERT INTO public.dept (id,nom) VALUES (24,'CREUSE');
INSERT INTO public.dept (id,nom) VALUES (25,'DORDOGNE');
INSERT INTO public.dept (id,nom) VALUES (26,'DOUBS');
INSERT INTO public.dept (id,nom) VALUES (27,'DROME');
INSERT INTO public.dept (id,nom) VALUES (28,'EURE');
INSERT INTO public.dept (id,nom) VALUES (29,'EURE_ET_LOIR');
INSERT INTO public.dept (id,nom) VALUES (30,'FINISTERE');
INSERT INTO public.dept (id,nom) VALUES (31,'GARD');
INSERT INTO public.dept (id,nom) VALUES (32,'HAUTE_GARONNE');
INSERT INTO public.dept (id,nom) VALUES (33,'GERS');
INSERT INTO public.dept (id,nom) VALUES (34,'GIRONDE');
INSERT INTO public.dept (id,nom) VALUES (35,'HERAULT');
INSERT INTO public.dept (id,nom) VALUES (36,'ILLE_ET_VILAINE');
INSERT INTO public.dept (id,nom) VALUES (37,'INDRE');
INSERT INTO public.dept (id,nom) VALUES (38,'INDRE_ET_LOIRE');
INSERT INTO public.dept (id,nom) VALUES (39,'ISERE');
INSERT INTO public.dept (id,nom) VALUES (40,'JURA');
INSERT INTO public.dept (id,nom) VALUES (41,'LANDES');
INSERT INTO public.dept (id,nom) VALUES (42,'LOIR_ET_CHER');
INSERT INTO public.dept (id,nom) VALUES (43,'LOIRE');
INSERT INTO public.dept (id,nom) VALUES (44,'HAUTE_LOIRE');
INSERT INTO public.dept (id,nom) VALUES (45,'LOIRE_ATLANTIQUE');
INSERT INTO public.dept (id,nom) VALUES (46,'LOIRET');
INSERT INTO public.dept (id,nom) VALUES (47,'LOT');
INSERT INTO public.dept (id,nom) VALUES (48,'LOT_ET_GARONNE');
INSERT INTO public.dept (id,nom) VALUES (49,'LOZERE');
INSERT INTO public.dept (id,nom) VALUES (50,'MANCHE');
INSERT INTO public.dept (id,nom) VALUES (51,'MARNE');
INSERT INTO public.dept (id,nom) VALUES (52,'HAUTE_MARNE');
INSERT INTO public.dept (id,nom) VALUES (53,'MAYENNE');
INSERT INTO public.dept (id,nom) VALUES (54,'MEURTHE_ET_MOSELLE');
INSERT INTO public.dept (id,nom) VALUES (55,'MEUSE');
INSERT INTO public.dept (id,nom) VALUES (56,'MORBIHAN');
INSERT INTO public.dept (id,nom) VALUES (57,'MOSELLE');
INSERT INTO public.dept (id,nom) VALUES (58,'NIEVRE');
INSERT INTO public.dept (id,nom) VALUES (59,'NORD');
INSERT INTO public.dept (id,nom) VALUES (60,'OISE');
INSERT INTO public.dept (id,nom) VALUES (61,'ORNE');
INSERT INTO public.dept (id,nom) VALUES (62,'PAS_DE_CALAIS');
INSERT INTO public.dept (id,nom) VALUES (63,'PUY_DE_DOME');
INSERT INTO public.dept (id,nom) VALUES (64,'PYRENEES_ATLANTIQUE');
INSERT INTO public.dept (id,nom) VALUES (65,'HAUTES_PYRENEES');
INSERT INTO public.dept (id,nom) VALUES (66,'PYRENNES_ORIENTALES');
INSERT INTO public.dept (id,nom) VALUES (67,'BAS_RHIN');
INSERT INTO public.dept (id,nom) VALUES (68,'HAUT_RHIN');
INSERT INTO public.dept (id,nom) VALUES (69,'RHONE');
INSERT INTO public.dept (id,nom) VALUES (70,'HAUTE_SAONE');
INSERT INTO public.dept (id,nom) VALUES (71,'SAONE_ET_LOIRE');
INSERT INTO public.dept (id,nom) VALUES (72,'SARTHE');
INSERT INTO public.dept (id,nom) VALUES (73,'SAVOIE');
INSERT INTO public.dept (id,nom) VALUES (74,'HAUTE_SAVOIE');
INSERT INTO public.dept (id,nom) VALUES (75,'PARIS');
INSERT INTO public.dept (id,nom) VALUES (76,'SEINE_MARITIME');
INSERT INTO public.dept (id,nom) VALUES (77,'SEINE_ET_MARNE');
INSERT INTO public.dept (id,nom) VALUES (78,'YVELINES');
INSERT INTO public.dept (id,nom) VALUES (79,'DEUX_SEVRES');
INSERT INTO public.dept (id,nom) VALUES (80,'SOMME');
INSERT INTO public.dept (id,nom) VALUES (81,'TARN');
INSERT INTO public.dept (id,nom) VALUES (82,'TARN_ET_GARONNE');
INSERT INTO public.dept (id,nom) VALUES (83,'VAR');
INSERT INTO public.dept (id,nom) VALUES (84,'VAUCLUSE');
INSERT INTO public.dept (id,nom) VALUES (85,'VENDEE');
INSERT INTO public.dept (id,nom) VALUES (86,'VIENNE');
INSERT INTO public.dept (id,nom) VALUES (87,'HAUTE_VIENNE');
INSERT INTO public.dept (id,nom) VALUES (88,'VOSGES');
INSERT INTO public.dept (id,nom) VALUES (89,'YONNE');
INSERT INTO public.dept (id,nom) VALUES (90,'TERRITOIRE_DE_BELFORT');
INSERT INTO public.dept (id,nom) VALUES (91,'ESSONNE');
INSERT INTO public.dept (id,nom) VALUES (92,'HAUTS_DE_SEINE');
INSERT INTO public.dept (id,nom) VALUES (93,'SEINES_ST_DENIS');
INSERT INTO public.dept (id,nom) VALUES (94,'VAL_DE_MARNE');
INSERT INTO public.dept (id,nom) VALUES (95,'VAL_D_OISE');


INSERT INTO public.compte_utilisateur (nom,prenom,pseudo,email,mdp,acces) VALUES ('Gallian','Laure','petitChat','lauregallian@gmail.com','test01','ROLE_USER');
INSERT INTO public.compte_utilisateur (nom,prenom,pseudo,email,mdp,acces) VALUES ('Plaxine','Philippe','admin','philippe.plaxine02@gmail.com','test02','ROLE_ADMIN');
INSERT INTO public.compte_utilisateur (nom,prenom,pseudo,email,mdp,acces) VALUES ('Millaya','Wouf','Chien','Millaya@gmail.com','test03','ROLE_USER');


INSERT INTO public.cotation (id,note) VALUES (1,'COTATION_1');
INSERT INTO public.cotation (id,note) VALUES (2,'COTATION_2');
INSERT INTO public.cotation (id,note) VALUES (3,'COTATION_3');
INSERT INTO public.cotation (id,note) VALUES (4,'COTATION_4');
INSERT INTO public.cotation (id,note) VALUES (5,'COTATION_5A');
INSERT INTO public.cotation (id,note) VALUES (6,'COTATION_5B');
INSERT INTO public.cotation (id,note) VALUES (7,'COTATION_5C');
INSERT INTO public.cotation (id,note) VALUES (8,'COTATION_6A');
INSERT INTO public.cotation (id,note) VALUES (9,'COTATION_6A_PLUS');
INSERT INTO public.cotation (id,note) VALUES (10,'COTATION_6B');
INSERT INTO public.cotation (id,note) VALUES (11,'COTATION_6B_PLUS');
INSERT INTO public.cotation (id,note) VALUES (12,'COTATION_6C');
INSERT INTO public.cotation (id,note) VALUES (13,'COTATION_6C_PLUS');
INSERT INTO public.cotation (id,note) VALUES (14,'COTATION_7A');
INSERT INTO public.cotation (id,note) VALUES (15,'COTATION_7A_PLUS');
INSERT INTO public.cotation (id,note) VALUES (16,'COTATION_7B');
INSERT INTO public.cotation (id,note) VALUES (17,'COTATION_7B_PLUS');
INSERT INTO public.cotation (id,note) VALUES (18,'COTATION_7C');
INSERT INTO public.cotation (id,note) VALUES (19,'COTATION_7C_PLUS');
INSERT INTO public.cotation (id,note) VALUES (20,'COTATION_8A');
INSERT INTO public.cotation (id,note) VALUES (21,'COTATION_8A_PLUS');
INSERT INTO public.cotation (id,note) VALUES (22,'COTATION_8B');
INSERT INTO public.cotation (id,note) VALUES (23,'COTATION_8B_PLUS');
INSERT INTO public.cotation (id,note) VALUES (24,'COTATION_8C');
INSERT INTO public.cotation (id,note) VALUES (25,'COTATION_8C_PLUS');
INSERT INTO public.cotation (id,note) VALUES (26,'COTATION_9A');
INSERT INTO public.cotation (id,note) VALUES (27,'COTATION_9A_PLUS');
INSERT INTO public.cotation (id,note) VALUES (28,'COTATION_9B');
INSERT INTO public.cotation (id,note) VALUES (29,'COTATION_9B_PLUS');
INSERT INTO public.cotation (id,note) VALUES (30,'COTATION_9C');


INSERT INTO public.topo (nom,presentation, createur_id ) VALUES ('Milles est une dune','Site superbe au milieu des dunes', 1);
INSERT INTO public.topo (nom,presentation, createur_id) VALUES (E'Escal\'add','Grimpez des panneaux publicitaires en plein Paris!',3);

INSERT INTO public.location_topo (date_debut_location, date_fin_location, topo_id, emprunteur_id, accepte) VALUES ('2018-05-14 07:00:00','2018-05-14 18:30:00', 1, 3, true);
INSERT INTO public.location_topo (date_debut_location, date_fin_location, topo_id, emprunteur_id, accepte) VALUES ('2018-12-25 07:00:00','2018-12-25 18:30:00', 1, 2, true);
INSERT INTO public.location_topo (date_debut_location, date_fin_location, topo_id, emprunteur_id, accepte) VALUES ('2018-12-23 07:00:00','2018-12-23 18:30:00', 1, 3, true);
INSERT INTO public.location_topo (date_debut_location, date_fin_location, topo_id, emprunteur_id, accepte) VALUES ('2018-12-17 07:00:00','2018-12-17 18:30:00', 1, 2, true);
INSERT INTO public.location_topo (date_debut_location, date_fin_location, topo_id, emprunteur_id, accepte) VALUES ('2018-12-06 07:00:00','2018-12-06 18:30:00', 1, 2, true);
INSERT INTO public.location_topo (date_debut_location, date_fin_location, topo_id, emprunteur_id, accepte) VALUES ('2018-12-19 07:00:00','2018-12-19 18:30:00', 1, 3, true);
INSERT INTO public.location_topo (date_debut_location, date_fin_location, topo_id, emprunteur_id, accepte) VALUES ('2018-12-22 07:00:00','2018-12-01 18:30:00', 1, 3, true);

INSERT INTO public.site (nom,lieu,date_creation,dept_id,compte_utilisateur_id,topo_id) VALUES ('Auban','A gauche de la forêt des pins','2018-03-01 00:00:00',2,2,1);
INSERT INTO public.site (nom,lieu,date_creation,dept_id,compte_utilisateur_id,topo_id) VALUES ('Argent','Après la ferme des 3 cochons','2018-03-01 18:17:15',5,1,2);

INSERT INTO public.secteur (nom,site_id) VALUES ('Zone51',1);
INSERT INTO public.secteur (nom,site_id) VALUES ('',2);
INSERT INTO public.secteur (nom,site_id) VALUES ('Zone Interdite',1);
INSERT INTO public.secteur (nom,site_id) VALUES (E'Couche d\'eau zone',1);

INSERT INTO public.voie (nom,hauteur,nombre_points,nombre_longueurs,cotation_id,secteur_id) VALUES ('Blop',11.27,'18','3',3,1);
INSERT INTO public.voie (nom,hauteur,nombre_points,nombre_longueurs,cotation_id,secteur_id) VALUES ('Blip',18.27,'23','4',4,2);
INSERT INTO public.voie (nom,hauteur,nombre_points,nombre_longueurs,cotation_id,secteur_id) VALUES ('Blap',17.46,'14','2',2,1);

SELECT * FROM public.secteur where site_id=(SELECT public.site.id FROM public.site WHERE site.nom='Argent');

SELECT public.site.id FROM public.site WHERE site.nom='Auban';

INSERT INTO public.commentaire (date_creation,contenu,site_id,compte_utilisateur_id) VALUES ('2018-02-17 06:09:18',E'Je n\'ai pas vraiment aimé ce site, je l\'ai trouvé vraiment difficile',1,2); 
INSERT INTO public.commentaire (date_creation,contenu,compte_utilisateur_id) VALUES ('2018-03-17 06:18:18',E'As-tu utilisé le bon matériel ?',1); 
INSERT INTO public.commentaire (date_creation,contenu,compte_utilisateur_id) VALUES ('2018-04-17 06:18:18',E'Oui Laure a raison, un bon matériel est essentiel',3); 

INSERT INTO public.commentaire_associe (commentaire_id, commentaire_associe_id) VALUES (1,2);
INSERT INTO public.commentaire_associe (commentaire_id, commentaire_associe_id) VALUES (1,3);



COMMIT;