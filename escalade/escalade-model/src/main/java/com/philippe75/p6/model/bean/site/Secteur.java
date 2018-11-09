package com.philippe75.p6.model.bean.site;

import java.util.List;

public class Secteur {
	
	private int id;
	
	private String nom;
	private List<Voie> voies;
	
	
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
	public List<Voie> getVoies() {
		return voies;
	}
	public void setVoies(List<Voie> voies) {
		this.voies = voies;
	}

}
