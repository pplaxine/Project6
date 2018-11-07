package com.philippe75.p6.business.impl.manager;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;
import com.philippe75.p6.business.exception.FormValidationException;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

@Named("CompteUtilisateurManager")
public class CompteUtilisateurManagerImpl extends AbstractManager implements CompteUtilisateurManager{
	
	public static final String CHAMP_NOM = "nomCompteUtilisateur";
	public static final String CHAMP_PRENOM = "prenomCompteUtilisateur";
	public static final String CHAMP_PSEUDO = "pseudoCompteUtilisateur";
	public static final String CHAMP_EMAIL = "emailCompteUtilisateur";
	public static final String CHAMP_MDP = "mdpCompteUtilisateur";
	public static final String CHAMP_MDP_CONF = "mdpCompteUtilisateurConf";

	private Map<String, String> erreurs; 
	private String result; 
	
	//G&S -------------------------------------
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResult() {
		return result;
	}
	//-----------------------------------------

	@Override
	public int getCountCompteUtilisateur() {
		return getDaoFactory().getCompteUtilisateurDao().getCountCompteUtilisateur();
	}

	@Override
	public CompteUtilisateur findCompteUtilisteurByUserPseudo(String pseudo) {	
		return getDaoFactory().getCompteUtilisateurDao().findCompteUtilisateur(pseudo);
	}

	@Override
	public CompteUtilisateur creerNouveauCompte(HttpServletRequest request) {
		
		erreurs = new HashMap<>();
		CompteUtilisateur compteUtilisateur = new CompteUtilisateur();
	
		//récupération des info du formulaire
		String nom = getValeurChamp(CHAMP_NOM, request); 
		String prenom = getValeurChamp(CHAMP_PRENOM, request);
		String pseudo = getValeurChamp(CHAMP_PSEUDO, request);
		String email = getValeurChamp(CHAMP_EMAIL, request);
		String mdp = getValeurChamp(CHAMP_MDP, request);
		String mdpConf = getValeurChamp(CHAMP_MDP_CONF, request);
		
		
		//test des informations fournies dans les champs et création du bean
		traiterNom(nom, compteUtilisateur);
		traiterPrenom(prenom, compteUtilisateur);
		traiterPseudo(pseudo, compteUtilisateur);
		traiterEmail(email, compteUtilisateur);
		traiterMDP(mdp, mdpConf, compteUtilisateur);
		compteUtilisateur.setAcces("ROLE_USER");
		
		if(erreurs.isEmpty()) {
			int succes = getDaoFactory().getCompteUtilisateurDao().createCompteUtilisateur(compteUtilisateur);
			if(succes > 0) {
				result ="Inscription réalisée avec succès";  
			}else {
				result ="Echec lors de l'enregistrement des informations en base de donnée";
			}
		}else {
			result ="Echec de l'inscription";
		}
		
		return compteUtilisateur;

	}
	
	
	//-------- METHODES VALIDATION DES VALEUR DES CHAMPS DU FORMULAIRE ---------------------------------------
	
		private void traiterNom(String nom, CompteUtilisateur cu) {
			try {
				validationNom(nom);
			} catch (FormValidationException e) {
				setErreur(CHAMP_NOM, e.getMessage());
			}
			cu.setNom(nom);
		}
		
		private void traiterPrenom(String prenom, CompteUtilisateur cu) {
			try {
				validationPrenom(prenom);
			} catch (FormValidationException e) {
				setErreur(CHAMP_PRENOM, e.getMessage());
			}
			cu.setPrenom(prenom);
		}
		
		private void traiterPseudo(String pseudo, CompteUtilisateur cu) {
			try {
				validationPseudo(pseudo);
			} catch (FormValidationException e) {
				setErreur(CHAMP_PSEUDO, e.getMessage());
			}
			cu.setPseudo(pseudo);
		}
		
		private void traiterEmail(String email, CompteUtilisateur cu) {
			try {
				validationEmail(email);
			} catch (FormValidationException e) {
				setErreur(CHAMP_EMAIL, e.getMessage());
			}
			cu.setEmail(email);
		}
		
		private void traiterMDP(String mdp, String mdpConf, CompteUtilisateur cu) {
			try {
				validationMDP(mdp, mdpConf);
			} catch (FormValidationException e) {
				setErreur(CHAMP_MDP, e.getMessage());
			}
			cu.setMdp(mdp);
		}
	
	//------- METHODES TEST DES VALEURS DES CHAMPS DU FORMULAIRE -------------------------------
	
	private void validationNom(String nom) throws FormValidationException{
		if(nom != null ) {
			if(nom.length() < 2) {
				throw new FormValidationException("Le nom de l'utilisateur doit contenir au moins 2 caractères.");
			}
		}else {
			throw new FormValidationException("Merci d'entrer un nom d'utilisateur.");
		}
	}
	
	private void validationPrenom(String prenom) throws FormValidationException{
		if(prenom != null ) {
			if(prenom.length() < 2) {
				throw new FormValidationException("Le nom de l'utilisateur doit contenir au moins 2 caractères.");
			}
		}else {
			throw new FormValidationException("Merci d'entrer un prénom d'utilisateur.");
		}
	}
	
	private void validationPseudo(String pseudo) throws FormValidationException{
		if(pseudo != null ) {
			for (CompteUtilisateur compteUtilisateur : getDaoFactory().getCompteUtilisateurDao().getAllCompteUtilisateur()) {
				if(compteUtilisateur.getPseudo().equals(pseudo)) {
					throw new FormValidationException("Ce pseudonyme est déjà utilisé par un autre utilisateur.");
				}
			}
		}else {
			throw new FormValidationException("Merci d'entrer votre pseudo.");
		}
	}
	
	private void validationEmail(String email) throws FormValidationException{
		if(email != null ) {
			for (CompteUtilisateur compteUtilisateur : getDaoFactory().getCompteUtilisateurDao().getAllCompteUtilisateur()) {
				if(compteUtilisateur.getEmail().equals(email)) {
					throw new FormValidationException("Cet adresse email est déjà rattaché à un compte utilisateur.");
				}
			}
		}else {
			throw new FormValidationException("Merci d'entrer une adresse email.");
		}
	}
	
	private void validationMDP(String mdp, String mdpConf) throws FormValidationException{
		if(mdp != null && mdp.equals(mdpConf)) {
																													//---------------------- autres critère à implémenter 
			if(mdp.length() < 4 ) {
				throw new FormValidationException("Le mot de passe doit contenir au moins 4 caractères.");
			}
		}else {
			throw new FormValidationException("Merci d'entrer un mot de passe.");
		}
	}
	
	
	
	//------- METHODES UTILITAIRES ---------------------------------------------------------
	
	private String getValeurChamp(String nomChamp, HttpServletRequest request) {
		String valeur = request.getParameter(nomChamp);
		if(valeur == null || valeur.trim().length() == 0) {
			return null;
		}
		return valeur;
	}

	private void setErreur(String nomChamp, String erreurMessage) {
		erreurs.put(nomChamp, erreurMessage);
	}
}
