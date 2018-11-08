package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class VoieRM implements RowMapper<Voie> {
	
	@Override	
	public Voie mapRow(ResultSet rs, int rn) throws SQLException {		
		
		Voie voie = new Voie(); 
		
		voie.setNom(rs.getString("nom"));
		voie.setHauteur(rs.getLong("hauteur"));
		voie.setNombrePoints(rs.getInt("nombre_points"));
		voie.setNombreLongueur(rs.getInt("nombre_longueurs"));
		voie.setCotation( Cotation.COTATION_1);
		
		return voie;		// l'ojet a maitenant ses 2 att et devient un bean singulier 
	}
}