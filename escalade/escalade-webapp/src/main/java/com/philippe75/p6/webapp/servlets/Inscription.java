package com.philippe75.p6.webapp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;


public class Inscription extends HttpServlet {
	
	public static final String VUE_INSCRIPTION_FORM ="/WEB-INF/inscription.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION_FORM).forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//récupération des info du formulaire
		String nom = request.getParameter("nomCompteUtilisateur");
		String prenom = request.getParameter("prenomCompteUtilisateur");
		String pseudo = request.getParameter("pseudoCompteUtilisateur");
		String email = request.getParameter("emailCompteUtilisateur");
		String mdp = request.getParameter("mdpCompteUtilisateur");
		String mdpConf = request.getParameter("mdpCompteUtilisateurConf");
		
		//création du bean 
		CompteUtilisateur cu = new CompteUtilisateur();
		cu.setNom(nom);
		cu.setPrenom(prenom);
		cu.setPseudo(pseudo);
		cu.setEmail(email);
		cu.setMdp(mdp);
		cu.setRole("ROLE_USER");
		
		//----------------------------------------------------------------------
		String resultat; 
		if (mdp.trim() !="" && mdp.equals(mdpConf)) {
			resultat="enregistrement effectué avec succès";
		}else {
			resultat="Les champs n'ont pas été correctement remplis";
		}
		//-------------------------------------------------------------------------
		
		request.setAttribute("cu", cu);
		request.setAttribute("resultat", resultat);
			
		this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION_FORM).forward(request, response);
		
		
	}
	

}
