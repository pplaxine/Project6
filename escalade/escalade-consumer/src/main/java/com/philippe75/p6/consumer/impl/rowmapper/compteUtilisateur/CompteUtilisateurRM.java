package com.philippe75.p6.consumer.impl.rowmapper.compteUtilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class CompteUtilisateurRM implements RowMapper<CompteUtilisateur> {
	
	@Override	
	public CompteUtilisateur mapRow(ResultSet rs, int rn) throws SQLException {		
		CompteUtilisateur compteUtilisateur = new CompteUtilisateur(); 		 
		compteUtilisateur.setPrenom(rs.getString("prenom"));
		compteUtilisateur.setNom(rs.getString("nom"));
		compteUtilisateur.setPseudo(rs.getString("pseudo"));
		compteUtilisateur.setEmail(rs.getString("email"));
		compteUtilisateur.setMdp(rs.getString("mdp"));
		compteUtilisateur.setAcces(rs.getString("acces"));
		
		
		return compteUtilisateur;		// l'ojet a maitenant ses 2 att et devient un bean singulier 
	}
}