package com.philippe75.p6.consumer.contract.dao;

import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public interface CompteUtilisateurDao {

	int getCountCompteUtilisateur();
	
	int createCompteUtilisateur(CompteUtilisateur compteUtilisateur);
	
	CompteUtilisateur findCompteUtilisateur(String userName);
}
