<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<form method="POST" action="<c:url value="/sites/creercommentaire/"/>">
	<div class="form-group ">
		<label for="commentaire">Répondre </label>
		<textarea class="form-control" id="commentaire" name="commentaire" placeholder="Laissez votre commentaire ..." ></textarea>
		<span class="text-danger">${sm.erreurs['commentaire']}</span>
	</div>
<div class="text-right">
	<button type="submit" class="btn btn-primary ">Répondre</button>
	<c:url value="/" var="retourVar"/>
	<a class="btn btn-success" role="button">Voir les réponses</a>
</div>
<sec:csrfInput/>
</form>

