<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="fr.gemao.view.Pattern"%>

<c:set var="titre" value="Location interne d'un instrument"
	scope="request" />

<c:import url="/inc/head.inc.jsp" />

<c:import url="/inc/header.inc.jsp" />
<c:import url="/inc/menu.inc.jsp" />

<h1>Location interne d'un instrument</h1>
<c:choose>
<c:when test="${empty validation }">
	<c:choose >
		<c:when test="${empty resultat}">
			<form id="location" method="post" action="#">
					<legend>Instrument</legend>
					<c:choose>
						<c:when test="${!empty requestScope.listeCategorie}">
							<div>
								<label for="categorie">Catégorie :</label>
								<select name="categorie">
									<c:forEach items="${requestScope.listeCategorie}" var="categorie">
										<option value="${categorie['idCategorie']}"><c:out
												value="${categorie['libelleCat']}" /></option>
									</c:forEach>
								</select>
							</div>
						</c:when>
						<c:otherwise>
							
								<div><span class='text-label'>Catégorie : </span><span><%= session.getAttribute("nomCategorie") %></span></div>
								
								<br>
								<label for="instrument">Instrument : </label>
									<br>
									<table class="tablesorter-blue  pure-table">
										<thead>
											<tr><th>Identifiant</th><th>Désignation</th><th>Marque</th><th>Etat</th><th>Fournisseur</th><th>Type matériel</th><th>Date achat</th><th>Choix</th></tr>
										</thead>
										<c:forEach items="${listeMateriel}" var="instrument">
											<tr>
											<c:forEach items="${instrument.value}" var="mat">
												<td><c:out value="${mat}"></c:out></td>
											</c:forEach>
												<td><input type="radio" name="nomDesignation" value="${instrument.key }" checked>Choisir</td>
											</tr>
										</c:forEach>
									</table>
						</c:otherwise>	
						</c:choose>
				<fieldset class='align-center no-border'>
					<a href="<c:url value="<%= Pattern.ACCUEIL %>"/>">Annuler</a>
					<input id="valider" type="submit" value="Valider" />
				</fieldset>
			</form>
		</c:when>
		<c:otherwise>
			<form method="post" action="#">
				<fieldset>
					<legend>Validation des informations</legend>
					<label>Categorie : </label><span id="nomCategorie"><%= session.getAttribute("nomCategorie") %></span><br>
					<label>Instrument : </label>
					<table class="tablesorter-blue  pure-table">
						<thead>
							<tr><th>Désignation</th><th>Numéro de série</th><th>Type de matériel</th><th>date d'achat</th><th>valeur achat</th><th>valeur reapprovisionnement</th><th>est déplacable</th><th>observation</th><th>Etat</th></tr>
						</thead>
						<tr>
							<c:forEach items="${nomInstrument}" var="mat">
								<td><c:out value="${mat}"></c:out></td>
							</c:forEach>
						</tr>
					</table><br>
					<label>Adhérent : </label><span id="nomAdherent"><%= session.getAttribute("nomAdherent") %></span><br>
					<label>Date d'emprunt : </label><span id="debutLocation"><%= session.getAttribute("debutLocation") %></span><br>
					<label>Date d'echéance : </label><span id="finLocation"><%= session.getAttribute("finLocation") %></span><br>
					<label>Montant : </label><span id="leMontant"><%= session.getAttribute("montant") %> euros par an</span><br>
					<label>Caution : </label><span id="laCaution"><%= session.getAttribute("caution") %> euros</span><br>
					<label>Imprimer : </label><input type="radio" name="imprimer" value="Oui" checked> Oui
  											  <input type="radio" name="imprimer" value="Non"> Non<br>
				</fieldset>
				<fieldset class='align-center no-border'>
						<a href="<c:url value="<%= Pattern.ACCUEIL %>"/>">Annuler</a>
						<input type="submit" value="Valider" />
				</fieldset>
			</form>
		</c:otherwise>
	</c:choose>
	</c:when>
	<c:otherwise>
		<p class="offset text-success"><%= request.getAttribute("validation") %></p><br>
		<a class="offset btn" href="<c:url value="<%= Pattern.ACCUEIL %>"/>">Retour</a>
	</c:otherwise>
</c:choose>
<c:import url="/inc/footer.inc.jsp" />