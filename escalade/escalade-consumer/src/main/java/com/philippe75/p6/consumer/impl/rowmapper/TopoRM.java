package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.topo.Topo;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class TopoRM implements RowMapper<Topo> {
	
	@Override	
	public Topo mapRow(ResultSet rs, int rn) throws SQLException {		
		Topo topo = new Topo(); 		 
		
		topo.setId(rs.getInt("id"));
		topo.setNom(rs.getString("nom"));
		topo.setDisponible(rs.getBoolean("disponible"));
		topo.setPresentation(rs.getString("presentation"));
		topo.setDateDebutLocation(rs.getTimestamp("date_debut_location"));
		topo.setDateFinLocation(rs.getTimestamp("date_fin_location"));
		topo.setPreteur_id(rs.getInt("preteur_id"));
		topo.setEmprunteur_id(rs.getInt("emprunteur_id"));
		return topo;
	}
}