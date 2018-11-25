package com.philippe75.p6.model.bean.topo;

import java.util.List;

import com.philippe75.p6.model.bean.site.Site;

public class Topo {
	
	private int id;
	
	private String nom; 
	private String presentation;
	private boolean disponible;
	private Site site;
	private int createur_id;
	private String createur;
	private int NbreResa;
	private List<LocationTopo> listLocationTopo;
	
	
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
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public int getCreateur_id() {
		return createur_id;
	}
	public void setCreateur_id(int createur_id) {
		this.createur_id = createur_id;
	}
	public String getCreateur() {
		return createur;
	}
	public void setCreateur(String createur) {
		this.createur = createur;
	}
	public int getNbreResa() {
		return NbreResa;
	}
	public void setNbreResa(int nbreResa) {
		NbreResa = nbreResa;
	}
	public List<LocationTopo> getListLocationTopo() {
		return listLocationTopo;
	}
	public void setListLocationTopo(List<LocationTopo> listLocationTopo) {
		this.listLocationTopo = listLocationTopo;
	} 
	
	
	

}
