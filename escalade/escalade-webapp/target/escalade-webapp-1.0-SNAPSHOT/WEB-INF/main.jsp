<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        
        <meta charset="utf-8" />
   		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	
    	<c:import url="/inc/bootstrapAndJQueryImport.jsp"/>
        <title>Home</title>
    </head>
    
    <body>
	    <c:import url="/inc/navBar.jsp"/>
    	<div class="bg_home">
    		<div class="container-fluid ">
				   <div class="row pt-3 ">
				   		<div class="col-md-10">
						       	<h1>Welcome to Escale'add website !</h1>
						       	<h2>Le site qui vous permet de partager sur l'escalade</h2>
				   		</div>
				   		<div class="col-md-2 hidden-md d-none d-lg-block text-right text-warning pr-4">	
				   			<c:out value="Nombre d'utilisateur inscrits : ${nbreCompteUtilisateur}"/> 
				   		</div>
				   </div>
				<div class="container-fluid mt-5 pt-2 pb-4 rounded" id="main_block">  
					<div class="row mt-4 ">
				   		<div class="col-md-3 offset-md-2 col-sm-12 pb-5 mr-2">
				   			<div class="row">
				   				<div class="card text-light ">
					   				<div class="card-header">
							   			<c:out value="Dernier site ajouté"/>
					   				</div>
					   				<ul class="list-group list-group-flush">
					   					<li class="list-group-item" id="home_dernier_site">
							   				<div class="col-lg-12 ">
							   					<c:out value="Nom : ${ lastSite.nom }"/>
							   				</div>
							   				<div class="col-lg-12">
							   					<c:out value="Département : "></c:out><c:out value="${ lastSite.dept }"/>
							   				</div>
							   				<div class="col-lg-12">
							   					<c:out value="Lieu : ${ lastSite.lieu }"/>
							   				</div>
							   				<c:if test="${ not empty lastSite.description }">
							   					<div class="col-lg-12">
							   						<c:out value="Description : ${ lastSite.description }"/>
							   					</div>
							   				</c:if>
							   				<c:if test="${ not empty lastSite.secteurs }">
							   					<div class="col-lg-12">
								   					<c:out value="Secteur : "/>
							   					</div>
							   					<div class="col-lg-12">
							   						<ul>
								   						<c:forEach items="${ lastSite.secteurs }" var="secteurVar">
								   							<li ><c:out value="${ secteurVar.nom}"/> </li>
								   							<c:if test="${ not empty secteurVar.voies}" >
												   				<c:out value="Voie : "/>
											   			 		<ul>
											   						<c:forEach items="${ secteurVar.voies }" var="voieVar">
											   							<li ><c:out value="Nom : ${ voieVar.nom}"/></li>
											   						</c:forEach>
										   						</ul> 
								   							</c:if>
								   						</c:forEach>
							   						</ul>
							   					</div>
							   				</c:if>
						   				</li>
					   				</ul>
				   				</div>
				   			</div>
				   		</div>
				   		<div class="col-md-4 col-sm-12">
				   			<div class="row" id="derniertopo_block">
				   				<div class="card text-light">
					   				<div class="card-header">
							   			<c:out value="Dernier topo mise à disposition"/>
					   				</div>
					   				<ul class="list-group list-group-flush" >
					   					<li class="list-group-item" id="home_dernier_topo">
					   						<div class="col-lg-12 ">
							   					<c:out value="Nom : ${ lastTopo.nom }"/>
							   				</div>
							   				<div class="col-lg-12 ">
							   					<c:out value="Site couvert : ${ lastTopo.site.nom }"/>
							   				</div>
							   				<c:if test="${ not empty lastTopo.presentation }">
							   					<div class="col-lg-12 ">
							   						<c:out value="Site presentation : ${ lastTopo.presentation }"/>
							   					</div>
							   				</c:if>
							   				<br/>
							   				<div class="col-lg-12 ">
							   					<c:out value="Par : ${ lastTopo.createur }"/>
							   				</div>
					   					</li>
				   					</ul>
				   				</div>
				   			</div>	
				   			<div class="row mt-5">
				   				<div class="card text-light">
				   					<div class="card-header">
							   			<c:out value="Derniers messages laissés"/>
					   				</div>
						   			<ul class="list-group list-group-flush">
							   			<c:forEach items="${lastCom }" var="lastComVar">
							   				<li class="list-group-item "id="home_derniers_mess">
							   					<c:out value="${ lastComVar.auteur } ~ sur le site ${ lastComVar.site_nom }  : "></c:out> <c:out value="${ lastComVar.contenu }"/>
							   				</li>
							   			</c:forEach>
						   			</ul>
				   				</div>
				   			</div>
				   		</div>
					</div>
				 </div>
			</div>  
    	</div>
    </body>
   
</html>