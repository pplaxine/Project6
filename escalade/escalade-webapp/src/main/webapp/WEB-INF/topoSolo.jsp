<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta charset="utf-8" />
   		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	
    	<c:import url="/inc/bootstrapAndJQueryImport.jsp"/>
  
  		
    	
        <title>Topo ${topo.nom}</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
	   
       	<br/>
        
	    <div class="row">
	       	<div class="col-md-6">
		      	<p>
					<h3>Topo <c:out value="${topo.nom}"/> </h3>
		       	</p>
				<img alt="photo" src="<c:url value="/inc/img/escalade.png"/>">
				<p>
					Nom du topo : <c:out value="${topo.nom}"/><br/>
					Présentation : <c:out value="${topo.presentation}"/><br/>
				   	Site : <c:out value="${topo.site.nom}"/><br/>
				</p>
			</div>
			<div class="col-md-6 ">
				<div class="card float-right mr-4" style="width: 23rem;">
			   		<c:choose>
			   			<c:when test="${not empty topo.listLocationTopo }">
					   		<div class="card-header">
					   			Prochaines locations 
					   		</div>
						   	<ul class="list-group list-group-flush">
						   		<c:forEach items="${topo.listLocationTopo }" var="locationTopoVar">
							   		<c:if test="${locationTopoVar.accepte == true }">
							   			<li class="list-group-item">du ${ locationTopoVar.dateDebutLocationFormat} au ${locationTopoVar.dateFinLocationFormat } </li>
							   		</c:if>
						   		</c:forEach>
						   	</ul>
			   			</c:when>
			   			<c:otherwise>
			   				<div class="card-header">
					   			Prochaines locations 
					   		</div>
					   		<li class="list-group-item"><c:out value="Aucune"/> </li>
			   			</c:otherwise>
			   		</c:choose>
			   	
			   	</div> 
			</div>
		</div>
	   
		
		<div class="container">
			<form method="POST" action="<c:url value="/topo"/>"> 
				
			<h3>Demande de location</h3>
			<input type="hidden" name="dateDebutLocationDemande" value="2018-12-06T08:30:00" />
			<input type="hidden" name="dateFinLocationDemande" value="2018-04-06T18:30:00" />
			<input type="hidden" name="topo_id" value="${ topo.id }" />
			<sec:csrfInput/>
			<span class="text-danger">${tm.erreurs['dateDebutLocationDemande']}</span>
				<button type="submit" class="btn btn-warning">Valider</button>
				<button type="reset"  class="btn btn-primary">Annuler</button>

			</form>	
			<p class="info"> ${ tm.result } </p>
			
		</div>
 
		   	 
    </body>
</html>