package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.topo.Topo;

public class TopoRM implements RowMapper<Topo> {
	
	@Override	
	public Topo mapRow(ResultSet rs, int rn) throws SQLException {		
		Topo topo = new Topo(); 		 
		
		topo.setId(rs.getInt("id"));
		topo.setNom(rs.getString("nom"));
		topo.setPresentation(rs.getString("presentation"));
		topo.setCreateur_id(rs.getInt("createur_id"));
		topo.setCreateur(rs.getString("pseudo"));
		return topo;
	}
}