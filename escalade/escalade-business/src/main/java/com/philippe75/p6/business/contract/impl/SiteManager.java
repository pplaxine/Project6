package com.philippe75.p6.business.contract.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public interface SiteManager {
	
	Site creerNouveauSite(HttpServletRequest request);
	
	Integer saveSite(Site site);
	
	Site findSite(int id);
	
	List<Site> listAllSite ();
	
	Map<String, String> getErreurs();
	String getResult();
	
	Site getLastSiteAdded();
	
	
}
