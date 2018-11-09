package com.philippe75.p6.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.p6.business.contract.ManagerHandler;
import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;
import com.philippe75.p6.business.contract.impl.SiteManager;

@Named("managerHandler")
public class ManagerHandlerImpl implements ManagerHandler {
	
	@Inject
	private CompteUtilisateurManager compteUtilisateurManager;
	
	@Inject
	private SiteManager siteManager;

	
	@Override
	public CompteUtilisateurManager getCompteUtilisateurManager() {
		return compteUtilisateurManager;
	}


	public SiteManager getSiteManager() {
		return siteManager;
	}
	
	

}
