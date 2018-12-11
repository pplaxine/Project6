package com.philippe75.p6.webapp.servlets.suppression;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.p6.model.bean.site.Secteur;



public class SupprimerSecteur extends HttpServlet {
	

	public static final String PARAM_DELETE_SECTEUR ="secteurSupp";
	public static final String REDIRECT_SUCCESS_SITE ="/sites/creersite/";

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String secteurToDelete	= request.getParameter(PARAM_DELETE_SECTEUR);		// verif à faire 
		HttpSession session = request.getSession();

		Map<String,Secteur> secteurs = (Map<String,Secteur>)session.getAttribute("secteurs");
		if(secteurs != null) {
			secteurs.remove(secteurToDelete);
		}
			
		session.setAttribute("secteurs", secteurs);
		response.sendRedirect(request.getContextPath() + REDIRECT_SUCCESS_SITE);
		}

}
