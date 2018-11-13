package com.philippe75.p6.business.impl.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.philippe75.p6.business.contract.impl.SiteManager;
import com.philippe75.p6.business.exception.FormValidationException;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;

@Named("siteManager")
public class SiteManagerImpl extends AbstractManager implements SiteManager{

	
	public static final String CHAMP_NOM = "nomSite";
	public static final String CHAMP_DEPT = "deptSite";
	public static final String CHAMP_LIEU = "lieuSite";
	public static final String CHAMP_DESCRIPTION = "descriptionSite";
	
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
	public List<Site> listAllSite() {
		return getDaoHandler().getSiteDao().listAllSite();
	}

	@Override
	public Site findSite(int id) {
		return getDaoHandler().getSiteDao().findSite(id);
	}

	@Override
	public Site creerNouveauSite(HttpServletRequest request) {
		Site site = new Site();
		erreurs = new HashMap<>();
		
		//récupération des info du formulaire
		String nomSite = capitaliser(getValeurChamp(CHAMP_NOM, request));
		String deptSite = getValeurChamp(CHAMP_DEPT, request);
		String lieuSite = capitaliser(getValeurChamp(CHAMP_LIEU, request));
		String descSite = getValeurChamp(CHAMP_DESCRIPTION, request);
	
		
		//test des informations fournies dans les champs et création du bean
		traiterNomSite(nomSite, site);
		traiterDeptSite(deptSite, site);
		traiterLieuSite(lieuSite, site);
		traiterDescSite(descSite, site);
		
		if(erreurs.isEmpty()) {
	
			result ="Création du site réalisée avec succès";  
		}else {
			result ="Echec de création de site";
		}
	
		return site;
	}
	
	//-------- METHODES VALIDATION DES VALEUR DES CHAMPS DU FORMULAIRE ---------------------------------------
	
	private void traiterNomSite(String nomSite, Site site) {
		try {
			validationNomSite(nomSite);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		site.setNom(nomSite);
	}
	
	private void traiterDeptSite(String deptSite, Site site) {
		if(deptSite != null) {
			site.setDept(Dept.get(deptSite));
		}
	}
	
	private void traiterLieuSite(String lieuSite, Site site) {
		try {
			validationLieuSite(lieuSite);
		} catch (FormValidationException e) {
			setErreur(CHAMP_LIEU, e.getMessage());
		}
		site.setLieu(lieuSite);
	}
	
	private void traiterDescSite(String descSite, Site site) {
		if(descSite != null) {
			site.setDescription(descSite);
		}
	}
	
	
	
	//------- METHODES TEST DES VALEURS DES CHAMPS DU FORMULAIRE -------------------------------
	private void validationNomSite(String nomSite) throws FormValidationException{
		if(nomSite != null ) {
			if(nomSite.length() < 2) {
				throw new FormValidationException("Le nom du site doit contenir au moins 2 caractères.");
			}
		}else {
			throw new FormValidationException("Merci d'entrer un nom de site.");
		}
	}
	
	private void validationLieuSite(String lieuSite) throws FormValidationException{
		if(lieuSite != null ) {
			if(lieuSite.length() < 2) {
				throw new FormValidationException("Le lieu doit contenir au moins 2 caractères.");
			}
		}else {
			throw new FormValidationException("Merci d'entrer le lieu ou se situe le site.");
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
