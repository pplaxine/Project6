package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.commentaire.Commentaire;
import com.philippe75.p6.model.bean.site.Cotation;

public class CommentaireRM implements RowMapper<Commentaire> {
	
	@Override	
	public Commentaire mapRow(ResultSet rs, int rn) throws SQLException {		
		
		Commentaire com = new Commentaire();
		
		com.setId(rs.getInt("id"));
		com.setDateCreation(rs.getTimestamp("date_creation"));
		com.setContenu(rs.getString("contenu"));
		com.setAuteur(rs.getString("pseudo"));
		
		return com;		
	}
}