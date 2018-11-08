package com.philippe75.p6.webapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.p6.business.contract.DaoFactory;
import com.philippe75.p6.business.contract.ManagerFactory;
import com.philippe75.p6.consumer.contract.dao.SecteurDao;
import com.philippe75.p6.consumer.contract.dao.VoieDao;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Voie;



public class Main extends HttpServlet {
	
	public static final String VUE_MAIN ="/WEB-INF/main.jsp";

	@Inject
	ManagerFactory managerFactory;
	
	@Inject
	VoieDao voieDao;
	
	@Inject
	SecteurDao secteurDao;
	
	//----------------------------------------------------------------------------
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	//----------------------------------------------------------------------------
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nbreCompteUtilisateur = Integer.toString(managerFactory.getCompteUtilisateurManager().getCountCompteUtilisateur());
		request.setAttribute("nbreCompteUtilisateur", nbreCompteUtilisateur);
		
		
		
		Voie voie = voieDao.findVoie(2);
		request.setAttribute("voie", voie);
		
		List<Voie> voies = voieDao.listVoie(1);
		request.setAttribute("voies", voies);
		
		Secteur secteur = secteurDao.findSecteur(1);
		request.setAttribute("secteur", secteur);
		
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

	



}
