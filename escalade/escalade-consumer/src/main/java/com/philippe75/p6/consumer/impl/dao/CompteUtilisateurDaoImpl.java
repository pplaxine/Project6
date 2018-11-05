package com.philippe75.p6.consumer.impl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Named;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.philippe75.p6.consumer.contract.dao.CompteUtilisateurDao;
import com.philippe75.p6.consumer.impl.rowmapper.compteUtilisateur.CompteUtilisateurRM;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

@Named("compteUtilisateurDao")
public class CompteUtilisateurDaoImpl extends AbstractDaoImpl implements CompteUtilisateurDao{
	


	@Override
	public int getCountCompteUtilisateur() {
		String sQl = "SELECT COUNT(*) FROM public.compte_utilisateur";
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		return jT.queryForObject(sQl, Integer.class);
	}

	@Override
	public int createCompteUtilisateur(CompteUtilisateur compteUtilisateur) {
		
		String sQL = "BEGIN TRANSACTION; INSERT INTO compte_utilisateur (nom,prenom,pseudo,email,mdp,acces) VALUES (:nom, :prenom, :pseudo, :email, :mdp, :acces); COMMIT";
			
			SqlParameterSource sPS = new BeanPropertySqlParameterSource(compteUtilisateur);
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			return nPJT.update(sQL, sPS); // update renvoie le nombre de ligne affectée par la requête 
	}

	@Override
	public CompteUtilisateur findCompteUtilisateur(String pseudo) {
		CompteUtilisateur compteUtilisateur = new CompteUtilisateur();
		
			String sQL = "SELECT * FROM public.compte_utilisateur WHERE pseudo=:pseudo";
			
			MapSqlParameterSource mSPS = new MapSqlParameterSource();

			if(pseudo !=null) {
				mSPS.addValue("pseudo", pseudo);
			}
			
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			
			RowMapper<CompteUtilisateur> rm = new CompteUtilisateurRM();
			
			return nPJT.queryForObject(sQL, mSPS, rm);
			
		
	}
	
	
	
	

}
