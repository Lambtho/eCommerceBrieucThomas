<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>

<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</head>

<body>


	<!-- 	Insertion du header -->
	<!-- 	<ui:insert> -->
	<!-- 		<ui:include src="/resources/templates/headerAdmin.xhtml" /> -->
	<!-- 	</ui:insert> -->

	<br />
	<div class="titreAdmin">
		<h1>Accueil</h1>
	</div>
	<br />

	<!-- Affichage de la liste des produits -->
	<table>
		<tr>
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
				<th><img src="/imagesAffich/image${produit.idProduit}.jpg"/></th>
				<th>${produit.idProduit}</th>
				<th>${produit.designation}</th>
				<th>${produit.description}</th>
				<th>${produit.prix}</th>
				<th>${produit.quantite}</th>
				<th>${produit.categorie.nomCategorie}</th>
				<th><a href="admin/soumettreSupProduit?id_param=${produit.idProduit}">Supprimer</a></th>
				<th><a href="admin/formModifProduit?id_param=${produit.idProduit}">Modifier</a></th>
			</tr>
			</c:forEach>
	</table>
</body>
</html>