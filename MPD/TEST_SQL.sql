INSERT INTO public.dept (id,nom) VALUES (1,'Ain');
INSERT INTO public.dept (id,nom) VALUES (2,'Aines');
INSERT INTO public.dept (id,nom) VALUES (3,'Allier');
INSERT INTO public.dept (id,nom) VALUES (4,'Alpes-de-Haute-Provence');
INSERT INTO public.dept (id,nom) VALUES (5,'Hautes-alpes');


INSERT INTO public.compte_utilisateur (nom,prenom,pseudo,email,mdp,acces) VALUES ('Gallian','Laure','petitChat','lauregallian@gmail.com','test01','7');

INSERT INTO public.compte_utilisateur (nom,prenom,pseudo,email,mdp,acces) VALUES ('Plaxine','Philippe','mdp','philippe.plaxine02@gmail.com','test02','99');
INSERT INTO public.compte_utilisateur (nom,prenom,pseudo,email,mdp,acces) VALUES ('Millaya','Wouf','chien','Millaya@gmail.com','test03','7');

INSERT INTO public.cotation (id,note) VALUES (1,'1');
INSERT INTO public.cotation (id,note) VALUES (2,'2');
INSERT INTO public.cotation (id,note) VALUES (3,'3');
INSERT INTO public.cotation (id,note) VALUES (4,'4');
INSERT INTO public.cotation (id,note) VALUES (5,'5A');
INSERT INTO public.cotation (id,note) VALUES (6,'5B');
INSERT INTO public.cotation (id,note) VALUES (7,'5C');
INSERT INTO public.cotation (id,note) VALUES (8,'6A');
INSERT INTO public.cotation (id,note) VALUES (9,'6A+');
INSERT INTO public.cotation (id,note) VALUES (10,'6B');
INSERT INTO public.cotation (id,note) VALUES (11,'6B+');
INSERT INTO public.cotation (id,note) VALUES (12,'6C');
INSERT INTO public.cotation (id,note) VALUES (13,'6C+');


INSERT INTO public.site (nom,lieu,date_creation,dept_id,compte_utilisateur_id) VALUES ('Auban','A gauche de la forêt des pins','2018-03-01 00:00:00',2,2);
INSERT INTO public.site (nom,lieu,date_creation,dept_id,compte_utilisateur_id) VALUES ('Argent','Après la ferme des 3 cochons','2018-03-01 18:17:15',5,1);

INSERT INTO public.secteur (nom,site_id) VALUES ('Zone51',3);
INSERT INTO public.secteur (nom,site_id) VALUES ('',2);
INSERT INTO public.secteur (nom,site_id) VALUES ('Zone Interdite',3);

INSERT INTO public.voie (nom,hauteur,nombre_points,nombre_longueurs,cotation_id,secteur_id) VALUES ('Blop',11.27,'18','3',1,1);
INSERT INTO public.voie (nom,hauteur,nombre_points,nombre_longueurs,cotation_id,secteur_id) VALUES ('Blip',18.27,'23','4',1,2);

SELECT * FROM public.secteur where site_id=(SELECT public.site.id FROM public.site WHERE site.nom='Argent');

SELECT public.site.id FROM public.site WHERE site.nom='Auban';

INSERT INTO public.commentaire (date_creation,contenu,site_id,compte_utilisateur_id) VALUES ('2018-10-31 06:09:18',E'Je n\'ai pas vraiment aimé ce site, je l\'ai trouvé vraiment difficile',3,2); 
INSERT INTO public.commentaire (date_creation,contenu,site_id,compte_utilisateur_id) VALUES ('2018-10-31 06:18:18',E'As-tu utilisé le bon matériel ?',3,1); 
INSERT INTO public.commentaire (date_creation,contenu,site_id,compte_utilisateur_id) VALUES ('2018-10-31 06:18:18',E'Oui Laure a raison, un bon matériel est essentiel',3,3); 

INSERT INTO public.commentaire_associe (commentaire_id, commentaire_associe_id) VALUES (1,2);
INSERT INTO public.commentaire_associe (commentaire_id, commentaire_associe_id) VALUES (1,3);

INSERT INTO public.topo (nom,disponible,date_retour,preteur_id,emprunteur_id) VALUES ('Milles est une dune', FALSE,'2018-11-03 18:00:00',1,3);
INSERT INTO public.topo (nom,disponible,preteur_id) VALUES (E'Escale\'add', TRUE,1);