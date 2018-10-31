package com.philippe75.p6.business.contract;

import com.philippe75.p6.consumer.contract.dao.CompteUtilisateurDao;
import com.philippe75.p6.consumer.contract.dao.TestDao;

public interface DaoFactory {

	TestDao getTestDao();
	void setTestDao(TestDao testDao);
	
	CompteUtilisateurDao getCompteUtilisateurDao();
	void setCompteUtilisateurDao(CompteUtilisateurDao compteUtilisateurDao);

	
}
