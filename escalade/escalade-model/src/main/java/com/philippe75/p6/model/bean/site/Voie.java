package com.philippe75.p6.model.bean.site;

public class Voie {
	
	private int id;
	
	private String nom; 
	private float hauteur;
	private int nombreLongueur;
	private int nombrePoints;
	private Cotation cotation;
	
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
	public float getHauteur() {
		return hauteur;
	}
	public void setHauteur(float hauteur) {
		this.hauteur = hauteur;
	}
	public int getNombrePoints() {
		return nombrePoints;
	}
	public void setNombrePoints(int nombrePoints) {
		this.nombrePoints = nombrePoints;
	}
	public Cotation getCotation() {
		return cotation;
	}
	public int getNombreLongueur() {
		return nombreLongueur;
	}
	public void setNombreLongueur(int nombreLongueur) {
		this.nombreLongueur = nombreLongueur;
	}
	public void setCotation(Cotation cotation) {
		this.cotation = cotation;
	} 
	
	
	
}
