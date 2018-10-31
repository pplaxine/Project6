package com.philippe75.p6.consumer.impl.dao;

import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;

import com.philippe75.p6.consumer.contract.dao.CompteUtilisateurDao;

@Named("compteUtilisateurDao")
public class CompteUtilisateurDaoImpl extends AbstractDaoImpl implements CompteUtilisateurDao{

	@Override
	public int getCountCompteUtilisateur() {
		String sQl = "SELECT COUNT(*) FROM public.compte_utilisateur";
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		return jT.queryForObject(sQl, Integer.class);
	}

}
