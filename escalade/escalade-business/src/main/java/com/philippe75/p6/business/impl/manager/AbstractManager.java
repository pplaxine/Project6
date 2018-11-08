package com.philippe75.p6.business.impl.manager;

import javax.inject.Inject;

import com.philippe75.p6.consumer.contract.DaoHandler;


public abstract class AbstractManager {
	
	@Inject
	private DaoHandler daoHandler;

	protected DaoHandler getDaoHandler() {
		return daoHandler;
	}
	
	public void setDaoHandler(DaoHandler daoHandler) {
		this.daoHandler = daoHandler;
	}
	
	
}
