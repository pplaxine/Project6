package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class SiteRM implements RowMapper<Site> {
	
	@Override	
	public Site mapRow(ResultSet rs, int rn) throws SQLException {		
		Site site = new Site(); 		 
		
		site.setId(rs.getInt("id"));
		site.setNom(rs.getString("nom"));
		site.setLieu(rs.getString("lieu"));
		site.setDescription(rs.getString("description"));
		site.setDateCreation(rs.getDate("date_creation"));
		site.setTopo((rs.getInt("topo_id")!=0)?true:false);
		
		return site;
	}
}