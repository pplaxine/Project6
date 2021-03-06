package com.philippe75.p6.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.philippe75.p6.consumer.contract.dao.CompteUtilisateurDao;
import com.philippe75.p6.consumer.impl.rowmapper.CompteUtilisateurRM;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

@Named("compteUtilisateurDao")
public class CompteUtilisateurDaoImpl extends AbstractDaoImpl implements CompteUtilisateurDao{
	


	@Override
	public int getCountCompteUtilisateur() {
		String sQl = "SELECT COUNT(*) FROM public.compte_utilisateur";
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		try {
			return jT.queryForObject(sQl, Integer.class);
		} catch (DataAccessException e) {	
			e.printStackTrace();
			return 0;
		} 
	}
	
	@Override
	public int createCompteUtilisateur(CompteUtilisateur compteUtilisateur) {
		
		String sQL = " INSERT INTO compte_utilisateur (nom,prenom,pseudo,email,mdp,acces) VALUES (:nom, :prenom, :pseudo, :email, :mdp, :acces); COMMIT";
			
			SqlParameterSource sPS = new BeanPropertySqlParameterSource(compteUtilisateur);
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			try {
				return nPJT.update(sQL, sPS); // update renvoie le nombre de ligne affectée par la requête 
			} catch (DataAccessException e) {	
				e.printStackTrace();
				return 0;
			} 
	}

	
	@Override
	public CompteUtilisateur findCompteUtilisateur(String pseudo) {
		
			String sQL = "SELECT * FROM public.compte_utilisateur WHERE pseudo=:pseudo";
			
			MapSqlParameterSource mSPS = new MapSqlParameterSource();

			if(pseudo !=null) {
				mSPS.addValue("pseudo", pseudo);
			}
			
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			
			RowMapper<CompteUtilisateur> rm = new CompteUtilisateurRM();
			try {
				return nPJT.queryForObject(sQL, mSPS, rm);
			} catch (DataAccessException e) {	
				e.printStackTrace();
				return null;
			} 
			
	}
	
	@Override
	public CompteUtilisateur findCompteUtilisateur(int user_id) {
		
			String sQL = "SELECT * FROM public.compte_utilisateur WHERE id=:user_id";
			
			MapSqlParameterSource mSPS = new MapSqlParameterSource();

			if(Integer.toString(user_id) !=null) {
				mSPS.addValue("id", user_id);
			}
			
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			
			RowMapper<CompteUtilisateur> rm = new CompteUtilisateurRM();
			
			try {
				return nPJT.queryForObject(sQL, mSPS, rm);
			} catch (DataAccessException e) {	
				e.printStackTrace();
				return null;
			} 
			
	}


	@Override
	public List<CompteUtilisateur> getAllCompteUtilisateur() {
		String sQL = "SELECT * FROM public.compte_utilisateur";
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		RowMapper<CompteUtilisateur> rm = new CompteUtilisateurRM();
		List<CompteUtilisateur> listCompteUtilisateur = jT.query(sQL, rm);
		try {
			return listCompteUtilisateur;
		} catch (DataAccessException e) {	
			e.printStackTrace();
			return null;
		} 
		
	}

}
