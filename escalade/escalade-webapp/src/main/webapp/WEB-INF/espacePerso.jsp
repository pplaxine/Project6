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
    	
        <title>Espace Prêt de Topo</title>
    </head>
    
    <body>
		<c:import url="/inc/navBar.jsp"/>
	    <div class="bg">
		    </br>
		    
		    <h3>Votres espace Perso</h3>  
	        <div class="row">
	        	<div class="col sm-12">
		      		<h5>Vos demandes de location de topos traités</h5><br/>
		      		<div class="container">
		      			<c:forEach items="${ listTopoUser }" var="topoUserVar">
							<h4>${topoUserVar.nom }</h4>
							<c:choose>
				      			<c:when test="${not empty topoUserVar.listLocationTopo }">
							      		<table class="table table-dark table-sm">
											<thead>
												<tr>
													<th scope="col">Demandeur</th>
													<th scope="col">Date début de location</th>
													<th scope="col">Date Fin de location</th>
													<th scope="col">Statut</th>
													<th scope="col"></th>
													<th scope="col"></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${topoUserVar.listLocationTopo }" var="locationTopoVar">
														<c:choose>
															<c:when test="${locationTopoVar.accepte == true }">
																<tr>
																	<td>${ locationTopoVar.emprunteur }</td>
																	<td>${ locationTopoVar.dateDebutLocationFormat }</td>
																	<td>${ locationTopoVar.dateFinLocationFormat }</td>
																	
																	<td>
																		<c:choose>
																			<c:when test="${ locationTopoVar.accepte == true }"><span class="text-success"><c:out value="Demande acceptée"/></span></c:when>
																		</c:choose>
																	</td>
																	<td class="text-center"><a class="text-danger" href="<c:url value="/topo/refuserlocation"><c:param name="location" value="${locationTopoVar.id}" /></c:url>" ><c:out value="Refuser"/></a></td>
																</tr>
															</c:when>
															<c:when test="${locationTopoVar.accepte == false }">
																<tr>
																	<td>${ locationTopoVar.emprunteur }</td>
																	<td>${ locationTopoVar.dateDebutLocationFormat }</td>
																	<td>${ locationTopoVar.dateFinLocationFormat }</td>
																	<td>
																		<c:choose>
																			<c:when test="${ locationTopoVar.accepte == false }"><span class="text-danger"><c:out value="Demande refusée"/></span></c:when>
																		</c:choose>
																	</td>
																	<td class="text-center"><a class="text-warning" href="<c:url value="/topo/accepterlocation"><c:param name="location" value="${locationTopoVar.id}" /></c:url>" ><c:out value="Accepter"/></a></td>
																</tr>
															</c:when>
														</c:choose>
												</c:forEach>								
											</tbody>
										</table>
				      			</c:when>
				      			<c:otherwise>
				      				<c:out value="Aucune demande de réservation"/>
				      			</c:otherwise>
							</c:choose>
					</c:forEach>
		      		</div>
		      	</div>
		      	
		      	<div class="col-sm-12">
		      		<h5>Vos demandes de location de topos en attente d'une réponse</h5><br/>
		      		<div class="container">
		      			<c:forEach items="${ listTopoUser }" var="topoUserVar">
							<h4>${topoUserVar.nom }</h4>
							<c:choose>
				      			<c:when test="${not empty topoUserVar.listLocationTopo }">
						      		<table class="table table-dark table-sm">
										<thead>
											<tr>
												<th scope="col">Demandeur</th>
												<th scope="col">Date début de location</th>
												<th scope="col">Date Fin de location</th>
												<th scope="col"></th>
												<th scope="col"></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${topoUserVar.listLocationTopo }" var="locationTopoVar">
												<c:if test="${locationTopoVar.accepte == null }">
													<tr>
														<td>${ locationTopoVar.emprunteur }</td>
														<td>${ locationTopoVar.dateDebutLocationFormat }</td>
														<td>${ locationTopoVar.dateFinLocationFormat }</td>
														<td class="text-center"><a class="text-warning" href="<c:url value="/topo/accepterlocation"><c:param name="location" value="${locationTopoVar.id}" /></c:url>" ><c:out value="Accepter"/></a></td>
														<td class="text-center"><a class="text-danger" href="<c:url value="/topo/refuserlocation"><c:param name="location" value="${locationTopoVar.id}" /></c:url>" ><c:out value="Refuser"/></a></td>
													</tr>
												</c:if>
											</c:forEach>								
										</tbody>
									</table>		
				      			</c:when>
				      			<c:otherwise>
				      				<c:out value="Aucune demande de location"/>
				      			</c:otherwise>
							</c:choose>
					</c:forEach>
		      		</div>
		      		
		      	</div>
		      	<div class="col-sm-12">
		      		<h5>Vos demandes de location de topo</h5><br/>
		      		<div class="container">
		      			
		      		</div>
		      		
		      	</div>
	    	</div>
	    </div>
        
       
            
    </body>
</html>