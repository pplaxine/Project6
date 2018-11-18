package com.philippe75.p6.business.impl.manager;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.business.contract.impl.VoieManager;
import com.philippe75.p6.business.exception.FormValidationException;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Voie;

@Named("voieManager")
public class VoieManagerImpl extends AbstractManager implements VoieManager{
	
	public static final String CHAMP_NOM = "nomVoie";
	public static final String CHAMP_HAUTEUR = "hauteurVoie";
	public static final String CHAMP_NOMBRE_LONGUEURS = "nombreLongueursVoie";
	public static final String CHAMP_NOMBRE_POINTS = "nombrePointsVoie";
	public static final String CHAMP_COTATION = "cotationVoie";
	
	private Map<String, String> erreurs; 
	private String result;

	//G&S -------------------------------------
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	public String getResult() {
		return result;
	}
	
	
	@Override
	public Voie creerNouvelleVoie(HttpServletRequest request) {
		
		Voie voie = new Voie();
		erreurs = new HashMap<>();
		
		//récupération des info du formulaire
		String nomVoie = capitaliser(getValeurChamp(CHAMP_NOM, request));
		String hauteurVoie = getValeurChamp(CHAMP_HAUTEUR, request);
		String nombreLongueursVoie = getValeurChamp(CHAMP_NOMBRE_LONGUEURS, request);
		String nombrePointsVoie = getValeurChamp(CHAMP_NOMBRE_POINTS, request);
		String cotationVoie = getValeurChamp(CHAMP_COTATION, request);
		
		//test des informations fournies dans les champs et création du bean
		traiterNomVoie(nomVoie, voie);
		traiterHauteurVoie(hauteurVoie, voie);
		traiterNombreLongueursVoie(nombreLongueursVoie, voie);
		traiterNombrePointsVoie(nombrePointsVoie, voie);
		traiterCotationVoie(cotationVoie, voie);
		
		if(erreurs.isEmpty()) {
			result ="Création de voie réalisée avec succès";  
		}else {
			result ="Echec de la création de voie";
		}
	
		return voie;
	}
	
	//-------- METHODES VALIDATION DES VALEUR DES CHAMPS DU FORMULAIRE ---------------------------------------
	
	private void traiterNomVoie(String nom, Voie voie) {
		try {
			validationNomVoie(nom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		voie.setNom(nom);
	}
	
	private void traiterHauteurVoie(String hauteurVoie, Voie voie) {
		try {
			validationHauteurVoie(hauteurVoie);
		} catch (FormValidationException e) {
			setErreur(CHAMP_HAUTEUR, e.getMessage());
		}
		if(hauteurVoie !=null) {
			voie.setHauteur(Float.valueOf(hauteurVoie));
		}
	}
	
	private void traiterNombreLongueursVoie(String nombreLongueursVoie, Voie voie) {
		if(nombreLongueursVoie != null) {
			voie.setNombreLongueur(Integer.valueOf(nombreLongueursVoie));
		}
	
	}
	
	private void traiterNombrePointsVoie(String nombrePointsVoie, Voie voie) {
		if(nombrePointsVoie != null) {
			voie.setNombrePoints(Integer.valueOf(nombrePointsVoie));
		}
	}
	
	private void traiterCotationVoie(String cotationVoie, Voie voie) {
		if(cotationVoie != null) {
			voie.setCotation( Cotation.get(cotationVoie)) ;
		}
	}
	
	
	//------- METHODES TEST DES VALEURS DES CHAMPS DU FORMULAIRE -------------------------------
	
	private void validationNomVoie(String nomVoie) throws FormValidationException{
		if(nomVoie != null ) {
			if(nomVoie.length() < 2) {
				throw new FormValidationException("Le nom de la voie doit contenir au moins 2 caractères.");
			}
		}else {
			throw new FormValidationException("Merci d'entrer un nom de voie.");
		}
	}
	
	private void validationHauteurVoie(String hauteurVoie) throws FormValidationException{
		if(hauteurVoie == null || Float.valueOf(hauteurVoie) < 1  ) {
			throw new FormValidationException("Merci d'entrer une hauteur de voie.");
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
	
	private String capitaliser(String str) {
		if(str != null) {
			String cap = str.substring(0,1).toUpperCase() + str.substring(1);
			return cap;
		}
		return null;
	}

	
}
