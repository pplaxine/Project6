package com.philippe75.p6.business.impl.manager;

import javax.inject.Named;

import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;

@Named("CompteUtilisateurManager")
public class CompteUtilisateurManagerImpl extends AbstractManager implements CompteUtilisateurManager{

	@Override
	public int getCountCompteUtilisateur() {
		
		return getDaoFactory().getCompteUtilisateurDao().getCountCompteUtilisateur();
	}

}
