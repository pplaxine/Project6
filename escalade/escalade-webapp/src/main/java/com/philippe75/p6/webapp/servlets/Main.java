package com.philippe75.p6.webapp.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.philippe75.p6.consumer.contract.dao.TestDao;



public class Main extends HttpServlet {

	@Inject
	TestDao testDao;



	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		//String test2 = testDao.getCountTest2();
		//if(test2 != null) {
			//System.out.println(test2);
		//}else {
			System.out.println("Empty!!!!!");
		//}
	
		//request.setAttribute( "test2", test2 );
		
		String message = "Transmission de variables : OK !";
		request.setAttribute( "test", message );
		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
	}



}
