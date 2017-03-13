<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!--     Ajout d ela taglib form de spring -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:p="http://primefaces.org/ui"
	xmlns:ui="http://java.sun.com/jsf/facelets">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
<link href="<c:url value="/resources/css/styleAdmin.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</head>

<body>

<%@include file="/resources/templates/headerAdmin.jsp"%>

	<br />
	<div class="titreAdmin">
		<h1>Accueil</h1>
	</div>
	<br />

	<!-- Affichage de la liste des produits -->
	<table class="afficheProd">
		<tr class="tableHeader">
			<th>Image</th>
			<th>Id</th>
			<th>Désignation</th>
			<th>Description</th>
			<th>Prix</th>
			<th>Quantité</th>
			<th>Catégorie</th>
		</tr>
		
		<c:forEach var="produit" items="${listeProduit}">
			<tr>
				<th><img
					src="${pageContext.request.contextPath}/admin/photo?idProduit=${produit.idProduit}"
					width="100px" /></th>
				<th>${produit.idProduit}</th>
				<th>${produit.designation}</th>
				<th>${produit.description}</th>
				<th>${produit.prix}</th>
				<th>${produit.quantite}</th>
				<th>${produit.categorie.nomCategorie}</th>
				<th><a href="${pageContext.request.contextPath}/admin/soumettreSupProduit?id_param=${produit.idProduit}">Supprimer</a></th>
				<th><a href="${pageContext.request.contextPath}/admin/formModifProduit?id_param=${produit.idProduit}">Modifier</a></th>
			</tr>
			</c:forEach>
	</table>

</body>
</html>