package com.philippe75.p6.model.bean.site;

import java.util.Date;

import com.philippe75.p6.model.bean.topo.Topo;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class Site {
	
	private int id;
	
	private String nom;
	private String lieu;
	private String description;
	private Date dateCreation;
	private Dept dept;
	private Secteur secteur;
	
	
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
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}


}
