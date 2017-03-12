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
		<h1>Ajout d'une Categorie</h1>
	</div>
	<br />
<h1>Formulaire Ajout/Edit</h1>
	<form:form method="POST" action="soumettreAddCat"
		commandName="addCategorie">
	
		<!-- ModelAttribute ou modelAttribute -->

		<table>
		
			<tr>
				<td><form:label path="nomCategorie"> nom Categorie</form:label></td>
				<td><form:input path="nomCategorie" /></td>
				<td><form:errors path="nomCategorie" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:input path="description" /></td>
				<td><form:errors path="description" cssStyle="color:red" /></td>
			</tr>


			<tr>
				<td><input type="submit" value="submit"></td>
			</tr>
		</table>

	</form:form>
	<br />
</body>
</html>