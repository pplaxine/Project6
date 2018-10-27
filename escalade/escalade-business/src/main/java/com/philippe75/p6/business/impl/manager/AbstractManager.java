package com.philippe75.p6.business.impl.manager;

import com.philippe75.p6.business.contract.DaoFactory;
import com.philippe75.p6.business.impl.DaoFactoryImpl;

public abstract class AbstractManager {
	
	
	//private static DaoFactory daoFactory;
	private static DaoFactory daoFactory = new DaoFactoryImpl();

	protected static DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public static void setDaoFactory(DaoFactory daoFactory) {
		AbstractManager.daoFactory = daoFactory;
	}
	
	
}
