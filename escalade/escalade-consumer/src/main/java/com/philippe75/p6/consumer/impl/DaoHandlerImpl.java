package com.philippe75.p6.consumer.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.p6.consumer.contract.DaoHandler;
import com.philippe75.p6.consumer.contract.dao.CompteUtilisateurDao;

@Named("daoHandler")
public class DaoHandlerImpl implements DaoHandler{
	
	@Inject
	private CompteUtilisateurDao compteUtilisateurDao;

	@Override
	public CompteUtilisateurDao getCompteUtilisateurDao() {
		return compteUtilisateurDao;
	}

	@Override
	public void setCompteUtilisateurDao(CompteUtilisateurDao compteUtilisateurDao) {
		this.compteUtilisateurDao = compteUtilisateurDao;
	}
	
}
