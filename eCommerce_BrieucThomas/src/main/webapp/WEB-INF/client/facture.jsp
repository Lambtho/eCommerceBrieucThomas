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
<title>Facture</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</head>
<body>
	<!-- 	Insertion du header -->
	<!-- 	Insertion du header -->
	<%@include file="/resources/templates/headerClient.jsp"%>
	<object data="${lienFacture}" type="text/html" width="95%"
		height="800px" class="affichePdf" />
</body>
</html>