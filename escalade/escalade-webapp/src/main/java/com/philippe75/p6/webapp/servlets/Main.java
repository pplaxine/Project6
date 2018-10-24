package com.philippe75.p6.webapp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.philippe75.p6.business.contract.ManagerFactory;
import com.philippe75.p6.business.impl.ManagerFactoryImpl;
import com.philippe75.p6.model.beans.Test;



public class Main extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//try {   Class.forName("org.postgresql.Driver"); } 
		
		//catch(ClassNotFoundException e) { }
		
		
		
		// à injecter ? 
	//	/ManagerFactory mf = new ManagerFactoryImpl();
		
	//	List<Test> listTest = mf.getTestManager().getAllTest();
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
		
	}



}
