package com.philippe75.p6.business.contract.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public interface VoieManager {
	
	Voie creerNouvelleVoie(HttpServletRequest request);
	
	Map<String, String> getErreurs();
	String getResult();
	
	
}
