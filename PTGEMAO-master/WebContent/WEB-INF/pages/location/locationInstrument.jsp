<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="fr.gemao.view.Pattern"%>

<c:set var="titre" value="Location d'un instrument" scope="request" />

<c:import url="/inc/head.inc.jsp" />

<c:import url="/inc/header.inc.jsp" />
<c:import url="/inc/menu.inc.jsp" />

    <%-- Location Interne --%>
    <%-- Refonte totale de la page --%>
	<h1>Location d'instrument interne</h1>

			<form id="location" method="post" action="#">
				<fieldset>
					<legend>Informations Adhérent</legend>
							<div>
								<label for="Nom_Adherent">Nom :</label>
								<input type="text" name="nom_adhérent">
							</div>
							
							<div>
								<label for="Prenom_Adherent">Prenom :</label>
								<input type="text" name="Prenom_adhérent">
							</div>
							
							<div>
								<label for="Adresse_Adherent">Adresse :</label>
								<input type="text" name="Adresse_adhérent">
							</div>
				</fieldset>			
				<fieldset>
					<legend>Instruments</legend>
					
					<c:choose>
						<c:when test="${empty requestScope.listeInstument}">
							<div>
								<label for="Nom_instrument">Nom de l'instrument :</label>
								<select name="Nom_instrument">
									<c:forEach items="${requestScope.listeInstument}" var="instrument">
										<option value="<c:out value="${instrument['idMateriel']}" />"><c:out
												value="${instrument['typeMateriel']}" /></option>
									</c:forEach>
								</select>
							</div>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${empty requestScope.listeInstument}">
							<div>
								<label for="Numero_instrument">n° de l'instrument :</label>
								<select name="Numero_instrument">
									<c:forEach items="${requestScope.listeInstument}" var="instrument">
										<option value="<c:out value="${instrument['idMateriel']}" />"><c:out
												value="${instrument['idMateriel']}" /></option>
									</c:forEach>
								</select>
							</div>
						</c:when>
					</c:choose>
						
				</fieldset>
				
				<fieldset class='align-center no-border'>
					<input type="submit" class="btn" value="Louer" />
				</fieldset>
	
			</form>

<c:import url="/inc/footer.inc.jsp" />