<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Accueil</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
	<link href="<c:url value="/resources/css/tether.sass" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.1.1.js" />"></script>
<script src="<c:url value="/resources/js/utils.js" />"></script>
<script src="<c:url value="/resources/js/tether.js" />"></script>
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
				value="${keyword}" /> <input type="submit" value="Rechercher"
				class="rechercheBouton">
			<p class="descCat">${categorie.nomCategorie}
				${categorie.description}</p>
		</form>
	</div>
	<div class="container">
		<div class="row row-eq-height" style="height: 350px">
			<div class="col-md-3" id="carouselTitre1"
				style="background-color: white; opacity: 0.6">
				<h2 style="text-align: center; padding-top: 75px; height: 350px">Marre
					de votre vieille tire?</h2>
			</div>
			<div id="carouselExampleControls" class="carousel slide col-md-6"
				data-ride="carousel">
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<img class="d-block img-fluid" style="height: 350px"
							src="/eCommerce_BrieucThomas/resources/images/2015063011431753.jpg"/>
						<div class="carousel-caption d-none d-md-block">
							<h3 style="color: black;">Bienvenue Sur notre Site</h3>
							<h5 style="color: black;">achetez une voiture en quelques
								click!</h5>

						</div>
					</div>
					<c:forEach var="car" items="${listeCarousel}" varStatus="status">
						<div class="carousel-item">

							<img class="d-block img-fluid" style="height: 350px"
								src="${pageContext.request.contextPath}/client/photo?idProduit=${car.idProduit}" />
							<div class="carousel-caption d-none d-md-block">
								<h3 style="color: black;background-color: rgba(200,200,200,0.3); border-radius: 8px;">${car.designation}</h3>


							</div>
						</div>
					</c:forEach>

					<!--     <div class="carousel-item"> -->
					<%--       <img class="d-block img-fluid" src="${pageContext.request.contextPath}/client/photo?idProduit=21" alt="Third slide"> --%>
					<!--     </div> -->
				</div>
				<a class="carousel-control-prev" href="#carouselExampleControls"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleControls"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
			<div class="col-md-3 " id="carouselTitre2"
				style="background-color: white; opacity: 0.6">
				<h2 style="text-align: center; padding-top: 75px">Consulter nos
					tendances du moment</h2>
			</div>
		</div>
	</div>

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
	<br />
	<!-- 	Insertion du footer -->
	<%@include file="/resources/templates/footerClient.jsp"%>
</body>
</html>