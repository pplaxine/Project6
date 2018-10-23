package com.philippe75.p6.business.contract.impl;

import java.util.List;

import com.philippe75.p6.model.beans.Test;

public interface TestManager {
	
	Test getTest(Integer id);
	
	List<Test> getAllTest();
}


