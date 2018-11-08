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
       	<h2>Le site qui vous permet de partager sur l'escalade</h2>
       </p> 
       
        </br>
        <p>
        	Nombre de compte utilisateur en BDD : ${nbreCompteUtilisateur}
        	Nom de la voie : ${voie.nom}. Nombre de longueurs ${ voie.nombreLongueur }
        </p>
        <p>
        	<c:forEach items="${voies}" var="voieVar">
        		<c:out value="${voieVar.nom}"></c:out>
        	</c:forEach>
        </p>
        <p>
        		Nom du secteur : <c:out value="${secteur.nom }"></c:out>
        		<p>
        		Nom des voies :
        		<ul>
        			<c:forEach items="${secteur.voie}" var="voieVar2">
        				<li>
        				 <c:out value="${voieVar2.nom}"/>
        				 	<ul>
        				 		<li>${voieVar2.hauteur}</li>
        				 		<li>${voieVar2.nombrePoints}</li>
        				 		<li>${voieVar2.nombreLongueur}</li>
        				 		<li>${voieVar2.cotation}</li>
        				 	</ul>
        				</li>
        			</c:forEach>
        		</ul>
        		</p>
        </p>
        
    </body>
</html>