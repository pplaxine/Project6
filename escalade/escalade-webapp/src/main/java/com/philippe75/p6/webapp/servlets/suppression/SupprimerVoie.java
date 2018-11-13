package com.philippe75.p6.webapp.servlets.suppression;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.p6.business.contract.ManagerHandler;
import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;
import com.philippe75.p6.business.contract.impl.VoieManager;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;



public class SupprimerVoie extends HttpServlet {
	

	public static final String PARAM_DELETE_VOIE ="voieSupp";
	public static final String REDIRECT_SUCCESS_SECTEUR ="/sites/creersite/creersecteur/";
	public static final String REDIRECT_SUCCESS_SITE ="/sites/creersite/";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String voieToDelete	= request.getParameter(PARAM_DELETE_VOIE);		// verif à faire 
		HttpSession session = request.getSession();
		
		System.out.println(voieToDelete);

		if(session.getAttribute("secteurs") != null ) {
			Map<String,Voie> voies = (Map<String,Voie>)session.getAttribute("voies");
			if(voies != null) {
				voies.remove(voieToDelete);
			}
			
			session.setAttribute("voies", voies);
			response.sendRedirect(request.getContextPath() + REDIRECT_SUCCESS_SECTEUR);
		}else {
			Map<String,Voie> voiesSite = (Map<String,Voie>)session.getAttribute("voiesSite");
			if(voiesSite != null) {
				voiesSite.remove(voieToDelete);
			}
			
			session.setAttribute("voiesSite", voiesSite);
			response.sendRedirect(request.getContextPath() + REDIRECT_SUCCESS_SITE);
		}
		
	}

}
