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
</head>
<body>

	<!-- 	Insertion du header -->
	<%@include file="/resources/templates/headerClient.jsp"%>

	<br />
	<br />

	<!-- Affichage de la liste des produits -->
	<table class="afficheProd">
		<tr>
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
		<div style="margin-bottom: 20px">
			<div class="total">
				<img src="images/basket.svg" width="100px" />
				<p>Total de votre commande ${totalPanier}</p>
			</div>

			<div class="formCommande">
				<h3>Passer commande</h3>
				<form:form method="post"
					action="${pageContext.request.contextPath}/client/passerCommande">
					<div>
						Nom : <form:input id="nom" path="nomClient" /> Adresse : <form:input id="adresse"
							path="Adresse" /> eMail : <form:input id="email" path="email" />
						Téléphone : <form:input id="telephone" path="tel" />
					</div>

					<input value="Commander" type="submit"
						${desactiveCmd} class="boutonCmd" />
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>