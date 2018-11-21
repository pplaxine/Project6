<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	
	<a class="navbar-brand" href="<c:url value="/apropos/" />">Escale'add</a>
  			
	<div class="collapse navbar-collapse" id="navbarNav">
   		
   		<ul class="navbar-nav ">
    		<li class="nav-item">
       			<a class="nav-link" href="<c:url value="/" />">Home<span class="sr-only">(current)</span></a>
     		</li>
     		
     		<li class="nav-item dropdown">
     			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" id="navbarDropdownSiteLink"  aria-haspopup="true" aria-expanded="false">
		          Sites
		        </a>
		        <div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdownSiteLink">
		        	<ul>
		        		<li>
		        			<a class="dropdoawn-item text-light" href="<c:url value="/sites/" />">Tous les sites</a>
		        		</li>
		        		<li>
		        			<a class="dropdoawn-item text-light " href="<c:url value="/sites/creersite/" />">Créer un site</a>
		        		</li>
		        	</ul>
		        </div>
     		 </li>
     		 
     		 
      		<li class="nav-item dropdown">
      			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" id="navbarDropdownTopoLink"  aria-haspopup="true" aria-expanded="false">
		          Espace prêt de topo
		        </a>
      			 <div class="dropdown-menu bg-dark" aria-labelledby="navbarDropdownTopoLink">
		        	<ul>
		        		<li>
		        			<a class="dropdoawn-item text-light" href="<c:url value="/topo/topos/" />">Tous les topos</a>
		        		</li>
		        		<li>
		        			<a class="dropdoawn-item text-light " href="<c:url value="/topo/creertopo/" />">Ajouter un topo</a>
		        		</li>
		        	</ul>
		        </div>
     		</li>
      		
      		<sec:authorize access="authenticated" var="authenticatedVar" />
      		
      		<c:choose>
      			<c:when test="${!authenticatedVar}">
      				<li class="nav-item">
        				<a class="nav-link" href="<c:url value="/inscription/" />">Sign up</a>
      				</li>
					<li class="nav-item">
        				<a class="nav-link" href="<c:url value="/login" />">Sign in</a>
      				</li>
      			</c:when>
      			<c:otherwise>
      				<li class="nav-item">
        				<a class="nav-link" href="<c:url value="#" />">Votre espace personnel</a>
      				</li>
      			</c:otherwise>
      		</c:choose>
    	</ul>
    	<ul class="navbar-nav ml-auto ">
      			<c:if test="${authenticatedVar}">
					<li class="nav-item ">
			      		<p class="navbar-text">
			      			<sec:authentication property="name" var="authVar"/>
			      			<c:if test="${authVar != null}"> Vous êtes connecté en tant que  <span class="text-warning">${authVar}</span> </c:if>
			      			
			      			<a id="logout" href="#">Logout</a>
			      		</p>
			   
			      		<c:url value="/logout" var="logoutVar"/>
			      		
			      		<form id="logout_form" action="${logoutVar}" method="POST">
			      			<sec:csrfInput/>
			      		</form>
      				</li>
      			</c:if>
      		<!-- param /main?logout=true passé lors du logout -->
      		<c:if test="${param.logout != null }">
				<p class="text-success">You have successfully been logged out.</p>
			</c:if>
    	</ul>
    	
  	</div>
</nav>

<!-- JAVA SCRIPT -->
<script type="text/javascript">
	$(document).ready(function(){
	
		$("#logout").click(function(e){
			e.preventDefault();
			$("#logout_form").submit();
		});
		
		 $(".dropdown-toggle").dropdown();
	});
	
	$('.dropdown-toggle').dropdown();
	

</script>



