package com.philippe75.p6.webapp.servlets.validation;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.p6.business.contract.ManagerHandler;




public class AccepterLocation extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	public static final String REDIRECT_SUCCESS_TOPO ="/espaceperso";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int location_id = Integer.valueOf(request.getParameter("location"));
		if(location_id != 0) {
			int result = managerHandler.getTopoManager().repondreDemandeLocation(location_id, true);
		}
		response.sendRedirect(request.getContextPath() + REDIRECT_SUCCESS_TOPO);
		}

}
