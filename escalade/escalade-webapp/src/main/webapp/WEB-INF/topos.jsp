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
    	
        <title>Topo</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
	   
       	</br>
        
       	<p>
			<h3>Espace prêt de topos</h3>
			
       	</p>

      	<div class="container">
      		<table class="table table-dark ">
				<thead>
					<tr>
						<th scope="col">Nom du topo</th>
						<th scope="col">Site</th>
						<th scope="col">Topo libre</th>
						<th scope="col">Date fin de location</th>
						<th scope="col">Fourni par</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allTopos}" var="allToposVar">
						<tr>
							<td><a href="/escalade-webapp/topos/topo?topo_id=${allToposVar.id}"><c:out value=" ${allToposVar.nom}"/></a></td>
							<td>
								<c:out value="${allToposVar.site.nom}"/>
							</td>
							<td>
								<c:choose>
									<c:when test="${allToposVar.disponible}">Libre</c:when>
									<c:otherwise>En location</c:otherwise>
								</c:choose>
							</td>
							<td><c:out value="${allToposVar.dateFinLocation}"/></td>
							<td><c:out value="${allToposVar.preteur}"/></td>
						</tr>
					</c:forEach>
				</tbody>      		
      		</table>
		</div>
    </body>
</html>