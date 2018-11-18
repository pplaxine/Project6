package com.philippe75.p6.business.contract.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.model.bean.commentaire.Commentaire;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public interface CommentaireManager {
	
	List<Commentaire> findAllCommentairesForSite(int site_id);
	
	Commentaire creerNouveauCommentaire(HttpServletRequest request, int site_id);
	
	Map<String, String> getErreurs();

	String getResult();
}
