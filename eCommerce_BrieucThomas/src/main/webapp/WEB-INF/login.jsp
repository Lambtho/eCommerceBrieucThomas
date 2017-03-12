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
<title>Login</title>
<link href="<c:url value="/resources/css/styleAdmin.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</head>

<body>

	<div class="titreLogin">
		<h1>Espace administrateur</h1>
		<h2>Veuillez vous identifier</h2>
	</div>

	<br />

	<div>
		<form action="j_spring_security_check" method="POST" class="formLogin">
			<table class="panelLogin">
				<tr>
					<td>Login :</td>
					<td><input type="text" name="j_username" /></td>
				</tr>

				<tr>
					<td>Password :</td>
					<td><input type="password" name="j_password" /></td>
				</tr>

				<tr>
					<td><a href="${pageContext.request.contextPath}">Retour</a></td>
					<td><input type="submit" value="Connexion" /></td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>