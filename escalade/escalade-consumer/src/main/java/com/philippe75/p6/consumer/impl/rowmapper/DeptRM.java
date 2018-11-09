package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.site.Dept;

public class DeptRM implements RowMapper<Dept> {
	
	@Override	
	public Dept mapRow(ResultSet rs, int rn) throws SQLException {		
		
		Dept dept = Dept.valueOf(rs.getString("nom"));
		
		return dept;		
	}
}