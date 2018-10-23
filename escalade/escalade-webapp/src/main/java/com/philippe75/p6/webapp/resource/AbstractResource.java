package com.philippe75.p6.webapp.resource;

import com.philippe75.p6.business.contract.ManagerFactory;

public abstract class AbstractResource {
	private static ManagerFactory managerFatory;
	private static DaoFactory daoFactory;
	
	
	protected static ManagerFactory getManagerFatory() {
		return managerFatory;
	}
	
	public static void setManagerFatory(ManagerFactory managerFatory) {
		AbstractResource.managerFatory = managerFatory;
	}
	
	protected static DaoFactory getDaoFactory() {
		return daoFactory;
	}
	
	public static void setDaoFactory(DaoFactory daoFactory) {
		AbstractResource.daoFactory = daoFactory;
	} 
	
	
}
