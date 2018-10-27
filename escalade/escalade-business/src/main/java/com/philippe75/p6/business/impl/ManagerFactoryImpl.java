package com.philippe75.p6.business.impl;

import com.philippe75.p6.business.contract.ManagerFactory;
import com.philippe75.p6.business.contract.impl.TestManager;
import com.philippe75.p6.business.impl.manager.TestManagerImpl;


public class ManagerFactoryImpl implements ManagerFactory {


	//private TestManager testManager; 
	TestManager testManager = new TestManagerImpl();
	
	@Override
	public TestManager getTestManager() {
		return testManager;
	}

	@Override
	public void setTestManager(TestManager testManager) {
		this.testManager = testManager; 
	}

}
