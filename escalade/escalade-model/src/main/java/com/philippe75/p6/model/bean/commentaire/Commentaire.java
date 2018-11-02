package com.philippe75.p6.model.bean.commentaire;

import java.util.Date;

import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class Commentaire {
	
	private int id;
	
	private Date dateCreation; 
	private String contenu;
	
	
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
	
}
