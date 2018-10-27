package com.philippe75.env.beans;

import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;

@Named("blop")
public class TestBean extends AbstractConnector{
	
	
	private String prenom ="Philippe" ;
	private String nom;
	
	
	public int getNumberOfTest() {
		
		String sQL ="SELECT COUNT(*) FROM public.test";
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		int nbre = jT.queryForObject(sQL, Integer.class);
		
		
		return nbre;
	}
	
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	} 
	

}
