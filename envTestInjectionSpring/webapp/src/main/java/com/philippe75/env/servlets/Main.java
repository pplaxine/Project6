package com.philippe75.env.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.philippe75.env.manager.TestManager;

public class Main extends HttpServlet {


	
	public TestManager testManager;

	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}


	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String str;
		if( testManager.getString() != null) {
			str ="pas vide";
		}else{
			str ="vide";
		}
		
		String test2 = str;
		
		//String test2 = testDao.getCountTest2();
		//if(test2 != null) {
			//System.out.println(test2);
		//}else {
			//System.out.println("Empty!!!!!");
		//}
	
		request.setAttribute( "test2", test2 );
		
		String message = "Transmission de variables : OK !";
		request.setAttribute( "test", message );
		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
	}



}
