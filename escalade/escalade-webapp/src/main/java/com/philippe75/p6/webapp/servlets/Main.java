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

import com.philippe75.p6.business.contract.ManagerHandler;
import com.philippe75.p6.model.bean.commentaire.Commentaire;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.topo.Topo;



public class Main extends HttpServlet {
	
	public static final String VUE_MAIN ="/WEB-INF/main.jsp";

	@Inject
	ManagerHandler managerHandler;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nbreCompteUtilisateur = Integer.toString(managerHandler.getCompteUtilisateurManager().getCountCompteUtilisateur());
		request.setAttribute("nbreCompteUtilisateur", nbreCompteUtilisateur);
		
		Site lastSite = managerHandler.getSiteManager().getLastSiteAdded();
		request.setAttribute("lastSite", lastSite);
		
		List<Commentaire> threeLastCom = managerHandler.getCommentaireManager().getThreelastCommentaire();
		request.setAttribute("lastCom", threeLastCom);
		
		Topo lastTopo = managerHandler.getTopoManager().getLastTopoAdded();
		request.setAttribute("lastTopo", lastTopo);
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}
}
