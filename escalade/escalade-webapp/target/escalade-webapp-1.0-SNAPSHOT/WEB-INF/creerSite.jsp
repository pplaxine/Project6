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
    	
        <title>Création Site</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
		<div class="bg_creersite">
			<h3 class="h2 pt-3 pb-2 pl-3">Créer un nouveau site</h3>
	       	<div class="container rounded pt-3 pb-4" id="creersite_block">
						<h3 class="pb-3 text-light" >Informations Site</h3>
						<c:if test="${not empty sessionScope.voiesSite}">
					      		<table class="table table-dark table-sm ">
									<thead>
										<tr>
											<th scope="col">Nom</th>
											<th scope="col">Hauteur</th>
											<th scope="col">Nombre Longueurs</th>
											<th scope="col">Nombre Points</th>
											<th scope="col">Cotation</th> 
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${sessionScope.voiesSite}" var="voiesSiteVar">
											<tr>
												<td><c:out value="${voiesSiteVar.value.nom }"/></td>
												<td><c:out value="${voiesSiteVar.value.hauteur }"/></td>
												<td><c:out value="${voiesSiteVar.value.nombreLongueur }"/></td>
												<td><c:out value="${voiesSiteVar.value.nombrePoints }"/></td>
												<td><c:out value="${voiesSiteVar.value.cotation }"/></td>
														
												<td class="text-center"><a href="<c:url value="/sites/creersite/creersecteur/supprimervoie"><c:param name="voieSupp" value="${voiesSiteVar.value.nom}" /></c:url>" ><c:out value="supprimer"/></a></td>
												
											</tr>
											
										</c:forEach>
									</tbody>
					      		</table>
						
						</c:if>
						<c:if test="${not empty sessionScope.secteurs}">
						
					      		<table class="table table-dark table-sm table-sm">
									<thead>
										<tr>
											<th scope="col">Nom</th>
											<th scope="col">Voie</th>
											<th scope="col"></th>
										</tr>
									</thead >
									<tbody>
										<c:forEach items="${sessionScope.secteurs}" var="secteursVar">
											<tr>
												<td><c:out value="${secteursVar.value.nom }"/></td>
												<td>
													<c:forEach items="${secteursVar.value.voies}" var="voiesVar">
														<c:out value="${voiesVar.nom}"/>
													</c:forEach>
												</td>
												<td class="text-center"><a href="<c:url value="/sites/creersite/supprimersecteur"><c:param name="secteurSupp" value="${secteursVar.value.nom }" /></c:url>" ><c:out value="supprimer"/></a></td>
											</tr>
											
										</c:forEach>
									</tbody>
					      		</table>
						</c:if>
				
					<form method="POST" action="<c:url value="/sites/creersite/"/>">
						<p>
							
							<c:if test="${empty sessionScope.voiesSite}">
								<c:out value="Vous pouvez ajouter un ou plusieurs secteurs à votre site"/><br/>
								<a href="<c:url value="/sites/creersite/creersecteur/" />" class="btn btn-warning" role="button">Ajouter un secteur</a>
							</c:if><br/><br/>	
							<c:if test="${empty sessionScope.secteurs }">
								<c:if test="${empty sessionScope.voiesSite}">
									<c:out value="Si votre site ne possède pas de secteurs vous pouvez créer directement vos voies"/><br/>			
								</c:if>
								<a href="<c:url value="/sites/creersite/creervoie/" />" class="btn btn-warning" role="button">Ajouter une voie</a>
							</c:if>
						</p>
						<br/>
						
						<div class="form-group">
							<label for="nomSite">Nom <span class="requis">*</span></label>
							<input type="text" class="form-control" id="nomSite" name="nomSite" placeholder="Nom du site" value="<c:out value="${site.nom}"/>" size="20" maxlength="20"/>
							<span class="erreur">${sm.erreurs['nomSite']}</span>
						</div>
						
						<div class="form-group">
							<label for="deptSite">Département<span class="requis">*</span></label>
							<select  class="form-control" name="deptSite" id="deptSite">
								<c:forEach items="${listDept}" var="listDeptVar">
									<option value="${ listDeptVar }"><c:out value="${listDeptVar}"/></option>
								</c:forEach>
							</select> 
						</div>
					
						<div class="form-group">	
							<label for="lieuSite">Lieu <span class="requis">*</span></label>
							<input type="text" class="form-control" id="lieuSite" name="lieuSite" placeholder="Lieu du site" value="<c:out value="${site.lieu}"/>" size="20" maxlength="400"/>
							<span class="erreur">${sm.erreurs['lieuSite']}</span>
						</div>	
							
						<div class="form-group">
							<label for="decriptionlieu">Description </label>
							<textarea class="form-control" id="descriptionSite" name="descriptionSite" rows="5" placeholder="La description du site ..." ></textarea>
							<span class="erreur">${sm.erreurs['descriptionSite']}</span>
						</div>
						<sec:csrfInput/>
						
					
						<button type="submit" class="btn btn-warning">Valider</button>
						<button type="reset"  class="btn btn-primary">Annuler</button>
				
						<c:url value="/topo/creertopo/" var="retourTopoVar"/>
						<c:url value="/home" var="retourVar"/>
						<c:choose>
							<c:when test="${ not empty requestFromTopo }">
								<a href="${retourTopoVar}" class="btn btn-info float-right" role="button">Retour</a>
							</c:when>
							<c:otherwise>
								<a href="${retourVar}" class="btn btn-info float-right" role="button">Retour</a>
							</c:otherwise>
						</c:choose>
						<p class="h5 pt-3 text-danger"> ${ sm.result }</p><br/>
					</form>	
			</div>
		</div> 
    </body>
</html>