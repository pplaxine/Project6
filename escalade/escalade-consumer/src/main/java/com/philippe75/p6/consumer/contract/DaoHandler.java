package com.philippe75.p6.consumer.contract;

import com.philippe75.p6.consumer.contract.dao.CompteUtilisateurDao;

public interface DaoHandler {
	
	CompteUtilisateurDao getCompteUtilisateurDao();
	void setCompteUtilisateurDao(CompteUtilisateurDao compteUtilisateurDao);

	
}
