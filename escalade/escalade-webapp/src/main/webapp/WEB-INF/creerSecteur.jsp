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
    	
        <title>Création Secteur</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
	    <div class="bg_creersecteur text-light pl-3 pt-3">
			<h3>Créer un nouveau secteur</h3>
	 	
			<div class="container col-sm-5 pt-5" id="creersecteur_block">
			
			
			<h3>Informations Secteur</h3>
				<c:if test="${not empty sessionScope.voies}">
					
					
						<h5>Voies</h5>
			      		<table class="table table-striped table-sm">
							<thead class="thead-dark">
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
								<c:forEach items="${sessionScope.voies}" var="voiesVar">
									<tr>
										<td><c:out value="${voiesVar.value.nom }"/></td>
										<td><c:out value="${voiesVar.value.hauteur }"/></td>
										<td><c:out value="${voiesVar.value.nombreLongueur }"/></td>
										<td><c:out value="${voiesVar.value.nombrePoints }"/></td>
										<td><c:out value="${voiesVar.value.cotation }"/></td>
												
										<td class="text-center"><a href="<c:url value="/sites/creersite/creersecteur/supprimervoie"><c:param name="voieSupp" value="${voiesVar.value.nom}" /></c:url>" ><c:out value="supprimer"/></a></td>
									</tr>
								</c:forEach>
							</tbody>
			      		</table>
				</c:if>	
								
			<form method="POST" action="<c:url value="/sites/creersite/creersecteur/"/>"> 
				<p>
					
					<a href="<c:url value="/sites/creersite/creervoie/"><c:param name="testSecteur" value="true"/></c:url>" class="btn btn-warning" role="button">Ajouter une voie</a>
				</p>
				<div class="form-group" >
					<label for="nomSecteur">Nom<span class="requis">*</span></label>
					<input type="text" class="form-control" id="nomSecteur" name="nomSecteur" placeholder="Nom du secteur" value="<c:out value="${secteur.nom}"/>" size="20" maxlength="200"/>
					<span class="text-danger">${secM.erreurs['nomSecteur']}</span>
				</div>
				<p>
					<button type="submit" class="btn btn-warning ">Créer secteur</button>
					<button type="reset"  class="btn btn-primary ">Annuler</button>
					<c:url value="/sites/creersite/" var="retourVar"/>
					<a href="${retourVar}" class="btn btn-info float-right" role="button">Retour</a>
				</p>
					<sec:csrfInput/>
				</form>
				<p class="info"> ${ secM.result } </p> 
			</div>
		</div>
    </body>
</html>
