package com.philippe75.p6.webapp.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.p6.business.contract.ManagerHandler;
import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;


public class Inscription extends HttpServlet {
	
	public static final String VUE_INSCRIPTION_FORM ="/WEB-INF/inscription.jsp";
	public static final String VUE_INSCRIPTION_SUCCES = "/WEB-INF/main.jsp";
	
	@Inject
	ManagerHandler managerHandler;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION_FORM).forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CompteUtilisateurManager cum = managerHandler.getCompteUtilisateurManager();
		CompteUtilisateur compteUtilisateur = cum.creerNouveauCompte(request);
		
		if (cum.getErreurs().isEmpty()) {

			//authentification 
			Authentication auth = new UsernamePasswordAuthenticationToken(compteUtilisateur, compteUtilisateur.getMdp(), compteUtilisateur.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			response.sendRedirect(request.getContextPath() + "/home");

		}else {
		
		request.setAttribute("cu", compteUtilisateur);
		request.setAttribute("cum", cum);
			
		this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION_FORM).forward(request, response);
		}
		
	}
	

}
