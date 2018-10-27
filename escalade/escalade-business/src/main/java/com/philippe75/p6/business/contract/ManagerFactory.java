package com.philippe75.p6.business.contract;

import com.philippe75.p6.business.contract.impl.TestManager;

public interface ManagerFactory {
	
	TestManager getTestManager();
	
	void setTestManager(TestManager testManager);
}
