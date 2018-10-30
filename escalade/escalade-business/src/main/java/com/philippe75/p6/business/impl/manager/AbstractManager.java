package com.philippe75.p6.business.impl.manager;

import javax.inject.Inject;

import com.philippe75.p6.business.contract.DaoFactory;


public abstract class AbstractManager {
	
	@Inject
	private DaoFactory daoFactory;
	

	protected DaoFactory getDaoFactory() {
		return daoFactory;
	}
	
	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
}
