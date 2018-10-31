package com.philippe75.p6.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.p6.business.contract.ManagerFactory;
import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;
import com.philippe75.p6.business.contract.impl.TestManager;

@Named("managerFactory")
public class ManagerFactoryImpl implements ManagerFactory {

	@Inject
	private TestManager testManager;
	
	@Inject
	private CompteUtilisateurManager compteUtilisateurManager;

	
	@Override
	public TestManager getTestManager() {
		return testManager;
	}

	@Override
	public void setTestManager(TestManager testManager) {
		this.testManager = testManager; 
	}

	@Override
	public CompteUtilisateurManager getCompteUtilisateurManager() {
		return compteUtilisateurManager;
	}

	@Override
	public void setCompteUtilisateurManager(CompteUtilisateurManager compteUtilisateurManager) {
		this.compteUtilisateurManager=compteUtilisateurManager;
		
	}

}
