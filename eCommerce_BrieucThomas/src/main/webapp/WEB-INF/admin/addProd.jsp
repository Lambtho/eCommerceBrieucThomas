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
		<h1>Ajout d'un Produit</h1>
	</div>
	<br />

	<h1>Formulaire Ajout/Edit</h1>
	<form:form method="POST" action="soumettreAddProduit"
		commandName="produitForm">
	
		<!-- ModelAttribute ou modelAttribute -->

		<table>
		<tr>
				<td>${produitForm.idProduit}</td>
				<td><form:input path="idProduit"/></td>

			</tr>
			<tr>
				<td><form:label path="designation"> Designation</form:label></td>
				<td><form:input path="designation" /></td>
				<td><form:errors path="designation" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:input path="description" /></td>
				<td><form:errors path="description" cssStyle="color:red" /></td>
			</tr>

<tr>
			<td><form:label path="categorie">Categorie</form:label></td>
			<td><select name="idCategorie">
			<c:forEach items="${listeCategorie}" var="cat">
				<option value="${cat.idCategorie}">${cat.nomCategorie}</option>
			</c:forEach>
			</select></td>
			<td><form:errors path="categorie" cssStyle="color:red" /></td>
		
	</tr>		
			<tr>
				<td><form:label path="prix"> Prix</form:label></td>
				<td><form:input path="prix" /></td>
				<td><form:errors path="prix" cssStyle="color:red" />
				</td>
			</tr>
			
			<tr>
				<td><form:label path="quantite"> Quantité</form:label></td>
				<td><form:input path="quantite" /></td>
				<td><form:errors path="quantite" cssStyle="color:red" />
				</td>
			</tr>
			
			<tr>
				<td><input type="submit" value="submit"></td>
			</tr>
		</table>

	</form:form>
	<br />


</body>
</html>