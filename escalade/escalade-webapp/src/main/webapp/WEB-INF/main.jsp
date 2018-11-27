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
    	 <style type="text/css">
	        body, html {
				height: 100%;
			}
			.bg {
			
			  background-image: url("https://wpblink.com/sites/default/files/wallpaper/sports/73611/climbing-wallpapers-hd-73611-5012556.png");
			
			  height: 100%;
		
			  background-position: center;
			  background-repeat: no-repeat;
			  background-attachment: fixed;
			  background-size: cover;
			}
        </style>
    	
    </head>
    
    <body>
	    <c:import url="/inc/navBar.jsp"/>
    	<div class="bg">
		   
		        <br/>
		        
		       <p>
		       	<h1>Welcome to Escale'add website !</h1>
		       	<h2>Le site qui vous permet de partager sur l'escalade</h2>
		       </p> 
		       <div class="container-fluid" >
		       
		        <br/>
		        <p>
		        	Nombre d'utilisateur inscrits : ${nbreCompteUtilisateur}
		        </p>
		     
		        	<h3>Tous les sites</h3>
		        	<c:forEach items="${allSites}" var="allSitesVar" >
				        <div class="container">
				        	Nom du site : <c:out value="${ allSitesVar.nom }"/><br/>
				        	Département : <c:out value="${ allSitesVar.dept }"/><br/>
				        	Lieu : <c:out value="${ allSitesVar.lieu }"/><br/>
				        	<c:if test="${ allSitesVar.description != null }">
				        		Description : <c:out value="${ allSitesVar.description }"/><br/>
				        	</c:if>
				        	Date de création : <c:out value="${ allSitesVar.dateCreation }"/>
				        	<div>
				        		<c:forEach items="${allSitesVar.secteurs}" var="secteursVar">
							        <ul>
							       		<li>
							    			Secteur : <c:out value="${secteursVar.nom}"/>
							        		<c:forEach items="${secteursVar.voies}" var="voiesVar3">
							        			<ul>
							        				<li>
							        					Voie : <c:out value="${voiesVar3.nom}"></c:out>
							        					<ul>
							        						<li>
							        							Hauteur : <c:out value="${voiesVar3.hauteur }"/>
							        						</li>
							        						<li>
							        							Nombre de points : <c:out value="${voiesVar3.nombrePoints }"/>
							        						</li>
							        						<li>
							        							Nombre de longueurs : <c:out value="${voiesVar3.nombreLongueur}"/>
							        						</li>
							        						<li>
							        							Cotation : <c:out value="${voiesVar3.cotation }"/>
							        						</li>
							        					</ul>
							        				</li>
							        			</ul>
							        		</c:forEach>
							        	</li>
							        </ul>
						        </c:forEach>
							</div> 
				        </div>
		        	</c:forEach>  
		      </div>  
		    
    	</div>
    </body>
</html>