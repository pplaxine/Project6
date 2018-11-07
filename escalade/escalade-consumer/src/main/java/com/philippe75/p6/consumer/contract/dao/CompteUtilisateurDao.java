package com.philippe75.p6.consumer.contract.dao;

import java.util.List;

import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public interface CompteUtilisateurDao {

	int getCountCompteUtilisateur();
	
	int createCompteUtilisateur(CompteUtilisateur compteUtilisateur);
	
	CompteUtilisateur findCompteUtilisateur(String userName);
	
	List<CompteUtilisateur> getAllCompteUtilisateur(); 
}
