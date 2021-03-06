package com.philippe75.p6.business.contract.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.model.bean.commentaire.Commentaire;

public interface CommentaireManager {
	
	List<Commentaire> findAllCommentairesForSite(int site_id);
	
	Commentaire creerNouveauCommentaire(HttpServletRequest request, int site_id);
	
	Map<String, String> getErreurs();

	String getResult();
	
	List<Commentaire> getThreelastCommentaire();
}
