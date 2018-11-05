package com.philippe75.p6.business.impl.manager;

import javax.inject.Named;

import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

@Named("CompteUtilisateurManager")
public class CompteUtilisateurManagerImpl extends AbstractManager implements CompteUtilisateurManager{

	@Override
	public int getCountCompteUtilisateur() {
		
		return getDaoFactory().getCompteUtilisateurDao().getCountCompteUtilisateur();
	}

	@Override
	public CompteUtilisateur findCompteUtilisteurByUserPseudo(String pseudo) {
		
		return getDaoFactory().getCompteUtilisateurDao().findCompteUtilisateur(pseudo);
	}

}
