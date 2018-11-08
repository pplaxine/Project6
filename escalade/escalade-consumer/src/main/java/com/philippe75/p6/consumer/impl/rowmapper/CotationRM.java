package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class CotationRM implements RowMapper<Cotation> {
	
	@Override	
	public Cotation mapRow(ResultSet rs, int rn) throws SQLException {		
		
		Cotation cotation = Cotation.COTATION_1;
		
		return cotation;		
	}
}