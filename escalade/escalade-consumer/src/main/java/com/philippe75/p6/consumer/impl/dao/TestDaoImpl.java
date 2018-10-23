package com.philippe75.p6.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import com.philippe75.p6.consumer.contract.dao.TestDao;
import com.philippe75.p6.model.beans.Test;


public class TestDaoImpl extends AbstractDaoImpl implements TestDao{

	@Override
	public Test getTest(Integer id) {
		return null;
	}

	@Override
	public List<Test> getAllTest() {
		return null;
	}
		
}
