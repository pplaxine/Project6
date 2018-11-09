package com.philippe75.p6.business.contract;

import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;
import com.philippe75.p6.business.contract.impl.SiteManager;

public interface ManagerHandler {
	
	CompteUtilisateurManager getCompteUtilisateurManager();
	
	SiteManager getSiteManager();
}
