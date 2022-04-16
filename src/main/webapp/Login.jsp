<%@page import="java.io.StringReader"%>
<%@page import="jakarta.json.JsonObject"%>
<%@page import="jakarta.json.JsonReader"%>
<%@page import="jakarta.json.Json"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form action= "login"  method="post">
		
		
		<input type=text name=login placeholder="Email" /><br>
		<input type=password name=password placeholder="Password" /><br>		
		<input type=submit value="IN"  />
	</form>
</body>
</html>