package com.philippe75.p6.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.p6.business.contract.DaoFactory;
import com.philippe75.p6.consumer.contract.dao.TestDao;

@Named("daoFactory")
public class DaoFactoryImpl implements DaoFactory{

	@Inject
	private TestDao testDao;


	@Override
	public TestDao getTestDao() {
		return testDao;
	}

	@Override
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao; 
	}

}
