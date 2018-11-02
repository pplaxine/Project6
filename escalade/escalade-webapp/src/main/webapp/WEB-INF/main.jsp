<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
        
        <meta charset="utf-8" />
   		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	
    	<c:import url="/inc/bootstrapAndJQueryImport.jsp"></c:import>
    	
        <title>Main</title>
    </head>
    
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  			<a class="navbar-brand" href="#">Escale'add</a>
  			
  			<div class="collapse navbar-collapse" id="navbarNav">
    			<ul class="navbar-nav">
      				<li class="nav-item">
        				<a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a>
      				</li>
      				<li class="nav-item">
        				<a class="nav-link" href="#">Espace prêt de topo</a>
     				</li>
      				<li class="nav-item">
        				<a class="nav-link" href="#">sign in</a>
      				</li>
    			</ul>
  			</div>
		</nav>
	
        
               
        <p>
        </br>
        </br>
        </br>
        </br>
            <% 
            
            String att1 = (String) request.getAttribute("test");
            String att2 = (String) request.getAttribute("nbreTest");
            String att3 = (String) request.getAttribute("nbreCompteUtilisateur");
            
            out.println( att1 + " "+ att2 + ". Nombre de compte utilisateur en BDD : " + att3 );
            
            
            
            %>
        </p>
        
        <!-- BOOTSTRAP AND JQUERY  -->
            
    </body>
</html>