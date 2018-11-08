package com.philippe75.p6.consumer.contract.dao;

import com.philippe75.p6.model.bean.site.Site;

public interface SiteDao {
	
	Site findSite(String nom);

}
