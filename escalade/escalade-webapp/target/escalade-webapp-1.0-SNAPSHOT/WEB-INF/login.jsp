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

	<title >Login</title>
</head>
<body>
	<div class="bg_login d-flex align-items-center pb-5">
		<div class="container text-light">
			<div class="row justify-content-center">
				<div class="col-lg-5">
					<h1>Login</h1>
					<c:url value="/login" var="loginVar"/>
					<form id="login-form" method="POST" action="${loginVar}">
						<div class="form-group">
							<label for="user_name">Pseudo</label>
							<input id="user_name" name="user_name" class="form-control"/>
						</div>
						<div class="form-group">
							<label for="user_pw">Password</label>
							<input type="password" id="user_pw" name="user_pw" class="form-control"/>
						</div>
						
						<sec:csrfInput/>
						<c:if test="${param.error != null }">
							<span class="erreur rounded ">- Invalide Username or Password -</span><br/>
						</c:if>
						<br/>
						<button type="submit" id="btn-save" class="btn btn-warning">Login</button>
						<button type="reset" class="btn btn-primary">cancel</button>
						
						<c:url value="/home" var="retourVar"/>
						<a href="${retourVar}" class="btn btn-info float-right" role="button">Retour</a>
					</form>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>