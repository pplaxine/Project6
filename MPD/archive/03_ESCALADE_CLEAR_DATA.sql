BEGIN TRANSACTION;

--- ===== Purge des tables
DELETE FROM public.commentaire;
DELETE FROM public.commentaire_associe;
DELETE FROM public.compte_utilisateur;
DELETE FROM public.cotation;
DELETE FROM public.dept;
DELETE FROM public.secteur;
DELETE FROM public.secteur_topo;
DELETE FROM public.site;
DELETE FROM public.site_topo;
DELETE FROM public.topo;
DELETE FROM public.voie;


--- ===== Réinitialisation des séquences
SELECT setval('public.commentaire_id_seq', 1, false);
SELECT setval('public.compte_utilisateur_id_seq', 1, false);
SELECT setval('public.secteur_id_seq', 1, false);
SELECT setval('public.secteur_topo_id_seq', 1, false);
SELECT setval('public.site_id_seq', 1, false);
SELECT setval('public.site_topo_id_seq', 1, false); 
SELECT setval('public.topo_id_seq', 1, false); 
SELECT setval('public.voie_id_seq', 1, false); 

COMMIT;