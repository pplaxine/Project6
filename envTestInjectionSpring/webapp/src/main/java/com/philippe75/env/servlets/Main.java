package com.philippe75.env.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.env.manager.TestManager;

@Configurable
public class Main extends HttpServlet {


	@Inject
	private TestManager testManager;
	
	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		String test2 = testManager.getString();
		
	
		request.setAttribute( "test2", test2 );
		
		String message = "Transmission de variables : OK !";
		request.setAttribute( "test", message );
		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
	}



}
