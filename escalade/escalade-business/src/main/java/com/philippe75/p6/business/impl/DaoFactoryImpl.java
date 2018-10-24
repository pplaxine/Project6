package com.philippe75.p6.business.impl;

import com.philippe75.p6.business.contract.DaoFactory;
import com.philippe75.p6.consumer.contract.dao.TestDao;

public class DaoFactoryImpl implements DaoFactory{

		private TestDao td;
	
	@Override
	public TestDao getTestDao() {
		return td;
	}

	@Override
	public void setTestDao(TestDao td) {
		this.td = td; 
	}

}
