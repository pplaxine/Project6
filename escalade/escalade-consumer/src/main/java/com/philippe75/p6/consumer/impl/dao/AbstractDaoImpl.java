package com.philippe75.p6.consumer.impl.dao;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.philippe75.p6.consumer.contract.DaoHandler;


public abstract class AbstractDaoImpl {
	
	@Inject
	private DaoHandler daoHandler; 
	
	private static DataSource dataSource;
	
	public DaoHandler getDaoHandler() {
		return daoHandler;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public static void setDataSource(DataSource dataSource) {
		AbstractDaoImpl.dataSource = dataSource;
	}

}
