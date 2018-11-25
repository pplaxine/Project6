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
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.p6.business.contract.ManagerHandler;
import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;
import com.philippe75.p6.business.contract.impl.TopoManager;
import com.philippe75.p6.consumer.contract.DaoHandler;
import com.philippe75.p6.model.bean.commentaire.Commentaire;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.topo.LocationTopo;
import com.philippe75.p6.model.bean.topo.Topo;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;



public class TopoSolo extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	
	public static final String VUE_TOPO_SOLO ="/WEB-INF/topoSolo.jsp";
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int topo_id_param = Integer.valueOf(request.getParameter("topo_id"));
		Topo topo = managerHandler.getTopoManager().findTopo(topo_id_param);
		request.setAttribute("topo", topo);
		this.getServletContext().getRequestDispatcher(VUE_TOPO_SOLO).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String topo_id = request.getParameter("topo_id");
		
		TopoManager tm = managerHandler.getTopoManager();
		LocationTopo locationTopo = tm.creerNouvelleDemandeLocation(request);
		
		if (tm.getErreurs().isEmpty()) {
			
			int result = managerHandler.getTopoManager().saveDemandeLocationTopo(locationTopo);
			if(result > 0) {
				session.removeAttribute("locationTopo");
				session.setAttribute("tm", tm);
			}
			response.sendRedirect(request.getContextPath() + "/topo?topo_id=" + topo_id);

		}else {
		
		session.setAttribute("locationTopo", locationTopo);
		session.setAttribute("tm", tm);
		
		response.sendRedirect(request.getContextPath() + "/topo?topo_id=" + topo_id);
		}
	}

}
