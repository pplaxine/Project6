package com.philippe75.p6.business.contract.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.model.bean.site.Secteur;

public interface SecteurManager {
	
	Secteur creerNouveauSecteur(HttpServletRequest request);
	
	Map<String, String> getErreurs();
	String getResult();
	
}
