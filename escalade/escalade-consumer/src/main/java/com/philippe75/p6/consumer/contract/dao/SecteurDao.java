package com.philippe75.p6.consumer.contract.dao;

import java.util.List;

import com.philippe75.p6.model.bean.site.Secteur;

public interface SecteurDao {
	
	Secteur findSecteur(int id); 
	
	List<Secteur> listSecteur(int site_id);
}
