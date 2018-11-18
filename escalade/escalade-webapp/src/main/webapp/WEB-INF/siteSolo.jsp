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
	   
       	<br/>
        
       	<p>
			<h3>Site <c:out value="${site.nom}"/> </h3>
       	</p>
       	
       	<div>
			Nom du site : <c:out value="${site.nom}"/><br/>
			<c:choose>
				<c:when test="${site.topo}">Topo disponible</c:when>
				<c:otherwise>Pas de topo disponible</c:otherwise>
			</c:choose><br/>
		   	Département : <c:out value="${site.dept}"/><br/>
		    Lieu : <c:out value="${site.lieu}"/><br/>
		    <c:if test="${ site.description != null }">
				Description : <c:out value="${ site.description }"/><br/>
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
			<div>
				<c:forEach items="${site.voies}" var="voiesListVar">
					<ul>
						<li>
					     		Voie : <c:out value="${voiesListVar.nom}"></c:out>
					        	<ul>
					        		<li>
					        			Hauteur : <c:out value="${voiesListVar.hauteur }"/>
					        		</li>
					        		<li>
					    				Nombre de points : <c:out value="${voiesListVar.nombrePoints }"/>
					       			</li>
					        		<li>
					        			Nombre de longueurs : <c:out value="${voiesListVar.nombreLongueur}"/>
					        		</li>
					        		<li>
					        			Cotation : <c:out value="${voiesListVar.cotation }"/>
					        		</li>
					      		</ul>
						</li>
					</ul>
				</c:forEach>
			</div> 
			<p>
			/////////////////////////////////////////////////////
			</p>
       		
       		<div class="container bg-dark pb-2">
       			<h3 class="text-light">Messages</h3><br/>
				<c:import url="/inc/ajoutCommentaire.jsp"/>				       		
	       		<div class="container bg-dark rounded pb-1">
	       		
	       		<c:forEach items="${commentaireList}" var="comListVar">
	       			<div class="container bg-warning rounded pb-3 mb-4">
	       				<h5>
		       				<c:out value="Commentaire laissé par : ${ comListVar.auteur }"/><c:out value="    le : ${ comListVar.dateCreation }"></c:out><br/>
	       				</h5>
	       				<div class="container bg-dark text-light rounded mt-2 mb-2 pt-2 pb-1">
			       			<p><c:out value="${ comListVar.contenu }"/></p>
	       				</div>
	       				<c:import url="/inc/reponseCommentaire.jsp"/>
	       				<div class="Container ml-5">
				       		<c:forEach items="${comListVar.commentaires}" var="comListVar2">
					     	  	<c:out value="Commentaire laissé par : ${ comListVar2.auteur }"/><c:out value="    le : ${ comListVar2.dateCreation }"></c:out><br/>
				       			<div class="container bg-dark text-light rounded mt-2 mb-2 pt-2 pb-1">
					     	  		<p><c:out value="${ comListVar2.contenu }"/></p>
					     	  	</div>
					     	  	<c:import url="/inc/reponseCommentaire.jsp"/>
				       		</c:forEach>
		       			</div>
		       		</div>	
	       		</c:forEach>
	       		
	       		</div>
       		</div>
		
    </body>
</html>