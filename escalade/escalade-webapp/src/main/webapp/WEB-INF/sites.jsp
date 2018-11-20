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
    	
        <title>Sites</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
	   
       	</br>
        
       	<p>
			<h3>Espace sites</h3>
			
       	</p>
       	<div class="container-fluid ">
       		<div class="input-group bg-dark p-2 rounded">
       			<div class="container-fluid col-md-10">
	       			<form method="Post" class="form form-inline " action="<c:url value="/sites/"/>" >
	  				<div class="input-group-prepend ">
	    				<span class="input-group-text  bg-dark text-warning" id="">Département</span>
	  				</div>
	  				<select  class="form-control mr-2" name="deptRech" id="deptRech">
						<c:forEach items="${listDept}" var="listDeptVar">
							<option value="${ listDeptVar }"><c:out value="${listDeptVar}"/></option>
						</c:forEach>
					</select> 
	  				
	  				<div class="input-group-prepend">
	    				<span class="input-group-text bg-dark text-warning" id="">Topo disponible</span>
	  				</div>
	  				<select  class="form-control " name="topoRech" id="topoRech" >
						<option value="TOUS"><c:out value="- Tous -"/></option>
						<option value="true"><c:out value="Disponible"/></option>
						<option value="false"><c:out value="Non disponible"/></option>
					</select> 
	  				
	  				<div class="input-group-prepend">
	    				<button type="submit" class="btn btn-info btn-warning float-right ml-2 rounded">Rechercher</button>
	  				</div>
	  				<input type="hidden" name="allSitesParam" value="false">
					
					<sec:csrfInput/>
	  				</form>
  				</div>
  				<div class="container col-md-2">
	  				<form method="Post" class="form form-inline" action="<c:url value="/sites/"/>" >
	  					<input type="hidden" name="allSitesParam" value="true">
		    			<button type="submit" class="btn btn-info btn-warning rounded"> Tous les sites</button>
						<sec:csrfInput/>
	  				</form>
  				</div>
			</div>
       	</div><br/>
	
       	
      	
      	<div class="container">
      		<table class="table table-dark ">
				<thead>
					<tr>
						<th scope="col">Nom</th>
						<th scope="col">Département</th>
						<th scope="col">Lieu</th>
						<th scope="col">Nombre de secteurs</th>
						<th scope="col">Nombre de voies</th>
						<th scope="col">Topo</th>
						
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ sitesParam == true }">	
							<c:forEach items="${allSites}" var="allSitesVar">
								<tr>
									<td><a href="/escalade-webapp/sites/site?site_id=${allSitesVar.id}"><c:out value=" ${allSitesVar.nom}"/></a></td>
									<td><c:out value="${allSitesVar.dept}"/></td>
									<td><c:out value="${allSitesVar.lieu}"/></td>
									<td>
										<c:if test="${not empty allSitesVar.secteurs}">
											<c:forEach items="${allSitesVar.secteurs}" var="allSecteursVar" varStatus="allSecteursVS">
												<c:set var="secteurCounter" value="${ allSecteursVS.count }"/>
											</c:forEach>
											<c:out value="${secteurCounter}"/>
										</c:if>
									</td>
									<td>
										<c:if test="${not empty allSitesVar.secteurs}">
											<c:forEach items="${allSitesVar.secteurs}" var="allSecteursVar" varStatus="allSecteursVS">
												<c:forEach items="${allSecteursVar.voies }" var="allVoiesVar" varStatus="allVoiesVS">
													<c:set var="voieCounter" value="${ allVoiesVS.count }"/>
												</c:forEach>
											</c:forEach>
											<c:out value="${voieCounter}"/>
										</c:if>
										<c:forEach items="${allSitesVar.voies}" var="allVoiesSiteVar" varStatus="allVoiesSiteVS">
											<c:set var="voieSiteCounter" value="${ allVoiesSiteVS.count }"/>
										</c:forEach>
										<c:out value="${voieSiteCounter}"/>
									</td>
									<td>
										<c:choose>
											<c:when test="${allSitesVar.topo}">Topo disponible</c:when>
											<c:otherwise>Pas de topo disponible</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</c:when>

						<c:otherwise>
							<c:forEach items="${allSites}" var="allSitesVar">
								<c:out value=" TOPO  ${allSitesVar.topo }"/>
						 		<c:if test="${ (allSitesVar.dept == deptRechV || deptRechV =='TOUS') && (allSitesVar.topo == topoRechV || topoRechV == 'TOUS' ) }">
									<tr>
										<td><a href="/escalade-webapp/sites/site?site_id=${allSitesVar.id}"><c:out value=" ${allSitesVar.nom}"/></a></td>
										<td><c:out value="${allSitesVar.dept}"/></td>
										<td><c:out value="${allSitesVar.lieu}"/></td>
										<td>
											<c:if test="${not empty allSitesVar.secteurs}">
												<c:forEach items="${allSitesVar.secteurs}" var="allSecteursVar" varStatus="allSecteursVS">
													<c:set var="secteurCounter" value="${ allSecteursVS.count }"/>
												</c:forEach>
												<c:out value="${secteurCounter}"/>
											</c:if>
										</td>
										<td>
											<c:if test="${not empty allSitesVar.secteurs}">
												<c:forEach items="${allSitesVar.secteurs}" var="allSecteursVar" varStatus="allSecteursVS">
													<c:forEach items="${allSecteursVar.voies }" var="allVoiesVar" varStatus="allVoiesVS">
														<c:set var="voieCounter" value="${ allVoiesVS.count }"/>
													</c:forEach>
												</c:forEach>
												<c:out value="${voieCounter}"/>
											</c:if>
											<c:forEach items="${allSitesVar.voies}" var="allVoiesSiteVar" varStatus="allVoiesSiteVS">
												<c:set var="voieSiteCounter" value="${ allVoiesSiteVS.count }"/>
											</c:forEach>
											<c:out value="${voieSiteCounter}"/>
										</td>
										<td>
											<c:choose>
												<c:when test="${allSitesVar.topo}">Topo disponible</c:when>
												<c:otherwise>Pas de topo disponible</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:otherwise>	
					</c:choose>
				</tbody>      		
      		</table>
		</div>
    </body>
</html>