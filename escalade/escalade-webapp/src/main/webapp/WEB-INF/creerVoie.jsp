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
    	
        <title>Création voie</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
    	
    	<br/>
    	
    	<p>
			<h3>Créer une nouvelle voie</h3>
       	</p>
 	
		<div class="container">
		
		<form method="POST" action="<c:url value="/sites/creersite/creervoie/"/>"> 
		
			<h3>Informations Voie</h3>
							
			<div class="form-group" >
				<label for="nomVoie">Nom<span class="requis">*</span></label>
				<input type="text" class="form-control" id="nomVoie" name="nomVoie" placeholder="Nom de la voie" value="<c:out value="${voie.nom}"/>" size="20" maxlength="20"/>
				<span class="text-danger">${vm.erreurs['nomVoie']}</span>
			</div>
			<div class="form-group">
				<label for="hauteurVoie">Hauteur (en mètre)<span class="requis">*</span></label>
				<input type="number" step="0.01" min="0" class="form-control" id="hauteurVoie" name="hauteurVoie" placeholder="11.18" value="<c:out value="${voie.hauteur}"/>" size="20" maxlength="20"/>	<%-- value="<c:out value="${voie.hauteur}"/>"  --%>
				<span class="text-danger">${vm.erreurs['hauteurVoie']}</span>
			</div>
			<div class="form-group">
				<label for="nombreLongueursVoie">Nombre de Longueurs<span class="requis">*</span></label>
				<input type="number" value="0" min="0" class="form-control" id="nombreLongueursVoie" name="nombreLongueursVoie" />
				
			</div>
			<div class="form-group">
			
				<label for="nombrePointsVoie">Nombre de Points<span class="requis">*</span></label>
				<input type="number" value="1" min="0" class="form-control"  value="1" id="nombrePointsVoie" name="nombrePointsVoie" />
			</div>
			<div class="form-group">
				<label for="cotationVoie">Cotation<span class="requis">*</span></label>
				<select class="form-control" id="cotationVoie" name="cotationVoie">
					<c:forEach items="${listCotation}" var="listCotationVar">
						<option><c:out value="${listCotationVar}"/></option>
					</c:forEach>
				</select> 
			</div>
			<sec:csrfInput/>
			<p>
				<button type="submit" class="btn btn-warning ">Ajouter voie</button>
				<button type="reset"  class="btn btn-primary ">Annuler</button>
				<c:url value="/sites/creersite/" var="retourSiteVar"/>
				<c:url value="/sites/creersite/creersecteur/" var="retourSecteurVar"/>
				<c:choose>
					<c:when test="${ not empty testSecteur }">
						<a href="${retourSecteurVar}" class="btn btn-info float-right" role="button">Retour</a>
					</c:when>
					<c:otherwise>
						<a href="${retourSiteVar}" class="btn btn-info float-right" role="button">Retour</a>
					</c:otherwise>
				</c:choose>
			</p>
			
			</form>
		
			<p class="info"> ${ vm.result } </p> 
		</div>
    </body>
</html>
