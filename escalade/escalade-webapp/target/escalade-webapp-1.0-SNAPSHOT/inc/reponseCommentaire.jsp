<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<form method="POST" action="<c:url value="/sites/creercommentaire/"/>">
	<div class="form-group ">
		<label for="commentaire">Répondre </label>
		<textarea class="form-control" id="contenuRepCom" name="contenuRepCom" placeholder="Laissez votre commentaire ..." ><c:out value="${sessionScope.commentaire.contenu}"/></textarea>
		<span class="text-danger">${sessionScope.comM.erreurs['contenuCom']}</span>
		<span class="text-danger">${sessionScope.comM.result}</span>
	</div>
	<div class="text-right">
		<button type="submit" class="btn btn-primary">Répondre</button>
		<a class="btn btn-success" data-toggle="collapse" href="#repCol" role="button" aria-expanded="false" aria-controls="collapseExample">Voir les réponses</a>
	</div>
	<input type="hidden" name="site_id" value="${site.id }">
	<sec:csrfInput/>
</form>

