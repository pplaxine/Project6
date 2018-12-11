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
import com.philippe75.p6.model.bean.topo.LocationTopo;
import com.philippe75.p6.model.bean.topo.Topo;

public class EspacePerso extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;

	public static final String VUE_ESPACE_PRET_TOPO ="/WEB-INF/espacePerso.jsp";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Topo> listTopoUser = managerHandler.getTopoManager().listAllTopoForUser();
		request.setAttribute("listTopoUser", listTopoUser);
		
		List<LocationTopo> listDemandeLocation = managerHandler.getTopoManager().findAllLocationOfUser();
		request.setAttribute("listDemandeLocation", listDemandeLocation);
		
		this.getServletContext().getRequestDispatcher(VUE_ESPACE_PRET_TOPO).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_ESPACE_PRET_TOPO).forward(request, response);
	}

}
