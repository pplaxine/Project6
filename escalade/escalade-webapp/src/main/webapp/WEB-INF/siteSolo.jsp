<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
	   	<div class="bg_siteSolo">
       		
	       	<div class="container-fluid text-light pt-4">
	       	
				<h3>Site <c:out value="${site.nom}"/> </h3>
	       	
	       		<div class="card col-lg-3 col-md-6 mb-2 pt-3 pb-5 h5">
				
				   	Département : <c:out value="${site.dept}"/><br/>
				    Lieu : <c:out value="${site.lieu}"/><br/>
				    <c:if test="${ site.description != null }">
						Description : <c:out value="${ site.description }"/><br/>
				   	</c:if>
				   	Date de création : <c:out value="${ site.dateCreation }"/>
				        	
				    <div><br/>
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
						<c:choose>
							<c:when test="${site.topo}">Topo disponible</c:when>
							<c:otherwise>Pas de topo disponible</c:otherwise>
						</c:choose><br/>
					</div>
				</div> 
	       	</div>
	       	
       		<div class="container bg-dark pb-2 rounded col-lg-5 offset-lg-4" id="site_solo_message_block">
       			<h3 class="text-light">Messages</h3><br/>
       			<div class="row">
	       			<div class="col-sm-12 mb-3">
						<c:import url="/inc/ajoutCommentaire.jsp"/>				       		
	       			</div>
		       		<div class="col-sm-12">
		       		
		       		<c:forEach items="${commentaireList}" var="comListVar">
		       			<div class="card mb-4">
		       					<div class="card-header bg-warning">
		       						<div class="row">
		       							<div class="col-md-6 ">
										    <c:out value="Commentaire laissé par : ${ comListVar.auteur } "/>
		       							</div>
		       							<div class="col-md-6 text-right">
		       								<c:out value="le : ${ comListVar.dateCreation }"/>
		       							</div>
		       						</div>
								</div >
			       			
			       				<div class="card-body bg-light">
					       			<p><c:out value="${ comListVar.contenu }"/></p>
			       				</div>
								
			       		<%-- 	<div class="collapse bg-dark ml-5 rounded" id="repCol">
			       					
						       		<c:forEach items="${comListVar.commentaires}" var="comListVar2">
						       			<div class="container bg-warning p-2 m-1 ">
						       				 <c:out value="Commentaire laissé par : ${ comListVar2.auteur }"/><c:out value="    le : ${ comListVar2.dateCreation }"></c:out><br/>
						       				<div class="container bg-dark text-light rounded mt-2 mb-2 pt-2 pb-1">
							     	  		<p><c:out value="${ comListVar2.contenu }"/></p>
							     	  		</div>
							     	  		<c:import url="/inc/reponseCommentaire.jsp"/>
						       			</div>
							     	 
						       		</c:forEach> 
				       			</div>	--%>
			       		</div>	
		       		</c:forEach>
		       		
		       		</div>
       			</div>
			</div>
		</div>	
    </body>
</html>