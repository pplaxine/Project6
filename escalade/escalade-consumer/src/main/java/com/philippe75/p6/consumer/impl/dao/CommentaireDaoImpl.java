package com.philippe75.p6.consumer.impl.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.sql.rowset.RowSetWarning;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.philippe75.p6.consumer.contract.dao.CommentaireDao;
import com.philippe75.p6.consumer.impl.rowmapper.CommentaireAssocieRM;
import com.philippe75.p6.consumer.impl.rowmapper.CommentaireRM;
import com.philippe75.p6.model.bean.commentaire.Commentaire;

@Named("commentaireDao")
public class CommentaireDaoImpl extends AbstractDaoImpl implements CommentaireDao{

	
	@Override
	public Commentaire findCommentaire(int commentaire_id) {
		
		String sQL = 	"SELECT commentaire.id, commentaire.date_creation, commentaire.contenu, commentaire.site_id, compte_utilisateur.pseudo FROM compte_utilisateur "
					  + "JOIN commentaire ON compte_utilisateur.id = commentaire.compte_utilisateur_id "
					  + "WHERE commentaire.id = :commentaire_id";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();

		if(Integer.toString(commentaire_id) != null) {
			mSPS.addValue("commentaire_id", commentaire_id);
		}
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		RowMapper<Commentaire> rm = new CommentaireRM();
		
		Commentaire commentaire = nPJT.queryForObject(sQL, mSPS, rm);
		
		//-----------------------------------------------------------------------
		
		List<Commentaire> commentairesList = new ArrayList<>();
		
		for (Integer commentaireAssociesId : findCommentaireAssociesId(commentaire.getId())) { //// find related message 
			commentairesList.add(findCommentaire(commentaireAssociesId)); ///// pour chaque commentaire liée, tu trouve ce commentaire (avec sa list com liés ) 
		}
		
		commentaire.setCommentaires(commentairesList);
		
		return commentaire;
	
	}

	@Override
	public List<Integer> findCommentaireAssociesId(int commentaire_id) {
		
		String sQL = 	"SELECT commentaire_associe_id FROM commentaire_associe WHERE commentaire_id = :commentaire_id";
	
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		if(Integer.toString(commentaire_id) != null) {
			mSPS.addValue("commentaire_id", commentaire_id);
		}
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		RowMapper<Integer> rm = new CommentaireAssocieRM();
		
		return nPJT.query(sQL, mSPS, rm);
	
	}

	@Override
	public List<Commentaire> findAllCommentairesForSite(int site_id) {
		
		return null;
	}	
	
	
	

}
