package com.philippe75.p6.webapp.servlets;

import java.io.IOException;
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
import com.philippe75.p6.model.bean.commentaire.Commentaire;
import com.philippe75.p6.model.bean.site.Site;



public class SiteSolo extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	public static final String VUE_MAIN ="/WEB-INF/siteSolo.jsp";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		int site_id = Integer.parseInt(request.getParameter("site_id"));
		Site site = managerHandler.getSiteManager().findSite(site_id);
		request.setAttribute("site", site);
		
		List<Commentaire> commentaireList = managerHandler.getCommentaireManager().findAllCommentairesForSite(site_id);
		Collections.sort(commentaireList);
		Collections.reverse(commentaireList);
		
		request.setAttribute("commentaireList", commentaireList);
		
	
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

}
