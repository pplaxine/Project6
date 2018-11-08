package com.philippe75.p6.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.p6.business.contract.ManagerHandler;
import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;

@Named("managerHandler")
public class ManagerHandlerImpl implements ManagerHandler {
	
	@Inject
	private CompteUtilisateurManager compteUtilisateurManager;

	
	@Override
	public CompteUtilisateurManager getCompteUtilisateurManager() {
		return compteUtilisateurManager;
	}

	@Override
	public void setCompteUtilisateurManager(CompteUtilisateurManager compteUtilisateurManager) {
		this.compteUtilisateurManager=compteUtilisateurManager;
	}

}
