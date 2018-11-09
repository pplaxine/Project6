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
    	
        <title>Site</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
	   
       	</br>
        
       	<p>
			<h3>Site <c:out value="${site.nom}"/> </h3>
       	</p>
       	
       	<div>
			Nom du site : <c:out value="${site.nom}"/></br>
		   	Département : <c:out value="${site.dept}"/></br>
		    Lieu : <c:out value="${site.lieu}"/></br>
		    <c:if test="${ site.description != null }">
				Description : <c:out value="${ site.description }"/></br>
		   	</c:if>
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
       	
       	
		
		
    </body>
</html>