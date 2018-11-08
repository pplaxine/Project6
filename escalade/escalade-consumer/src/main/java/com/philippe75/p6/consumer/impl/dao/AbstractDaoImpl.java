package com.philippe75.p6.consumer.impl.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractDaoImpl {
	
	private static DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public static void setDataSource(DataSource dataSource) {
		AbstractDaoImpl.dataSource = dataSource;
	}
}
