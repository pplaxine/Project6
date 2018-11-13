package com.philippe75.p6.business.contract;

import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;
import com.philippe75.p6.business.contract.impl.CotationManager;
import com.philippe75.p6.business.contract.impl.DeptManager;
import com.philippe75.p6.business.contract.impl.SecteurManager;
import com.philippe75.p6.business.contract.impl.SiteManager;
import com.philippe75.p6.business.contract.impl.VoieManager;

public interface ManagerHandler {
	
	CompteUtilisateurManager getCompteUtilisateurManager();
	
	SiteManager getSiteManager();
	
	DeptManager getDeptManager();
	
	CotationManager getCotationManager();
	
	SecteurManager getSecteurManager();
	
	VoieManager getVoieManager();
}
