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
    	
        <title>Création Site</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
	   
       	<br/>
        
       	<p>
			<h3>Créer un nouveau site</h3>
       	</p>
       	
       	<div class="container">
			<form method="POST" action="<c:url value="/sites/creersite/"/>"> 
				
					<h3>Informations Site</h3>
					
					<div class="form-group">
						<label for="nomSite">Nom <span class="requis">*</span></label>
						<input type="text" class="form-control" id="nomSite" name="nomSite" placeholder="Nom du site" value="<c:out value="${site.nom}"/>" size="20" maxlength="20"/>
						<span class="text-danger">${sm.erreurs['nomSite']}</span>
					</div>
					
					<div class="form-group">
						<label for="deptSite">Département<span class="requis">*</span></label>
						<select id="deptSite" class="form-control" name="deptSite">
							<c:forEach items="${listDept}" var="listDeptVar">
								<option value="${ listDeptVar }"><c:out value="${listDeptVar}"/></option>
							</c:forEach>
						</select> 
					</div>
				
					<div class="form-group">	
						<label for="lieuSite">Lieu <span class="requis">*</span></label>
						<input type="text" class="form-control" id="lieuSite" name="lieuSite" placeholder="Lieu du site" value="<c:out value="${site.lieu}"/>" size="20" maxlength="20"/>
						<span class="text-danger">${sm.erreurs['lieuSite']}</span>
					</div>	
						
					<div class="form-group">
						<label for="decriptionlieu">Description </label>
						<textarea class="form-control" id="descriptionSite" name="descriptionSite" rows="5" placeholder="La description du site ..." ></textarea>
						<span class="text-danger">${sm.erreurs['descriptionSite']}</span>
					</div>
					<sec:csrfInput/>
					<c:if test="${not empty sessionScope.voiesSite}">
						<div class="container">
				      		<table class="table table-dark">
								<thead>
									<tr>
										<th scope="col">Nom</th>
										<th scope="col">Hauteur</th>
										<th scope="col">Nombre Longueurs</th>
										<th scope="col">Nombre Points</th>
										<th scope="col">Cotation</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${sessionScope.voiesSite}" var="voiesSiteVar">
										<tr>
											<td><c:out value="${voiesSiteVar.value.nom }"/></td>
											<td><c:out value="${voiesSiteVar.value.hauteur }"/></td>
											<td><c:out value="${voiesSiteVar.value.nombreLongueur }"/></td>
											<td><c:out value="${voiesSiteVar.value.nombrePoints }"/></td>
											<td><c:out value="${voiesSiteVar.value.cotation }"/></td>${voiesVar.value.nom}
													
											<td><a href="<c:url value="/sites/creersite/creersecteur/supprimervoie"><c:param name="voieSupp" value="${voiesSiteVar.value.nom }" /></c:url>" ><c:out value="supprimer"/></a></td>
											
										</tr>
										
									</c:forEach>
								</tbody>
				      		</table>
						</div>
					</c:if>
					<p>
						<c:forEach items="${sessionScope.secteurs}" var="secteursVar">
							<strong>Secteur : </strong><c:out value="${secteursVar.value.nom }"/>
							
							<c:forEach items="${secteursVar.value.voies}" var="voiesVar">
								<strong>voie : </strong><c:out value="${voiesVar.nom}"/>
							</c:forEach>
							
							<br/>
						</c:forEach>
					</p>
					<p>
						<c:if test="${empty sessionScope.voiesSite}">
							<a href="<c:url value="/sites/creersite/creersecteur/" />" class="btn btn-warning" role="button">Ajouter un secteur</a>
						</c:if>					
						<a href="<c:url value="/sites/creersite/creervoie/" />" class="btn btn-warning" role="button">Ajouter une voie</a>
					</p>
					<br/>
				
				<button type="submit" class="btn btn-warning">Valider</button>
				<button type="reset"  class="btn btn-primary">Annuler</button>
				
				<c:url value="/" var="retourVar"/>
				<a href="${retourVar}" class="btn btn-info float-right" role="button">Retour</a>
			</form>	
			<p class="info"> ${ sm.result } </p>
		</div>
		
    </body>
</html>