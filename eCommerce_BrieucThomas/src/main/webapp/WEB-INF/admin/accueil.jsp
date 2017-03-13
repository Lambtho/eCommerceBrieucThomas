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
<link href="<c:url value="/resources/css/styleAdmin.css" />"
	rel="stylesheet">
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
				<th><a
					href="${pageContext.request.contextPath}/admin/soumettreSupProduit?id_param=${produit.idProduit}">
						<button type="button" class="btn btn-primary"
							data-target="${pageContext.request.contextPath}/admin/soumettreSupProduit?id_param=${produit.idProduit}"
							data-whatever="@mdo">Suppression</button>
				</a></th>
				<th><button type="button" class="btn btn-primary"
						data-toggle="modal"
						data-target="#exampleModal${produit.idProduit}"
						data-whatever="@mdo">Modification</button></th>
			</tr>

		</c:forEach>
	</table>

	<c:forEach var="produit" varStatus="i" items="${listeProduitAjout}">
		<div class="modal fade" id="exampleModal${produit.idProduit}"
			tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">Modification</h4>
					</div>
					<div class="modal-body">
						<form:form method="POST"
							action="${pageContext.request.contextPath}/admin/soumettreAddProduit"
							enctype="multipart/form-data">

							<!-- ModelAttribute ou modelAttribute -->
							<table class="formAjout">
								<tr>
									<td>${produit.idProduit}</td>
									<td><input name="idProduit" value="${produit.idProduit }"
										style="display: none;" /></td>
								<tr>
									<td><label>Designation</label></td>
									<td><input name="designation"
										value="${produit.designation }" /></td>
								</tr>
								<tr>
									<td><label>Description</label></td>
									<td><input name="description"
										value="${produit.description }" /></td>
								</tr>

								<tr>
									<td><label>Categorie</label></td>
									<td><select name="idCategorie">
											<c:forEach items="${listeCategorie}" var="cat">
												<option value="${cat.idCategorie}">${cat.nomCategorie}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><label> Prix</label></td>
									<td><input name="prix" value="${produit.prix}" /></td>
								</tr>

								<tr>
									<td><label> Quantité</label></td>
									<td><input name="quantite" value="${produit.quantite }" /></td>
								</tr>

								<tr>
									<td>Image</td>
									<td><input type="file" name="file" /></td>
								</tr>

							</table>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Ajout/Modification</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<div class="modal fade" id="ajoutCat" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">Ajout Catégorie</h4>
				</div>
				<div class="modal-body">

					<form:form method="POST"
						action="${pageContext.request.contextPath}/admin/cat/soumettreAddCat"
						commandName="addCategorie">

						<!-- ModelAttribute ou modelAttribute -->

						<table class="formAjout">

							<tr>
								<td><label> Nom </label></td>
								<td><input name="nomCategorie" /></td>
							</tr>
							<tr>
								<td><label>Description</label></td>
								<td><input name="description" /></td>
							</tr>
						</table>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Ajout/Modification</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>