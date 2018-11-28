package com.philippe75.p6.consumer.contract.dao;

import java.util.List;

import com.philippe75.p6.model.bean.commentaire.Commentaire;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public interface CommentaireDao {

	int createCommentaire(Commentaire commentaire);
	
	Commentaire findCommentaire(int commentaire_id);
	
	List<Integer> findCommentaireAssociesId(int commentaire_id);
	
	List<Commentaire> findAllCommentairesForSite(int site_id);
	
	List<Commentaire> getThreelastCommentaire(); 
}
