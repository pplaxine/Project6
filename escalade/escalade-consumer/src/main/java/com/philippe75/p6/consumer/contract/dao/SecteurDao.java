package com.philippe75.p6.consumer.contract.dao;

import java.util.List;

import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Voie;

public interface SecteurDao {
	
	Secteur findSecteur(int id); 
	
	int getSecteurId(String nomSecteur, int site_id);
	
	List<Secteur> listSecteur(int site_id);
	
	int saveSecteur(Secteur secteur, int site_id);
}
