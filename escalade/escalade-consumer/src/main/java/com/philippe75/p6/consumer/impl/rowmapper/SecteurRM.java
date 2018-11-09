package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.consumer.contract.DaoHandler;
import com.philippe75.p6.consumer.impl.DaoHandlerImpl;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class SecteurRM implements RowMapper<Secteur>{
	
	DaoHandler dh = new DaoHandlerImpl();
	
	@Override	
	public Secteur mapRow(ResultSet rs, int rn) throws SQLException {		
		
		Secteur secteur = new Secteur(); 
		
		secteur.setNom(rs.getString("nom"));
		secteur.setId(rs.getInt("id"));
		
		return secteur;
	}
}