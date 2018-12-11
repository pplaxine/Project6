package com.philippe75.p6.webapp.servlets.creation;

import java.io.IOException;
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
import com.philippe75.p6.business.contract.impl.VoieManager;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Voie;



public class CreerVoie extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	public static final String VUE_MAIN ="/WEB-INF/creerVoie.jsp";
	public static final String REDIRECT_SUCCESS_SECTEUR ="/sites/creersite/creersecteur/";
	public static final String REDIRECT_SUCCESS_SITE ="/sites/creersite/";
	
	private String testSecteur; 
	private HttpSession session;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//Enums 
		List<Cotation> listCotation = managerHandler.getCotationManager().listAllCotation();
		this.getServletContext().setAttribute("listCotation", listCotation);
		
		String testSecteur = request.getParameter("testSecteur");
		if(testSecteur != null) {
			this.testSecteur = testSecteur;
			request.setAttribute("testSecteur", testSecteur);
		}else {
			this.testSecteur = null;
		}

		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VoieManager vm = managerHandler.getVoieManager();
		Voie voie = vm.creerNouvelleVoie(request);
			
		if(vm.getErreurs().isEmpty()) {
			
			session = request.getSession();
			
			if(testSecteur != null) {
				Map<String,Voie> voies = (Map<String,Voie>)session.getAttribute("voies");
				if(voies == null) {
					voies = new HashMap<String,Voie>();
				}
				voies.put(voie.getNom(), voie); 
				
				session.setAttribute("voies", voies);
				response.sendRedirect(request.getContextPath() + REDIRECT_SUCCESS_SECTEUR);
			}else {
				Map<String,Voie> voiesSite = (Map<String,Voie>)session.getAttribute("voiesSite");
				if(voiesSite == null) {
					voiesSite = new HashMap<String,Voie>();
				}
				voiesSite.put(voie.getNom(), voie); 
				
				session.setAttribute("voiesSite", voiesSite);
				response.sendRedirect(request.getContextPath() + REDIRECT_SUCCESS_SITE);
			}
			
		}else {
			request.setAttribute("vm", vm);
			request.setAttribute("voie", voie);
			this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
		}
	}

	



}
