package com.philippe75.p6.business.impl.manager;

import com.philippe75.p6.business.contract.DaoFactory;

public abstract class AbstractManager {
	DaoFactory daoFactory;

	protected DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
}
