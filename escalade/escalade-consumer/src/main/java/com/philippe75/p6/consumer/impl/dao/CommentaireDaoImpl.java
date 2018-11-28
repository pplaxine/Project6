package com.philippe75.p6.consumer.impl.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.philippe75.p6.consumer.contract.dao.CommentaireDao;
import com.philippe75.p6.consumer.impl.rowmapper.CommentaireAssocieRM;
import com.philippe75.p6.consumer.impl.rowmapper.CommentaireIntegerRM;
import com.philippe75.p6.consumer.impl.rowmapper.CommentaireRM;
import com.philippe75.p6.model.bean.commentaire.Commentaire;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

@Named("commentaireDao")
public class CommentaireDaoImpl extends AbstractDaoImpl implements CommentaireDao{

	@Override
	public int createCommentaire(Commentaire commentaire) {
		
		String sQL = "INSERT INTO commentaire (contenu, date_creation, site_id, compte_utilisateur_id) VALUES (:contenu, :date_creation, :site_id, :compte_utilisateur_id); COMMIT";
		if(commentaire != null) {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userPseudo = auth.getName();
			CompteUtilisateur cu = getDaoHandler().getCompteUtilisateurDao().findCompteUtilisateur(userPseudo);

			
			MapSqlParameterSource mSPS = new MapSqlParameterSource();
			
			mSPS.addValue("contenu", commentaire.getContenu());
			mSPS.addValue("date_creation", new Date());
			mSPS.addValue("site_id", commentaire.getSite_id());	
			mSPS.addValue("compte_utilisateur_id", cu.getId());
			
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			
			try {
				int result = nPJT.update(sQL, mSPS);
				return result;
			} catch (DataAccessException e) {	
				e.printStackTrace();
				return 0;
			} 
		}
		return 0;
	}	
	
	
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
		
		try {
			Commentaire commentaire = nPJT.queryForObject(sQL, mSPS, rm);
			List<Commentaire> commentairesList = new ArrayList<>();
			
			for (Integer commentaireAssociesId : findCommentaireAssociesId(commentaire.getId())) { 
				commentairesList.add(findCommentaire(commentaireAssociesId)); 
			}
			
			commentaire.setCommentaires(commentairesList);
			
			return commentaire;
			
		} catch (DataAccessException e) {
			e.printStackTrace();;
			return null; 
		}
	}
	
	@Override
	public List<Commentaire> getThreelastCommentaire() {
		
		String sQL = 	"SELECT id FROM commentaire WHERE site_id IS NOT NULL ORDER BY ID DESC LIMIT 3";
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		RowMapper<Integer> rm = new CommentaireIntegerRM();
		try {
			List<Integer> listCommentaireId = nPJT.query(sQL,rm);
			
			List<Commentaire> listCommentaire = new ArrayList<>();
			for (Integer com_id : listCommentaireId) {
				Commentaire com = findCommentaire(com_id);
				Site site = getDaoHandler().getSiteDao().findSite(com.getSite_id());
				com.setSite_nom(site.getNom());
				listCommentaire.add(com);
			}
			return listCommentaire;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return null;
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
		try {	
			return nPJT.query(sQL, mSPS, rm);
		} catch (DataAccessException e) {
			e.printStackTrace();;
			return null; 
		}
	}

	@Override
	public List<Commentaire> findAllCommentairesForSite(int site_id) {
		String sQL = "SELECT commentaire.id FROM commentaire WHERE site_id = :site_id";
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		if(Integer.toString(site_id) != null) {
			mSPS.addValue("site_id", site_id);
		}
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		RowMapper<Integer> rm = new CommentaireIntegerRM();
		
		List<Commentaire> listCommentaire = new ArrayList<>();
		
		try {	
			for (Integer commentaire_id : nPJT.query(sQL, mSPS ,rm)) {
				listCommentaire.add(findCommentaire(commentaire_id));
			}
			return listCommentaire;
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null; 
		}
	}

}
