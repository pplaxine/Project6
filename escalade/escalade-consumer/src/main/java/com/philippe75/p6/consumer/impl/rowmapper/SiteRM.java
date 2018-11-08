package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class SiteRM implements RowMapper<Site> {
	
	@Override	
	public Site mapRow(ResultSet rs, int rn) throws SQLException {		
		Site site = new Site(); 		 
		site.setNom(rs.getString("nom"));

		
		return site;		// l'ojet a maitenant ses 2 att et devient un bean singulier 
	}
}