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
import com.philippe75.p6.business.contract.impl.TopoManager;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.topo.Topo;



public class CreerTopo extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	private HttpSession session;
	
	public static final String VUE_CREERTOPO ="/WEB-INF/creerTopo.jsp";
	public static final String REDIRECT_SUCESS = "/topo/topos/";
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//permet au bouton retour de creerSite de renvoyer a nouveau à la page main 
		session = request.getSession();
		session.removeAttribute("requestFromTopo");
		
		this.getServletContext().getRequestDispatcher(VUE_CREERTOPO).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		TopoManager tm = managerHandler.getTopoManager();
		Topo topo = tm.creerNouveauTopo(request);

		if(tm.getErreurs().isEmpty() && session.getAttribute("siteTopo") != null) {
			Site site = (Site)session.getAttribute("siteTopo");
			topo.setSite(site);
			int str = managerHandler.getTopoManager().saveTopo(topo);
			if(str != 0 ) {
				session.removeAttribute("siteTopo");
			}
			response.sendRedirect(request.getContextPath() + REDIRECT_SUCESS);

		}else {
			request.setAttribute("tm", tm);
			request.setAttribute("topo", topo);

			this.getServletContext().getRequestDispatcher(VUE_CREERTOPO).forward(request, response);
		}
	}

	



}
