package com.philippe75.p6.business.impl.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.philippe75.p6.business.contract.impl.SecteurManager;
import com.philippe75.p6.business.contract.impl.TopoManager;
import com.philippe75.p6.business.exception.FormValidationException;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Voie;
import com.philippe75.p6.model.bean.topo.Topo;

@Named("topoManager")
public class TopoManagerImpl extends AbstractManager implements TopoManager{
	
	public static final String CHAMP_NOM = "nomTopo";
	public static final String CHAMP_PRESENTATION = "presentationTopo";

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
	public int saveTopo(Topo topo) {
		return getDaoHandler().getTopoDao().createTopo(topo);
	}
	
	
	@Override
	public Topo creerNouveauTopo(HttpServletRequest request) {
		
		Topo topo = new Topo();
		erreurs = new HashMap<>();
		
		//récupération des info du formulaire
		String nomTopo = capitaliser(getValeurChamp(CHAMP_NOM, request));
		String presentationTopo = capitaliser(getValeurChamp(CHAMP_PRESENTATION, request));

		
		//test des informations fournies dans les champs et création du bean
		traiterNomTopo(nomTopo, topo, request);
		topo.setPresentation(presentationTopo);
		
		if(erreurs.isEmpty()) {
			
			result ="Création de voie réalisée avec succès";  
		}else {
			result ="Echec de la création de voie";
		}
		return topo;
	}
	
	//-------- METHODES VALIDATION DES VALEUR DES CHAMPS DU FORMULAIRE ---------------------------------------
	
	private void traiterNomTopo(String nomTopo, Topo topo, HttpServletRequest request) {
		try {
			validationNomTopo(nomTopo);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		topo.setNom(nomTopo);
	}

	//------- METHODES TEST DES VALEURS DES CHAMPS DU FORMULAIRE -------------------------------
	
	private void validationNomTopo(String nomTopo) throws FormValidationException{
		if(nomTopo != null ) {
			if(nomTopo.length() < 2) {
				throw new FormValidationException("Le nom du Topo doit contenir au moins 2 caractères.");
			}
		}else {
			throw new FormValidationException("Merci d'entrer un nom de Topo.");
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
