<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--     Ajout d ela taglib form de spring -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Formulaire Ajout/Edit</h1>
	<form:form method="POST" action="soumettreAddProduit"
		commandName="addProduit">
		<!-- ModelAttribute ou modelAttribute -->

		<table>
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
			<td><form:select path="categorie">
			<c:forEach items="${listeCategorie}" var="cat">
				<option value="${cat.idCategorie}">${cat.nomCategorie}</option>
			</c:forEach>
			</form:select></td>
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