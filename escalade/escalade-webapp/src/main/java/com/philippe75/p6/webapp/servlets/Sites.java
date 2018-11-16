package com.philippe75.p6.webapp.servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.p6.business.contract.ManagerHandler;
import com.philippe75.p6.consumer.contract.DaoHandler;
import com.philippe75.p6.model.bean.commentaire.Commentaire;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;



public class Sites extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	@Inject
	DaoHandler daoHandler; 
	
	public static final String VUE_MAIN ="/WEB-INF/sites.jsp";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Site> allSites = managerHandler.getSiteManager().listAllSite();
		request.setAttribute("allSites", allSites);
		
		Commentaire com = daoHandler.getCommentaireDao().findCommentaire(1);
		System.out.println(com.getContenu());
		
		List<Commentaire> comcom = com.getCommentaires();
		Collections.sort(comcom);
		for (Commentaire com2 : comcom) {
			System.out.println("	2ème niv " + com2.getContenu());
		}
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

	



}
