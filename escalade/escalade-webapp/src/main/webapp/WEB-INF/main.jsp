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
        </p>


		<p>	
        	----------------------------------------------------------------------
        </p>
        
        
        <p>
        	<h3>Tous les sites</h3>
        	<c:forEach items="${allSites}" var="allSitesVar" >
		        <div>
		        	Nom du site : <c:out value="${ allSitesVar.nom }"/></br>
		        	Lieu : <c:out value="${ site.lieu }"/></br>
		        	<c:if test="${ site.description != null }">
		        		Description : <c:out value="${ site.description }"/></br>
		        	</c:if>
		        	Département : <c:out value="${ site.dept }"/></br>
		        	Date de création : <c:out value="${ site.dateCreation }"/>
		        	<div>
		        		<c:forEach items="${site.secteurs}" var="secteursVar">
					        <ul>
					       		<li>
					    			Secteur : <c:out value="${secteursVar.nom}"/>
					        		<c:forEach items="${secteursVar.voies}" var="voiesVar3">
					        			<ul>
					        				<li>
					        					Voie : <c:out value="${voiesVar3.nom}"></c:out>
					        					<ul>
					        						<li>
					        							Hauteur : <c:out value="${voiesVar3.hauteur }"/>
					        						</li>
					        						<li>
					        							Nombre de points : <c:out value="${voiesVar3.nombrePoints }"/>
					        						</li>
					        						<li>
					        							Nombre de longueurs : <c:out value="${voiesVar3.nombreLongueur}"/>
					        						</li>
					        						<li>
					        							Cotation : <c:out value="${voiesVar3.cotation }"/>
					        						</li>
					        					</ul>
					        				</li>
					        			</ul>
					        		</c:forEach>
					        	</li>
					        </ul>
				        </c:forEach>
					</div>    
		        </div>
		        
        	</c:forEach>  
        
        </p>
        
       	<p>	
        	----------------------------------------------------------------------
        </p>
        
        <c:out value="${ site.nom }"></c:out>
        
        
    </body>
</html>