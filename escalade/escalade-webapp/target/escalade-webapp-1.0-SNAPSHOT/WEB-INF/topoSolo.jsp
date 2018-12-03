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
    	
    	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment-with-locales.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
    	
        <title>Topo ${topo.nom}</title>
    </head>
    
    <body>
    <c:import url="/inc/navBar.jsp"/>
		<div class="bg_topoSolo text-light">
			<div class="container-fluid h-100">  
		       	<br/>
		        
			    <div class="row h-100 ">
			       	<div class="col-md-3">
			       		<div class="container bg-dark rounded pt-2 pb-3 " id="presentation_topoSolo">
				       		<p>
								<h3>Topo <c:out value="${topo.nom}"/> </h3>
					       	</p>
							<%-- <img alt="photo" src="<c:url value="/inc/img/escalade.png"/>"> --%>
							<p>
								Nom du topo : <c:out value="${topo.nom}"/><br/>
								Présentation : <c:out value="${topo.presentation}"/><br/>
							   	Site : <c:out value="${topo.site.nom}"/><br/>
							</p>
			       		</div>
					</div>
					<div class="col-md-4 offset-md-1 d-flex align-items-center justify-content-center">
						<form method="POST" action="<c:url value="/topo"/>"> 
							
							<h3 class="pl-3">Demande de location</h3>
							<div id="demandelocation_block">
								<input type="hidden" name="topo_id" value="${ topo.id }" />
							    <div class='col-md-12'>
							        <div class="form-group">
							           <div class="input-group date" id="datetimepicker7" data-target-input="nearest">
							                <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker7" name="dateDebutLocationDemande"/>
							                <div class="input-group-append" data-target="#datetimepicker7" data-toggle="datetimepicker">
							                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
							                </div>
							            </div>
							        </div>
							    </div>
							    <div class='col-md-12'>
							        <div class="form-group">
							           <div class="input-group date" id="datetimepicker8" data-target-input="nearest">
							                <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker8" name="dateFinLocationDemande"/>
							                <div class="input-group-append" data-target="#datetimepicker8" data-toggle="datetimepicker">
							                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
							                </div>
							            </div>
							        </div>
							    </div>
								<sec:csrfInput/>
							</div>
							<div class="pl-3">
								<span class="erreur">${tm.erreurs['dateDebutLocationDemande']}</span>
								<c:choose>
									<c:when test="${empty tm.erreurs['dateDebutLocationDemande']}"><div class="erreur text-success mt-2"> ${ tm.result } </div></c:when>
									<c:otherwise><div class="erreur mt-2"> ${ tm.result } </div></c:otherwise>
								</c:choose><br/>
								<button type="submit" class="btn btn-warning">Valider</button>
								<button type="reset"  class="btn btn-primary">Annuler</button>
							</div>
						</form>	
					
					</div>
					
					<div class="col-md-4 ">
						<div class="card float-right mr-4" id="card_topoSolo">
					   		<c:choose>
					   			<c:when test="${not empty topo.listLocationTopo }">
							   		<div class="card-header">
							   			Prochaines locations 
							   		</div>
								   	<ul class="list-group list-group-flush">
								   		<c:forEach items="${topo.listLocationTopo }" var="locationTopoVar">
									   		<c:if test="${locationTopoVar.accepte == true }">
									   			<li class="list-group-item text-dark">du ${ locationTopoVar.dateDebutLocationFormat} au ${locationTopoVar.dateFinLocationFormat } </li>
									   		</c:if>
								   		</c:forEach>
								   	</ul>
					   			</c:when>
					   			<c:otherwise>
					   				<div class="card-header" >
							   			Prochaines locations 
							   		</div>
							   		<li class="list-group-item text-dark"><c:out value="Aucune"/></li>
					   			</c:otherwise>
					   		</c:choose>
					   	
					   	</div> 
					</div>
				</div>
				<script type="text/javascript">
				    $(function () {
				        $('#datetimepicker7').datetimepicker({
			    		    
				        	format: 'DD MM YYYY HH:mm',
			    		  });
				        $('#datetimepicker8').datetimepicker({
				        	format: 'DD MM YYYY HH:mm',
				            useCurrent: false
				        });
				     
				        $("#datetimepicker7").on("change.datetimepicker", function (e) {
				            $('#datetimepicker8').datetimepicker('minDate', e.date);
				        });
				        $("#datetimepicker8").on("change.datetimepicker", function (e) {
				            $('#datetimepicker7').datetimepicker('maxDate', e.date);
				        });
				    });
				</script>
			</div>
		</div> 
    </body>
</html>