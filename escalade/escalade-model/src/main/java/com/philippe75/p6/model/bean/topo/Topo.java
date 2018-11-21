package com.philippe75.p6.model.bean.topo;

import java.util.Date;

import com.philippe75.p6.model.bean.site.Site;

public class Topo {
	
	private int id;
	
	private String nom; 
	private String presentation;
	private boolean disponible;
	private Date dateDebutLocation;
	private Date dateFinLocation;
	private Site site;
	private String preteur;
	private String emprunteur;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPresentation() {
		return presentation;
	}
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Date getDateDebutLocation() {
		return dateDebutLocation;
	}
	public void setDateDebutLocation(Date dateDebutLocation) {
		this.dateDebutLocation = dateDebutLocation;
	}
	public Date getDateFinLocation() {
		return dateFinLocation;
	}
	public void setDateFinLocation(Date dateFinLocation) {
		this.dateFinLocation = dateFinLocation;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public String getPreteur() {
		return preteur;
	}
	public void setPreteur(String preteur) {
		this.preteur = preteur;
	}
	public String getEmprunteur() {
		return emprunteur;
	}
	public void setEmprunteur(String emprunteur) {
		this.emprunteur = emprunteur;
	} 
	
	
	

}
