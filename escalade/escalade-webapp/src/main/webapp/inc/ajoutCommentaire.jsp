<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<form method="POST" action="<c:url value="/sites/creercommentaire/"/>" class="pb-5">
	<div class="form-group ">
		<textarea class="form-control" id="contenuCom" name="contenuCom" rows="4" placeholder="Laissez votre commentaire ..."  ><c:out value="${sessionScope.commentaire.contenu}"/></textarea>
		<span class="text-danger">${sessionScope.comM.erreurs['contenuCom']}</span>
		<span class="text-danger">${sessionScope.comM.result}</span>
	</div>
	<input type="hidden" name="site_id" value="${site.id }">
	<button type="submit" class="btn btn-primary">Ajouter un commentaire</button>
	<sec:csrfInput/>
</form>

