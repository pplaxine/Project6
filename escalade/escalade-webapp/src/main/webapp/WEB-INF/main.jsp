<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta charset="utf-8" />
   		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	
    	<c:import url="/inc/bootstrapAndJQueryImport.jsp"/>
    	
        <title>Main</title>
    </head>
    
    <body>
		<c:import url="/inc/navBar.jsp"/>
	   
        </br>
        
       <p>
       	<h1>Welcome to Escale'add website !</h1>
       	<h2>Le site qui vous permet de partager sur l'escalde</h2>
       </p> 
       
        </br>
        <p>
            <% 
            
            String att1 = (String) request.getAttribute("test");
            String att2 = (String) request.getAttribute("nbreTest");
            String att3 = (String) request.getAttribute("nbreCompteUtilisateur");
            
            out.println( att1 + " "+ att2 + ". Nombre de compte utilisateur en BDD : " + att3 );
            
            
            
            %>
        </p>
        
      
            
    </body>
</html>