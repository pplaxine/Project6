<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 	
<div class="container">
<form method="POST" action="<c:url value="#"/>"> 
				
	<h5>création secteur</h5>
					
	<div class="form-group">
		<label for="nomSite">Nom <span class="requis">*</span></label>
		<input type="text" class="form-control" id="nomSite" name="nomSite" value="<c:out value="${site.nom}"/>" size="20" maxlength="20"/>
		<span class="text-danger">${site.erreurs['nomSite']}</span>
	</div>
	
	<p>
		<button type="submit" class="btn btn-warning">Ajouter un secteur</button>
	</p>
	<p class="info"> ${ cum.result } </p>
</div>
