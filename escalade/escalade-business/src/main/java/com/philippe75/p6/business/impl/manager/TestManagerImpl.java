package com.philippe75.p6.business.impl.manager;

import java.util.List;

import com.philippe75.p6.business.contract.impl.TestManager;
import com.philippe75.p6.model.beans.Test;


public class TestManagerImpl extends AbstractManager implements TestManager{

	@Override
	public int getCountTest() {
		
		int nbre = getDaoFactory().getTestDao().getCountTest();
		return nbre;
	}

	
	@Override
	public List<Test> getAllTest() {
		List<Test> listTest = getDaoFactory().getTestDao().getAllTest();
		return listTest;
	}


}
