<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
    <head>
    
   		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	
    	<c:import url="/inc/bootstrapAndJQueryImport.jsp"/> 
    	
        <title>Espace Sites</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
    <div class="bg_sites">
	   <h3 class="h1 pt-4 pr-3">Espace sites</h3>
       	
        
       	
     
       	<div class="container">
			
       	
       		<div class="input-group bg-dark p-2 rounded ">
       			<div class="container-fluid ">
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
	  				
	  				<div class="float-right">
	    				<button type="submit" class="btn btn-info btn-warning ml-2 rounded">Rechercher</button>
	  				</div>
	  				<input type="hidden" name="allSitesParam" value="false">
					
					<sec:csrfInput/>
	  				</form>
  				</div>
			</div>
	       	<br/>
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
											<c:choose>
												<c:when test="${not empty allSitesVar.secteurs}">
													<c:forEach items="${allSitesVar.secteurs}" var="allSecteursVar" varStatus="allSecteursVS">
														<c:if test="${not empty allSecteursVar.voies }">
															<c:forEach items="${allSecteursVar.voies }" var="allVoiesVar" varStatus="allVoiesVS">
																<c:set var="voieCounter" value="${ allVoiesVS.count }"/>
															</c:forEach>
															<c:out value="S${ allSecteursVS.count}:${voieCounter}v "/>
														</c:if>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach items="${allSitesVar.voies}" var="allVoiesSiteVar" varStatus="allVoiesSiteVS">
														<c:set var="voieSiteCounter" value="${ allVoiesSiteVS.count }"/>
													</c:forEach>
													<c:out value="${voieSiteCounter}"/>
												</c:otherwise>
											</c:choose>
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
												<c:choose>
													<c:when test="${not empty allSitesVar.secteurs}">
														<c:forEach items="${allSitesVar.secteurs}" var="allSecteursVar" varStatus="allSecteursVS">
															<c:if test="${not empty allSecteursVar.voies }">
																<c:forEach items="${allSecteursVar.voies }" var="allVoiesVar" varStatus="allVoiesVS">
																	<c:set var="voieCounter" value="${ allVoiesVS.count }"/>
																</c:forEach>
																<c:out value="S${ allSecteursVS.count}:${voieCounter}v "/>
															</c:if>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<c:forEach items="${allSitesVar.voies}" var="allVoiesSiteVar" varStatus="allVoiesSiteVS">
															<c:set var="voieSiteCounter" value="${ allVoiesSiteVS.count }"/>
														</c:forEach>
														<c:out value="${voieSiteCounter}"/>
													</c:otherwise>
												</c:choose>
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
		</div>
    </body>
</html>