<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    

<%@ page import="java.util.ArrayList"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Liste des users</h1>
<nav style="text-align: right;"><a href = "logout">Disc</a></nav>
<table border=1>
	<tr>
		<th>id</th>
		<th>username</th>
		<th>email</th>
		<th>&nbsp;</th>
		<th>&nbsp;</th>
	</tr>
	
	<c:forEach items="${weaker}" var="w" >
		<tr>
			<td>${ w._id }</td>
			<td>${ w.username }</td>
			<td>${ w.email }</td>
			
			<!-- <td><a href = "fiche?id=${ e.id }">fiche</a></td>
			<td><a href = "edit?id=${ e.id }">modifier</a></td>
			<td><a href = "delete?id=${ e.id }">supprimer</a></td>-->
		</tr>
	</c:forEach>
</table>
</body>
</html>