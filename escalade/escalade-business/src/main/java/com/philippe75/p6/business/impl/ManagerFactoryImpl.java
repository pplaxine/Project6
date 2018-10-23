package com.philippe75.p6.business.impl;

import com.philippe75.p6.business.contract.ManagerFactory;
import com.philippe75.p6.business.contract.impl.TestManager;


public class ManagerFactoryImpl implements ManagerFactory {


	private TestManager tm; 
	
	@Override
	public TestManager getTestManager() {
		return tm;
	}

	@Override
	public void setTestManager(TestManager tm) {
		this.tm = tm; 
	}

}
