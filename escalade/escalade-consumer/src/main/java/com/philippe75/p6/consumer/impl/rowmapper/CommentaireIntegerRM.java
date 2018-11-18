package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CommentaireIntegerRM implements RowMapper<Integer> {
	
	@Override	
	public Integer mapRow(ResultSet rs, int rn) throws SQLException {		
		return rs.getInt("id");
	}
}