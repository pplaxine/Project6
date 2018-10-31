package com.philippe75.p6.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.p6.business.contract.DaoFactory;
import com.philippe75.p6.consumer.contract.dao.CompteUtilisateurDao;
import com.philippe75.p6.consumer.contract.dao.TestDao;

@Named("daoFactory")
public class DaoFactoryImpl implements DaoFactory{

	@Inject
	private TestDao testDao;
	
	@Inject
	private CompteUtilisateurDao compteUtilisateurDao;


	@Override
	public TestDao getTestDao() {
		return testDao;
	}

	@Override
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao; 
	}

	@Override
	public CompteUtilisateurDao getCompteUtilisateurDao() {
		return compteUtilisateurDao;
	}

	@Override
	public void setCompteUtilisateurDao(CompteUtilisateurDao compteUtilisateurDao) {
		this.compteUtilisateurDao = compteUtilisateurDao;
	}
	
}
