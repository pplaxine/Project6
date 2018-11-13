package com.philippe75.p6.webapp.servlets;

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
import com.philippe75.p6.business.contract.impl.SecteurManager;
import com.philippe75.p6.business.contract.impl.VoieManager;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;



public class CreerSecteur extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	public static final String VUE_MAIN ="/WEB-INF/creerSecteur.jsp";
	public static final String REDIRECT_SUCCESS ="/sites/creersite/";
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SecteurManager secM = managerHandler.getSecteurManager();
		Secteur secteur = secM.creerNouveauSecteur(request);
			
		if(secM.getErreurs().isEmpty()) {
			HttpSession session = request.getSession();
			Map<String,Secteur> secteurs = (Map<String,Secteur>)session.getAttribute("secteurs");
			if(secteurs == null) {
				secteurs = new HashMap<String,Secteur>();
			}
			
			secteurs.put(secteur.getNom(), secteur); 
			session.setAttribute("secteurs", secteurs);
			response.sendRedirect(request.getContextPath() + REDIRECT_SUCCESS);
		}else {
			request.setAttribute("secM", secM);
			request.setAttribute("secteur", secteur);
			this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
		}
	}

	



}
