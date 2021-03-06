package com.philippe75.p6.webapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.p6.business.contract.ManagerHandler;
import com.philippe75.p6.consumer.contract.DaoHandler;
import com.philippe75.p6.model.bean.topo.Topo;



public class Topos extends HttpServlet {
	
	@Inject
	ManagerHandler managerHandler;
	
	@Inject
	DaoHandler daoHandler; 
	
	public static final String VUE_TOPOS ="/WEB-INF/topos.jsp";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		List<Topo> allTopos = managerHandler.getTopoManager().listAllTopo();
		request.setAttribute("allTopos", allTopos);
		
		//retrait du message de success location (topoSolo) 
		session.removeAttribute("tm");
		
		this.getServletContext().getRequestDispatcher(VUE_TOPOS).forward(request, response);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Topo> allTopos = daoHandler.getTopoDao().listAllTopo();		// a modif pour managerHandler
		request.setAttribute("allTopos", allTopos);
	
		this.getServletContext().getRequestDispatcher(VUE_TOPOS).forward(request, response);
		
		
	}
	

	
	



}
