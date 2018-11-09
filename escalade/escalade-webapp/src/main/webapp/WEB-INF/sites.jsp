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
    	
        <title>Sites</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
	   
       	</br>
        
       	<p>
			<h3>Espace sites</h3>
			
       	</p>
       	
      	<div class="container">
      		<table class="table table-dark ">
				<thead>
					<tr>
						<th scope="col">Nom</th>
						<th scope="col">Département</th>
						<th scope="col">Lieu</th>
						<th scope="col">Nombre de secteurs</th>
						<th scope="col">Nombre de voies</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach items="${allSites}" var="allSitesVar">
						
						
						<tr>
							<td><a href="/escalade-webapp/sites/site?site_id=${allSitesVar.id}"><c:out value=" ${allSitesVar.nom}"/></a></td>
							<td><c:out value="${allSitesVar.dept}"/></td>
							<td><c:out value="${allSitesVar.lieu}"/></td>
							<td>
								<c:forEach items="${allSitesVar.secteurs}" var="allSecteursVar" varStatus="allSecteursVS">
									<c:set var="secteurCounter" value="${ allSecteursVS.count }"/>
								</c:forEach>
								<c:out value="${secteurCounter}"/>
							</td>
							<td>
								<c:forEach items="${allSitesVar.secteurs}" var="allSecteursVar" varStatus="allSecteursVS">
									<c:forEach items="${allSecteursVar.voies }" var="allVoiesVar" varStatus="allVoiesVS">
										<c:set var="voieCounter" value="${ allVoiesVS.count }"/>
									</c:forEach>
								</c:forEach>
								<c:out value="${voieCounter}"/>
							</td>
							
						
						</tr>
					</c:forEach>
				</tbody>      		
      		</table>
		</div>
		
    </body>
</html>