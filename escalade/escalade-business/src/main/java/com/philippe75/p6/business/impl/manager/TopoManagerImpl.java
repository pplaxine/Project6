package com.philippe75.p6.business.impl.manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import com.philippe75.p6.business.contract.impl.TopoManager;
import com.philippe75.p6.business.exception.FormValidationException;
import com.philippe75.p6.model.bean.topo.LocationTopo;
import com.philippe75.p6.model.bean.topo.Topo;

@Named("topoManager")
public class TopoManagerImpl extends AbstractManager implements TopoManager{
	
	public static final String CHAMP_NOM = "nomTopo";
	public static final String CHAMP_PRESENTATION = "presentationTopo";
	
	public static final String CHAMP_DATE_DEBUT_LOCATION_DEMANDE = "dateDebutLocationDemande";
	public static final String CHAMP_DATE_FIN_LOCATION_DEMANDE = "dateFinLocationDemande";
	public static final String ATT_TOPO_ID = "topo_id";

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
	public Topo findTopo(int topo_id) {
		
		Topo topo = getDaoHandler().getTopoDao().findTopo(topo_id);
		if(topo != null) {
			List<LocationTopo> listLocationTopo = topo.getListLocationTopo();
			LocalDateTime now = LocalDateTime.now();
			List<LocationTopo> listSansLocationPassee = new ArrayList<>();
			for (LocationTopo lt : listLocationTopo) {
				if(!(lt.getDateDebutLocation().isBefore(now))) {
					listSansLocationPassee.add(lt);
				}
			}
			topo.setListLocationTopo(listSansLocationPassee);
		}
		
		return topo;
		
	}
	
	@Override
	public List<Topo> listAllTopo() {
		return getDaoHandler().getTopoDao().listAllTopo();
	}
	
	@Override
	public Topo creerNouveauTopo(HttpServletRequest request) {
		
		Topo topo = new Topo();
		erreurs = new HashMap<>();
		
		//récupération des info du formulaire
		String nomTopo = capitaliser(getValeurChamp(CHAMP_NOM, request));
		String presentationTopo = capitaliser(getValeurChamp(CHAMP_PRESENTATION, request));

		
		//test des informations fournies dans les champs et création du bean
		traiterNomTopo(nomTopo, topo);
		topo.setPresentation(presentationTopo);
		
		if(erreurs.isEmpty()) {
			
			result ="Création de voie réalisée avec succès";  
		}else {
			result ="Echec de la création de voie";
		}
		return topo;
	}
	
	@Override
	public List<Topo> listAllTopoForUser() {
		return getDaoHandler().getTopoDao().listAllTopoForUser();
	}
	
	@Override
	public List<LocationTopo> findAllLocationOfUser() {
		return getDaoHandler().getTopoDao().findAllLocationOfUser();
	}

	
	@Override
	public LocationTopo creerNouvelleDemandeLocation(HttpServletRequest request) {
		
		LocationTopo lt = new LocationTopo();
		erreurs = new HashMap<>();
		
		//récupération des info du formulaire
		String dateDebutLocationFullFormat = addSecToDT(getValeurChamp(CHAMP_DATE_DEBUT_LOCATION_DEMANDE, request));
		LocalDateTime dateDebutLocationDemande = LocalDateTime.parse(dateDebutLocationFullFormat, DateTimeFormatter.ofPattern("dd MM yyyy HH:mm:ss")); 
		
		String dateFinLocationFullFormat = addSecToDT(getValeurChamp(CHAMP_DATE_FIN_LOCATION_DEMANDE, request));
		LocalDateTime dateFinLocationDemande = LocalDateTime.parse(dateFinLocationFullFormat, DateTimeFormatter.ofPattern("dd MM yyyy HH:mm:ss"));  
		
		int topo_id = Integer.valueOf(getValeurChamp(ATT_TOPO_ID, request));
		
		//test des informations fournies dans les champs et création du bean
		traiterDateLocationTopo(dateDebutLocationDemande, dateFinLocationDemande, topo_id, lt);
		lt.setTopo_id(topo_id);
		
		
		if(erreurs.isEmpty()) {
			
			result ="Création de demande de location de topo réalisée avec succès";  
		}else {
			result ="Echec de la création de la demande de location de topo";
		}
		
		return lt;
	}
	
	@Override
	public int saveDemandeLocationTopo(LocationTopo locationTopo) {
		int result = getDaoHandler().getTopoDao().saveDemandeLocationTopo(locationTopo);
		return result;
	}
	
	@Override
	public int repondreDemandeLocation(int location_id, Boolean accepter) {
		return getDaoHandler().getTopoDao().repondreDemandeLocation(location_id, accepter);
	}
	@Override
	public int deleteLocation(int location_id) {
		return getDaoHandler().getTopoDao().deleteLocation(location_id);
	}
	
	
	//-------- METHODES VALIDATION DES VALEUR DES CHAMPS DU FORMULAIRE ---------------------------------------
	
	private void traiterNomTopo(String nomTopo, Topo topo) {
		try {
			validationNomTopo(nomTopo);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		topo.setNom(nomTopo);
	}
	
	private void traiterDateLocationTopo(LocalDateTime dateDebutLocationDemande, LocalDateTime dateFinLocationDemande, int topo_id, LocationTopo lt) {
		try {
			validationDateLocationTopo(dateDebutLocationDemande, dateFinLocationDemande, topo_id);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DATE_DEBUT_LOCATION_DEMANDE, e.getMessage());
		}
		lt.setDateDebutLocation(dateDebutLocationDemande);
		lt.setDateFinLocation(dateFinLocationDemande);
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
	
	private void validationDateLocationTopo(LocalDateTime dateDebutLocationDemande, LocalDateTime dateFinLocationDemande, int topo_id) throws FormValidationException{
		if(dateDebutLocationDemande != null && dateFinLocationDemande != null && topo_id != 0) {
			List <LocationTopo> listLocations = getDaoHandler().getTopoDao().findLocationTopo(topo_id);
			for (LocationTopo lt : listLocations) {
				if(lt.getAccepte() != null && lt.getAccepte() == true && (dateIsBetweenTheseDates(dateDebutLocationDemande, lt.getDateDebutLocation(), lt.getDateFinLocation()) || dateIsBetweenTheseDates(dateFinLocationDemande, lt.getDateDebutLocation(), lt.getDateFinLocation())  )) {
					throw new FormValidationException("La date choisi est comprise dans une période ou le topo fait déjà l'objet d'une location.");
				}
			}
			
			
		}else {
			throw new FormValidationException("Merci d'entrer une date de début et de fin de demande de location");
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
	
	private boolean dateIsBetweenTheseDates(LocalDateTime dateToCheck, LocalDateTime startDate, LocalDateTime endDate) {
		if(dateToCheck.isAfter(startDate) && dateToCheck.isBefore(endDate)) {
			return true;
		}
		return false;
	}

	private String addSecToDT(String dtDate) {
		String newDate = dtDate + ":01";
		return newDate;
	}



}
