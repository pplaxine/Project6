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
	
		<title>Inscription</title>
	
	</head>
	<body>
		<div class="container">
			<form method="POST" action="<c:url value="/inscription/"/>"> 
				
					<h3>Information client</h3>
					
					<div class="form-group">
						<label for="nomCompteUtilisateur">Nom <span class="requis">*</span></label>
						<input type="text" class="form-control" id="nomCompteUtilisateur" name="nomCompteUtilisateur" value="<c:out value="${cu.nom}"/>" size="20" maxlength="20"/>
						<span class="text-danger">${cum.erreurs['nomCompteUtilisateur']}</span>
					</div>
					
					<div class="form-group">
						<label for="prenomCompteUtilisateur">Prénom <span class="requis">*</span></label>
						<input type="text" class="form-control" id="prenomCompteUtilisateur" name="prenomCompteUtilisateur" value="<c:out value="${cu.prenom}"/>" size="20" maxlength="20"/>
						<span class="text-danger">${cum.erreurs['prenomCompteUtilisateur']}</span>
					</div>
				
					<div class="form-group">	
						<label for="pseudoCompteUtilisateur">Pseudo <span class="requis">*</span></label>
						<input type="text" class="form-control" id="pseudoCompteUtilisateur" name="pseudoCompteUtilisateur" value="<c:out value="${cu.pseudo}"/>" size="20" maxlength="20"/>
						<span class="text-danger">${cum.erreurs['pseudoCompteUtilisateur']}</span>
					</div>	
					
					<div class="form-group">
						<label for="emailCompteUtilisateur">Adresse email<span class="requis">*</span></label>
						<input type="email" class="form-control" id="emailCompteUtilisateur" name="emailCompteUtilisateur" value="<c:out value="${cu.email}"/>" size="30" maxlength="60" />
						<span class="text-danger">${cum.erreurs['emailCompteUtilisateur']}</span>
					</div>
						
					<div class="form-group">
						<label for="mdpCompteUtilisateur">Mot de passe <span class="requis">*</span></label>
						<input type="password" class="form-control" id="mdpCompteUtilisateur" name="mdpCompteUtilisateur" size="30" maxlength="30" />
						<span class="text-danger">${cum.erreurs['mdpCompteUtilisateur']}</span>
					</div>	
					
					<div class="form-group">
						<label for="mdpCompteUtilisateurConf">Confirmation du mot de passe <span class="requis">*</span></label>
						<input type="password" class="form-control" id="mdpCompteUtilisateurConf" name="mdpCompteUtilisateurConf" size="30" maxlength="30" />
					</div>
					
				
				
				<sec:csrfInput/>
				<button type="submit" class="btn btn-warning">Valider</button>
				<button type="reset"  class="btn btn-primary">Annuler</button>
				
				<c:url value="/" var="retourVar"/>
				<a href="${retourVar}" class="btn btn-info float-right" role="button">Retour</a>
			</form>	
			<p class="info"> ${ cum.result } </p>
		</div>
	
	</body>
</html>