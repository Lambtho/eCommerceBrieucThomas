<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:p="http://primefaces.org/ui"
	xmlns:ui="http://java.sun.com/jsf/facelets">
<head>
<title>Accueil</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</head>
<body>

	<!-- 	Insertion du header -->
	<%@include file="/resources/templates/headerClient.jsp"%>

	<div class="barreRecherche">
		<!-- Barre de recherche -->
		<form method="get" action="${pageContext.request.contextPath}/client">
			<select id="categorie" class="rechercheCat" name="idCategorie">
				<c:forEach items="${listeCategories}" var="cat">
					<option value="${cat.idCategorie}">${cat.nomCategorie}</option>
				</c:forEach>
			</select> <input class="rechercheBarre" type="text" name="keyword"
				value="${keyword}" /> <input type="submit" value="Rechercher" class="rechercheBouton">
			<p class="descCat">${categorie.nomCategorie}
				${categorie.description}</p>
		</form>
	</div>
	<!-- Affichage de la liste des produits -->
	<table class="afficheProd">
		<tr class="tableHeader">
			<th>Image</th>
			<th>Id</th>
			<th>Désignation</th>
			<th>Description</th>
			<th>Prix</th>
			<th>Catégorie</th>
			<th></th>

		</tr>

		<c:forEach var="produit" items="${listeProduits}">
			<tr>
				<td><img
					src="${pageContext.request.contextPath}/client/photo?idProduit=${produit.idProduit}"
					width="100px" /></td>

				<th>${produit.idProduit}</th>
				<th>${produit.designation}</th>
				<th>${produit.description}</th>
				<th>${produit.prix}</th>
				<th>${produit.categorie.nomCategorie}</th>
				<th><c:if test="${produit.quantite!=0}">
						<a
							href="${pageContext.request.contextPath}/client/ajoutPanier?idProd=${produit.idProduit}&idCategorie=${idCategorie}&keyword=${keyword}">Panier</a>
					</c:if> <c:if test="${produit.quantite==0}">
						<a>Plus de stock</a>
					</c:if></th>
			</tr>
		</c:forEach>

	</table>
</body>
</html>