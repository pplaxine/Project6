package com.philippe75.p6.business.impl.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.business.contract.impl.CommentaireManager;
import com.philippe75.p6.business.exception.FormValidationException;
import com.philippe75.p6.model.bean.commentaire.Commentaire;

@Named("commentaireManager")
public class CommentaireManagerImpl extends AbstractManager implements CommentaireManager{
	
	public static final String CHAMP_COMMENTAIRE = "contenuCom";
	
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
	public List<Commentaire> findAllCommentairesForSite(int site_id) {
		return getDaoHandler().getCommentaireDao().findAllCommentairesForSite(site_id);
	}

	@Override
	public Commentaire creerNouveauCommentaire(HttpServletRequest request, int site_id) {

		erreurs = new HashMap<>();
		Commentaire commentaire = new Commentaire();
	
		//récupération des info du formulaire
		String contenu = getValeurChamp(CHAMP_COMMENTAIRE, request); 
		
		
		
		//test des informations fournies dans les champs et création du bean
		traiterContenu(contenu, commentaire);
		commentaire.setSite_id(site_id);
		
		if(erreurs.isEmpty()) {
			int succes = getDaoHandler().getCommentaireDao().createCommentaire(commentaire); 
			if(succes > 0) {
				result ="Votre message à bien été ajouté";  
			}else {
				result ="Echec lors de l'enregistrement des informations en base de donnée";
			}
		}else {
			result ="Echec de l'ajout de votre message";
		}
		
		return commentaire;
	}
	
	//-------- METHODES VALIDATION DES VALEUR DES CHAMPS DU FORMULAIRE ---------------------------------------
	
	private void traiterContenu(String contenu, Commentaire commentaire) {
		try {
			validationContenu(contenu);
		} catch (FormValidationException e) {
			setErreur(CHAMP_COMMENTAIRE, e.getMessage());
		}
		commentaire.setContenu(contenu);
	}
	
	//------- METHODES TEST DES VALEURS DES CHAMPS DU FORMULAIRE -------------------------------
	
	private void validationContenu(String contenu) throws FormValidationException{
		if(contenu == null ) {
			throw new FormValidationException("Merci d'entrer un message.");
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

	@Override
	public List<Commentaire> getThreelastCommentaire() {
		return getDaoHandler().getCommentaireDao().getThreelastCommentaire();
	}
}
