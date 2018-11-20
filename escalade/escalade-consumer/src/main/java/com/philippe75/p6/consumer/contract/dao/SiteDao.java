package com.philippe75.p6.consumer.contract.dao;

import java.util.List;

import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Site;

public interface SiteDao {
	
	Site findSite(int id);
	
	int getSiteId(String nom);
	
	List<Site> listAllSite();
	
	public List<Site> listAllSiteWithCriteria(String dept, int nombreSecteur, int nombreVoie, boolean topoDisponible);
	
	int saveSite(Site site);

}
