<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Inscription</title>
</head>
<body>
	<div>
		<form method="post" action="<c:url value="/inscription"/>"> 
			<fieldset>
				<legend>Information client</legend>
				<c:import url="/inc/inscription.jsp"></c:import>
		
			</fieldset>
			<p class="info"> ${ form.result } </p>
			<input type="submit" value="Valider">
			<input type="reset" value="Annuler">
		</form>	
	</div>
	<p>
		${resultat}
	</p>
</body>
</html>