package com.philippe75.p6.business.impl;

import com.philippe75.p6.business.contract.DaoFactory;
import com.philippe75.p6.consumer.contract.dao.TestDao;
import com.philippe75.p6.consumer.impl.dao.TestDaoImpl;

public class DaoFactoryImpl implements DaoFactory{

		
		//private TestDao testDao;
		private TestDao testDao = new TestDaoImpl();
	
	@Override
	public TestDao getTestDao() {
		return testDao;
	}

	@Override
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao; 
	}

}
