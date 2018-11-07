package com.philippe75.p6.business.contract.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public interface CompteUtilisateurManager {
	int getCountCompteUtilisateur();
	
	CompteUtilisateur findCompteUtilisteurByUserPseudo(String pseudo);
	
	CompteUtilisateur creerNouveauCompte(HttpServletRequest request);
	
	public Map<String, String> getErreurs();

	public String getResult();
}
