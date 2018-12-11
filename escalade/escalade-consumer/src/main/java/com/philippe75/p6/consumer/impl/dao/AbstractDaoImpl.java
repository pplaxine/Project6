package com.philippe75.p6.consumer.impl.dao;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.philippe75.p6.consumer.contract.DaoHandler;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;


public abstract class AbstractDaoImpl {
	
	@Inject
	private DaoHandler daoHandler; 
	
	private static DataSource dataSource;
	
	public DaoHandler getDaoHandler() {
		return daoHandler;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public static void setDataSource(DataSource dataSource) {
		AbstractDaoImpl.dataSource = dataSource;
	}
	
	protected CompteUtilisateur getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userPseudo = auth.getName();
		CompteUtilisateur cu = getDaoHandler().getCompteUtilisateurDao().findCompteUtilisateur(userPseudo);
		
		return cu;
	}

}
