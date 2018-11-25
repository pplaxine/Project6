package com.philippe75.p6.model.bean.topo;

import java.time.LocalDateTime;
import java.util.Date;

import com.philippe75.p6.model.bean.site.Site;

public class LocationTopo {
	
	private int id;
	
	private int topo_id;
	private LocalDateTime dateDebutLocation;
	private LocalDateTime dateFinLocation;
	private String dateDebutLocationFormat;
	private String dateFinLocationFormat;
	private String emprunteur;
	private Boolean accepte;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTopo_id() {
		return topo_id;
	}
	public void setTopo_id(int topo_id) {
		this.topo_id = topo_id;
	}
	public LocalDateTime getDateDebutLocation() {
		return dateDebutLocation;
	}
	public void setDateDebutLocation(LocalDateTime dateDebutLocation) {
		this.dateDebutLocation = dateDebutLocation;
	}
	public LocalDateTime getDateFinLocation() {
		return dateFinLocation;
	}
	public void setDateFinLocation(LocalDateTime dateFinLocation) {
		this.dateFinLocation = dateFinLocation;
	}
	public String getEmprunteur() {
		return emprunteur;
	}
	public void setEmprunteur(String emprunteur) {
		this.emprunteur = emprunteur;
	}
	public Boolean getAccepte() {
		return accepte;
	}
	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}
	public String getDateDebutLocationFormat() {
		return dateDebutLocationFormat;
	}
	public void setDateDebutLocationFormat(String dateDebutLocationFormat) {
		this.dateDebutLocationFormat = dateDebutLocationFormat;
	}
	public String getDateFinLocationFormat() {
		return dateFinLocationFormat;
	}
	public void setDateFinLocationFormat(String dateFinLocationFormat) {
		this.dateFinLocationFormat = dateFinLocationFormat;
	}
	
	

}
