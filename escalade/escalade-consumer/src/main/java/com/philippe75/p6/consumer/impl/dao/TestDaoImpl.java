package com.philippe75.p6.consumer.impl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.philippe75.p6.consumer.contract.dao.TestDao;
import com.philippe75.p6.model.beans.Test;


public class TestDaoImpl extends AbstractDaoImpl implements TestDao{

	@Override
	public int getCountTest() {
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		int nbreTest = jT.queryForObject("SELECT COUNT(*) FROM public.test", Integer.class);
		
		return nbreTest;
	}
	
	
	@Override
	public List<Test> getAllTest() {
		String SQL = "SELECT * FROM public.test";
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		RowMapper<Test> rm = new RowMapper<Test>() {
			@Override
			public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
				Test test = new Test(rs.getInt("id"));
				test.setPrenom(rs.getString("prenom"));
				test.setNom(rs.getString("nom"));
				return test;
			}
		};
		
		List<Test> listTest = jT.query(SQL, rm);
		
		return listTest;
	}





}
