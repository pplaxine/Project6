package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.commentaire.Commentaire;
import com.philippe75.p6.model.bean.site.Cotation;

public class CommentaireAssocieRM implements RowMapper<Integer> {
	
	@Override	
	public Integer mapRow(ResultSet rs, int rn) throws SQLException {		
		return rs.getInt("commentaire_associe_id");
	}
}