package com.philippe75.p6.consumer.impl.dao;

import javax.activation.DataSource;
import javax.inject.Inject;
import javax.inject.Named;

public abstract class AbstractDaoImpl {

	private DataSource dataSource;
	
	protected DataSource getDataSource() {
		return dataSource;
	}
}
