package com.philippe75.p6.webapp.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.p6.business.contract.ManagerFactory;
import com.philippe75.p6.model.bean.site.Cotation;



public class Main extends HttpServlet {
	
	public static final String VUE_MAIN ="/WEB-INF/main.jsp";

	@Inject
	ManagerFactory managerFactory;
	
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	//----------------------------------------------------------------------------
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	//----------------------------------------------------------------------------
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cotation cotation = Cotation.COTATION_9B_PLUS;
		
		
		String message = "Transmission de variables : OK ! " + cotation;
		request.setAttribute( "test", message );
		
		String nbreTest = " Nombre de test dans la BDD : " + managerFactory.getTestManager().getCountTest();
		request.setAttribute( "nbreTest", nbreTest );
		
		String nbreCompteUtilisateur = Integer.toString(managerFactory.getCompteUtilisateurManager().getCountCompteUtilisateur());
		request.setAttribute("nbreCompteUtilisateur", nbreCompteUtilisateur);
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
	}

	



}
