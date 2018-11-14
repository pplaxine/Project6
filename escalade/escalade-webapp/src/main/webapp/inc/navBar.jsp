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
     			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Sites
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		        	<a class="dropdoawn-item" href="<c:url value="/sites/" />">Sites</a>
		          	<a class="dropdown-item" href="#">Action</a>
		          	<a class="dropdown-item" href="#">Another action</a>
		          	<a class="dropdown-item" href="#">Something else here</a>
		        </div>
        
      			
     		 </li>
      		<li class="nav-item">
      			<a class="nav-link" href="<c:url value="/pret/" />">Espace pr�t de topo</a>
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
			      			<c:if test="${authVar != null}"> Vous �tes connect� en tant que  <span class="text-warning">${authVar}</span> </c:if>
			      			
			      			<a id="logout" href="#">Logout</a>
			      		</p>
			   
			      		<c:url value="/logout" var="logoutVar"/>
			      		
			      		<form id="logout_form" action="${logoutVar}" method="POST">
			      			<sec:csrfInput/>
			      		</form>
      				</li>
      			</c:if>
      		<!-- param /main?logout=true pass� lors du logout -->
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
</script>	
