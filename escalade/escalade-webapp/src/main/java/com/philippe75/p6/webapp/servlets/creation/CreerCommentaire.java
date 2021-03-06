package com.philippe75.p6.webapp.servlets.creation;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.p6.business.contract.ManagerHandler;
import com.philippe75.p6.business.contract.impl.CommentaireManager;
import com.philippe75.p6.model.bean.commentaire.Commentaire;



public class CreerCommentaire extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	private HttpSession session;
	
	public static final String REDIRECT_URL = "/sites/site?site_id=";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		String site_id = (String)request.getParameter("site_id");	
		//String commentaire_id = (String)request.getParameter("commentaire_id");
		
		CommentaireManager comM = managerHandler.getCommentaireManager();
		Commentaire commentaire = comM.creerNouveauCommentaire(request, Integer.valueOf(site_id));
		
		if (comM.getErreurs().isEmpty()) {
			session.removeAttribute("commentaire");
			session.removeAttribute("comM");
			response.sendRedirect(request.getContextPath() + REDIRECT_URL + site_id);
			
		}else {
		session.setAttribute("commentaire", commentaire);
		session.setAttribute("comM", comM);
		
		response.sendRedirect(request.getContextPath() + REDIRECT_URL + site_id);
	
		}
		
	}
	

}
