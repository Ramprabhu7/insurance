<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="content">
		<table border="1">
			<tr>
				<th>Company Name</th>
				<th>Insured Declared Value(IDV)</th>
				<th>Premium</th>
			</tr>
			<c:forEach var="insurance" items="${ insuranceDetails}">
				<tr>
					<td>${insurance.companyName}</td>
					<td>${insurance.premium}</td>
					<td>${insurance.IDV}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>