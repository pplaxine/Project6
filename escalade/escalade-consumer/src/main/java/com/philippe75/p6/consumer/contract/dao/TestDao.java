package com.philippe75.p6.consumer.contract.dao;

import java.util.List;

import com.philippe75.p6.model.beans.Test;

public interface TestDao {
	
	String getCountTest2();
	
	int getCountTest();
	
	List<Test> getAllTest();
}
