package com.philippe75.p6.webapp.servlets.creation;

import java.io.IOException;
import java.util.ArrayList;
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
import com.philippe75.p6.business.contract.impl.SiteManager;
import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;



public class CreerSite extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	public static final String VUE_MAIN ="/WEB-INF/creerSite.jsp";
	public static final String URL_RETOUR_SITES ="/sites/";
	public static final String URL_RETOUR_CREERTOPO ="/topo/creertopo/";
	

	private boolean requestFromTopo;
	
	private HttpSession session;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		
		//Enums 
		List<Dept> listDept = managerHandler.getDeptManager().listAllDepts();
		listDept.remove(Dept.TOUS);
		this.getServletContext().setAttribute("listDept", listDept);
		
		//InitiateurDeLaRequete
		if(request.getParameter("topo") != null) {
			requestFromTopo = request.getParameter("topo").equals("true")?true:false;
			//mise en session pour le bouton retour
			session.setAttribute("requestFromTopo", requestFromTopo);
		}
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SiteManager sm = managerHandler.getSiteManager();
		Site site = sm.creerNouveauSite(request);
		
		
		
		if(sm.getErreurs().isEmpty()) {
			
			session = request.getSession();
			List<Secteur> secteurList;
			List<Voie> voieList;
			
			
			if(requestFromTopo) {
				
				if(session.getAttribute("secteurs") != null) {
					Map<String,Secteur> secteursMap = (Map<String,Secteur>)session.getAttribute("secteurs");
					secteurList = new ArrayList<Secteur>(secteursMap.values());
					site.setSecteurs(secteurList);
					
					session.setAttribute("siteTopo", site); 
					session.removeAttribute("secteurs");;
					
				}
				if(session.getAttribute("voiesSite") != null) {
					Map<String,Voie> voiesMap = (Map<String,Voie>)session.getAttribute("voiesSite");
					voieList = new ArrayList<Voie>(voiesMap.values());
					site.setVoies(voieList);
					
					session.setAttribute("siteTopo", site);
					session.removeAttribute("voiesSite");
					
				}
				
				response.sendRedirect(request.getContextPath() + URL_RETOUR_CREERTOPO);
			}else {
			
				if(session.getAttribute("secteurs") != null) {
					Map<String,Secteur> secteursMap = (Map<String,Secteur>)session.getAttribute("secteurs");
					if(!secteursMap.isEmpty()) {
						secteurList = new ArrayList<Secteur>(secteursMap.values());
						site.setSecteurs(secteurList);
						
						int str = managerHandler.getSiteManager().saveSite(site); 
						if(str != 0 ) {
							session.removeAttribute("secteurs");
						}
					}
				}
				
				if(session.getAttribute("voiesSite") != null) {
					Map<String,Voie> voiesMap = (Map<String,Voie>)session.getAttribute("voiesSite");
					if(!voiesMap.isEmpty()) {
						voieList = new ArrayList<Voie>(voiesMap.values());
						site.setVoies(voieList);
						
						int str = managerHandler.getSiteManager().saveSite(site); 
						if(str != 0 ) {
							session.removeAttribute("voiesSite");
						}
					}
				}
				
				response.sendRedirect(request.getContextPath() + URL_RETOUR_SITES);
			}
			
			
		}else {
			request.setAttribute("sm", sm);
			request.setAttribute("site", site);

			this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
		}
	}

	



}
