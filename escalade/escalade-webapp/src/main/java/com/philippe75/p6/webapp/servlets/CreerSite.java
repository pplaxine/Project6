package com.philippe75.p6.webapp.servlets;

import java.io.IOException;
import java.util.ArrayList;
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
import com.philippe75.p6.business.contract.impl.SiteManager;
import com.philippe75.p6.business.contract.impl.VoieManager;
import com.philippe75.p6.consumer.contract.DaoHandler;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;



public class CreerSite extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	@Inject
	DaoHandler daoHandler; 
	
	public static final String VUE_MAIN ="/WEB-INF/creerSite.jsp";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Enums 
		List<Dept> listDept = managerHandler.getDeptManager().listAllDepts();
		this.getServletContext().setAttribute("listDept", listDept);
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SiteManager sm = managerHandler.getSiteManager();
		Site site = sm.creerNouveauSite(request);

		if(sm.getErreurs().isEmpty()) {
			
			HttpSession session = request.getSession();
			List<Secteur> secteurList;
			if(session.getAttribute("secteurs") != null) {
				Map<String,Secteur> secteursMap = (Map<String,Secteur>)session.getAttribute("secteurs");
				secteurList = new ArrayList<Secteur>(secteursMap.values());
				site.setSecteurs(secteurList);
				
				int str = managerHandler.getSiteManager().saveSite(site); 
				if(str != 0 ) {
					secteursMap = new HashMap<String,Secteur>();
					session.setAttribute("secteurs", secteursMap);
				}
			}
			
			response.sendRedirect(request.getContextPath() + "/sites/");
		}else {
			request.setAttribute("sm", sm);
			request.setAttribute("site", site);

			this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
		}
	}

	



}