<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="fr.gemao.view.Pattern"%>

<c:set var="titre" value="Liste des locations" scope="request" />
<c:import url="/inc/head.inc.jsp" />
<c:import url="/inc/header.inc.jsp" />
<c:import url="/inc/menu.inc.jsp" />

<c:choose>
	<c:when test="${empty resultat}">
		<form id="listeLocation" method="post" action="#">
			<h1>Choix de la catégorie</h1>
			<fieldset>
				<legend>Catégorie</legend>
				<c:choose>
					<c:when test="${!empty requestScope.listeCategorie}">
						<div>
							<label for="categorie">Catégorie :</label> <select
								name="categorie">
								<c:forEach items="${requestScope.listeCategorie}"
									var="categorie">
									<option value="<c:out value="${categorie['idCategorie']}" />"><c:out
											value="${categorie['libelleCat']}" /></option>
								</c:forEach>
							</select>
						</div>
					</c:when>

				</c:choose>
				<input type="submit" class="btn" value="Suivant" />
			</fieldset>
		</form>
	</c:when>
</c:choose>

<c:if test="${empty vide}">
		<form method="post" action="#" id="listeLocation2">
			<h1>Liste des locations</h1>
			<table class='tablesorter-blue pure-table'>
				<thead>
					<tr>
						<th>Nom</th>
						<th>Prénom</th>
						<th>Date de naissance</th>
						<th>Designation</th>
						<th>Type Matériel</th>
						<th>Date Emprunt</th>
						<th>Date Échéance</th>
						<th>Date retour</th>
						<th>Etat début</th>
						<th>État fin</th>
						<th>Montant annuel</th>
					</tr>
				</thead>
			
				<tbody>
				<c:forEach items="${listeLocation}" var="location">
					<tr>
						<td class="listeNom"><c:out
								value="${location.getPersonne().getNom()}" /></td>
						<td class="listePrenom"><c:out
								value="${location.getPersonne().getPrenom()}" /></td>
						<td class="listeDatenaissance"><c:out
								value="${location.getPersonne().getDateNaissance()}" /></td>
						<td class="listDesignation"><c:out
								value="${location.getMateriel().getDesignation().getLibelleDesignation()}" /></td>
						<td class="listDesignation"><c:out
								value="${location.getMateriel().getDesignation().getLibelleDesignation()}" /></td>
						<td class="listDateEmprunt"><c:out
								value="${location.getDateEmprunt()}" /></td>
						<td class="listDateEcheance"><c:out
								value="${location.getDateEcheance()}" /></td>
						<td class="listDateRetour"><c:out
								value="${location.getDateRetour()}" /></td>
						<td class="listEtatDebut"><c:out
								value="${location.getEtatDebut().getLibelleEtat()}" /></td>
						<td class="listEtatFin"><c:out
								value="${location.getEtatFin().getLibelleEtat()}" /></td>
						<td class="listMontantAnnuel"><c:out
								value="${location.getMontant()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</form>
</c:if>
