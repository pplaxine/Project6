package com.philippe75.p6.business.contract.impl;

import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public interface CompteUtilisateurManager {
	int getCountCompteUtilisateur();
	
	CompteUtilisateur findCompteUtilisteurByUserPseudo(String pseudo);
}
