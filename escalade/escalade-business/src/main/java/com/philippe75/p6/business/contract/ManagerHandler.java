package com.philippe75.p6.business.contract;

import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;

public interface ManagerHandler {
	
	CompteUtilisateurManager getCompteUtilisateurManager();
	void setCompteUtilisateurManager(CompteUtilisateurManager compteUtilisateurManager);
}
