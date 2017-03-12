<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--     Ajout d ela taglib form de spring -->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
				<td><form:label path="designation_prd"> Designation</form:label></td>
				<td><form:input path="designation_prd" /></td>
				<td><form:errors path="designation_prd" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td><form:label path="description_prd">Description</form:label></td>
				<td><form:input path="description_prd" /></td>
				<td><form:errors path="description_prd" cssStyle="color:red" /></td>
			</tr>
			<tr>
				<td><form:label path="categorie_id_fk"> Categorie</form:label></td>
				<td><form:select path="categorie_id_fk" /></td>
				<td><form:errors path="categorie_id_fk" cssStyle="color:red" />
				</td>
			</tr>
			
			<tr>
				<td><form:label path="prix_prd"> Prix</form:label></td>
				<td><form:input path="prix_prd" /></td>
				<td><form:errors path="prix_prd" cssStyle="color:red" />
				</td>
			</tr>
			
			<tr>
				<td><form:label path="quantite_prd"> Quantité</form:label></td>
				<td><form:input path="quantite_prd" /></td>
				<td><form:errors path="quantite_prd" cssStyle="color:red" />
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