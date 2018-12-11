package com.philippe75.p6.webapp.servlets.suppression;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;



public class SupprimerSite extends HttpServlet {
	
	public static final String REDIRECT_SUCCESS_TOPO ="/topo/creertopo/";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute("siteTopo");

		response.sendRedirect(request.getContextPath() + REDIRECT_SUCCESS_TOPO);
		}

}
