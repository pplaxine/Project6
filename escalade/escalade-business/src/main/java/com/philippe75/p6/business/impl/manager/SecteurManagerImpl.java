package com.philippe75.p6.business.impl.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.philippe75.p6.business.contract.impl.SecteurManager;
import com.philippe75.p6.business.exception.FormValidationException;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Voie;

@Named("secteurManager")
public class SecteurManagerImpl extends AbstractManager implements SecteurManager{
	
	public static final String CHAMP_NOM = "nomSecteur";

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
	public Secteur creerNouveauSecteur(HttpServletRequest request) {
		
		Secteur secteur = new Secteur();
		erreurs = new HashMap<>();
		
		//récupération des info du formulaire
		String nomSecteur = capitaliser(getValeurChamp(CHAMP_NOM, request));

		
		//test des informations fournies dans les champs et création du bean
		traiterNomSecteur(nomSecteur, secteur, request);
		
		if(erreurs.isEmpty()) {

			secteur.setVoies(getSessionVoies(request));
			result ="Création de voie réalisée avec succès";  
		}else {
			result ="Echec de la création de voie";
		}
	
		return secteur;
	}
	
	//-------- METHODES VALIDATION DES VALEUR DES CHAMPS DU FORMULAIRE ---------------------------------------
	
	private void traiterNomSecteur(String nomSecteur, Secteur secteur, HttpServletRequest request) {
		try {
			validationNomSecteur(nomSecteur, request);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		secteur.setNom(nomSecteur);
	}
	
	//------- METHODES TEST DES VALEURS DES CHAMPS DU FORMULAIRE -------------------------------
	
	private void validationNomSecteur(String nomSecteur, HttpServletRequest request) throws FormValidationException{
		if(nomSecteur != null ) {
			if(nomSecteur.length() < 2) {
				throw new FormValidationException("Le nom du secteur doit contenir au moins 2 caractères.");
			}
			if(SecteurExistant(nomSecteur, request)) {
				throw new FormValidationException("Vous avez déjà crée un secteur de ce nom.");
			}
		
		}else {
			throw new FormValidationException("Merci d'entrer un nom de secteur.");
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

	private List<Voie> getSessionVoies(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("voies") !=null) {
			
			Map<String,Voie> voiesMap = (Map<String,Voie>)session.getAttribute("voies");
			List<Voie> voiesList = new ArrayList<Voie>(voiesMap.values());
			voiesMap = new HashMap<String,Voie>();
			session.setAttribute("voies", voiesMap);
			
			return voiesList;
		}
		return null;
	}
	
	private boolean SecteurExistant(String nomSecteur, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("secteurs") !=null) {
			Map<String,Secteur> secteurs = (Map<String,Secteur>)session.getAttribute("secteurs");
			for (Map.Entry<String, Secteur> secteur : secteurs.entrySet()) {
				if(secteur.getKey().equals(nomSecteur)) {
					System.out.println("Secteur existant");
					return true;
				}
			}
		}
		 System.out.println("Secteur non existant");
		return false; 
	}

}
