package com.philippe75.env.beans;

import javax.sql.DataSource;

public abstract class AbstractConnector {

	
	private static DataSource dataSource;

	protected static DataSource getDataSource() {
		return dataSource;
	}

	public static void setDataSource(DataSource dataSource) {
		AbstractConnector.dataSource = dataSource;
	}
	
	
	
}
