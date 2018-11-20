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
    	
        <title>Création Topo</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
	   
       	<br/>
        
       	<p>
			<h3>Créer un nouveau Topo</h3>
       	</p>
       	
       	<div class="container">
			<h3>Informations Topo</h3>
			<c:if test="${not empty sessionScope.siteTopo}">
				<div class="container">
      				<table class="table table-sm table-dark ">
						<thead>
							<tr>
								<th scope="col">Nom</th>
								<th scope="col">Département</th>
								<th scope="col">Lieu</th>
								<th scope="col">Nombre de secteurs</th>
								<th scope="col">Nombre de voies</th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>			
							<tr>
								<td><c:out value="${siteTopo.nom}"/></td>
								<td><c:out value="${siteTopo.dept}"/></td>
								<td><c:out value="${siteTopo.lieu}"/></td>
								<td>
									<c:if test="${not empty siteTopo.secteurs}">
										<c:forEach items="${siteTopo.secteurs}" var="allSecteursVar" varStatus="allSecteursVS">
											<c:set var="secteurCounter" value="${ allSecteursVS.count }"/>
										</c:forEach>
										<c:out value="${secteurCounter}"/>
									</c:if>
								</td>
								<td>
									<c:if test="${not empty siteTopo.secteurs}">
										<c:forEach items="${siteTopo.secteurs}" var="allSecteursVar" varStatus="allSecteursVS">
											<c:forEach items="${siteTopo.voies }" var="allVoiesVar" varStatus="allVoiesVS">
												<c:set var="voieCounter" value="${ allVoiesVS.count }"/>
											</c:forEach>
										</c:forEach>
										<c:out value="${voieCounter}"/>
									</c:if>
									<c:forEach items="${siteTopo.voies}" var="allVoiesSiteVar" varStatus="allVoiesSiteVS">
										<c:set var="voieSiteCounter" value="${ allVoiesSiteVS.count }"/>
									</c:forEach>
									<c:out value="${voieSiteCounter}"/>
								</td>
								<td class="text-center"><a href="<c:url value="/topo/creertopo/supprimersite/"/>" ><c:out value="supprimer"/></a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:if>
			
			<form method="POST" action="<c:url value="/topo/creertopo/"/>">
				<p>
					
					<c:if test="${empty sessionScope.siteTopo}">
						<a href="<c:url value="/sites/creersite/"><c:param name="topo" value="true" /></c:url>" class="btn btn-warning" role="button">Ajouter un site</a>
					</c:if><br/><br/>	
				</p>
				<br/>
				
				<div class="form-group">
					<label for="nomTopo">Nom <span class="requis">*</span></label>
					<input type="text" class="form-control" id="nomTopo" name="nomTopo" placeholder="Nom du topo" value="<c:out value="${topo.nom}"/>" size="20" maxlength="20"/>
					<span class="text-danger">${sm.erreurs['nomTopo']}</span>
				</div>
				
				<div class="form-group">
					<label for="presentationTopo">Présentation<span class="requis">*</span></label>
					<textarea class="form-control" id="presentationTopo" name="presentationTopo" rows="5" placeholder="Description du topo"><c:out value="${topo.presentation}"/></textarea> 
				</div>
				<sec:csrfInput/>
			
				<button type="submit" class="btn btn-warning">Valider</button>
				<button type="reset"  class="btn btn-primary">Annuler</button>
				
				<c:url value="/topo/" var="retourVar"/>
				<a href="${retourVar}" class="btn btn-info float-right" role="button">Retour</a>
			</form>	
			<p class="info"> ${ sm.result } </p>
		</div>
		
    </body>
</html>