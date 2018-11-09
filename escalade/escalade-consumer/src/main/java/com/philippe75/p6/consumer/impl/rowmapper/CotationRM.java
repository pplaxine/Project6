package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.site.Cotation;

public class CotationRM implements RowMapper<Cotation> {
	
	@Override	
	public Cotation mapRow(ResultSet rs, int rn) throws SQLException {		
		
		Cotation cotation = Cotation.valueOf(rs.getString("note"));
		
		
		//String str = cotation.name();	<-- memo pour les entrées en bdd 
		
		return cotation;		
	}
}