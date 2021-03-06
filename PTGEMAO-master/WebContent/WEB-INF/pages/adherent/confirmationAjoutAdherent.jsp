<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="titre" value="Confirmation de l'ajout d'un adhérent" scope="request" />
<%@ page import="fr.gemao.view.Pattern"%>
<c:import url="/inc/head.inc.jsp" />

<c:import url="/inc/header.inc.jsp" />
<c:import url="/inc/menu.inc.jsp" />

<script src="<c:url value="/js/modifierAdherent.js"/>"></script>
<h1>Confirmation</h1>
<p class="offset text-success">L'adhérent <c:out value="${adherent['prenom']}" /> <c:out value="${adherent['nom']}" /> a bien été ajouté.</p>
<a class="offset btn" href="<c:url value="<%=Pattern.ACCUEIL%>"/>">Retour</a>
<c:import url="/inc/footer.inc.jsp" />
