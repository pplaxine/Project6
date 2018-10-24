package com.philippe75.p6.consumer.impl.dao;

import javax.sql.DataSource;

public abstract class AbstractDaoImpl {

	private static DataSource dataSource;
	
	protected static DataSource getDataSource() {
		return dataSource;
	}
	
	public static void setDataSource(DataSource dataSource) {
		AbstractDaoImpl.dataSource = dataSource;
	}
}
