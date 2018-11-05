package com.philippe75.p6.security;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.philippe75.p6.business.contract.ManagerFactory;
import com.philippe75.p6.consumer.contract.dao.CompteUtilisateurDao;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

@Named("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

	@Inject
	private ManagerFactory managerFactory;
	
	@Override
	public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
		
		CompteUtilisateur compteUtilisateur = managerFactory.getCompteUtilisateurManager().findCompteUtilisteurByUserPseudo(pseudo);
		
		//instance de UserDetails
		return new User(compteUtilisateur.getPseudo(), compteUtilisateur.getMdp(), AuthorityUtils.createAuthorityList(compteUtilisateur.getAcces()));
	}

}
