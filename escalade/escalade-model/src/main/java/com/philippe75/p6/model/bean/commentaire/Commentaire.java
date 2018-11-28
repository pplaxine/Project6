package com.philippe75.p6.model.bean.commentaire;

import java.util.Date;
import java.util.List;

import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class Commentaire implements Comparable<Commentaire>{
	
	private int id;
	
	private String site_nom;
	private int site_id;
	private String auteur;
	private Date dateCreation; 
	private String contenu;
	private List<Commentaire> commentaires;
	
	

	public int getSite_id() {
		return site_id;
	}
	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}
	
	public String getSite_nom() {
		return site_nom;
	}
	public void setSite_nom(String site_nom) {
		this.site_nom = site_nom;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	} 
	public List<Commentaire> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	@Override
	public int compareTo(Commentaire com) {
		return getDateCreation().compareTo(com.getDateCreation());
	}
	
}
