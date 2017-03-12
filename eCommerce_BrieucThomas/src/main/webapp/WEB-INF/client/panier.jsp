<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:p="http://primefaces.org/ui"
	xmlns:ui="http://java.sun.com/jsf/facelets">
<head>
<title>Panier</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</head>
<body>

	<!-- 	Insertion du header -->
	<%@include file="/resources/templates/headerClient.jsp"%>

	<br />
	<br />

	<!-- Affichage de la liste des produits -->
	<table class="afficheProd">
		<tr class="tableHeader">
			<th>Image</th>
			<th>Id</th>
			<th>Désignation</th>
			<th>Description</th>
			<th>Prix</th>
			<th>Catégorie</th>
			<th>Quantité</th>
			<th></th>
		</tr>

		<c:forEach var="produit" items="${listeProduits}">
			<tr>
				<th><img src="/imagesAffich/image${produit.idProduit}.jpg" /></th>
				<th>${produit.idProduit}</th>
				<th>${produit.designation}</th>
				<th>${produit.description}</th>
				<th>${produit.prix}</th>
				<th>${produit.categorie.nomCategorie}</th>
				<th>${produit.quantite}</th>
				<th><c:if test="${produit.quantite!=0}">
						<a
							href="${pageContext.request.contextPath}/client/supprimerPanier?idProd=${produit.idProduit}">Supprimer</a>
					</c:if> <c:if test="${produit.quantite==0}">
						<a>Plus de stock</a>
					</c:if></th>
			</tr>
		</c:forEach>
	</table>

	<div class="globalCommande">
		<div class="total">
			<img
				src="${pageContext.request.contextPath}/resources/images/basket.svg"
				width="100px" />
			<p>Total de votre commande ${totalPanier}</p>
		</div>

		<div class="formCommande">
			<h3>Passer commande</h3>

			<form:form method="post"
				action="${pageContext.request.contextPath}/client/passerCommande">
				<div>
					<table>
						<tr>
							<td>Nom :</td>
							<td><form:input id="nom" path="nomClient" /></td>
						</tr>
						<tr>
							<td>Adresse :</td>
							<td><form:input id="adresse" path="Adresse" /></td>
						</tr>
						<tr>
							<td>eMail :</td>
							<td><form:input id="email" path="email" /></td>
						</tr>
						<tr>
							<td>Téléphone :</td>
							<td><form:input id="telephone" path="tel" /></td>
						</tr>
					</table>
				</div>
				<input value="Commander" type="submit" ${desactiveCmd}
					class="boutonCmd" />
			</form:form>
		</div>
	</div>
</body>
</html>