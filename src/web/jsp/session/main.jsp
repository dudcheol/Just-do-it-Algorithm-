<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	text-align: center;
}

table {
	border: double;
	margin: auto;
	text-align: center;
}
</style>
</head>
<body>
<%
	String id = (String)session.getAttribute("id");
	String msg = "";
	String loginText = "로그인";
	String loginUrl = "login.jsp";
	
	if(id != null){
		loginText = "로그아웃";
		loginUrl = "logout.jsp";
		msg = "welcome, " + id + "! ";
	}
%>
	<h1>Customer Data</h1>
	<span id="id-area"><%= msg %></span><a href=<%= loginUrl %>><%= loginText %></a>
	<table>
		<tbody>
			<tr>
				<td>111</td>
				<td>dada</td>
			</tr>
		</tbody>
	</table>

	<a href="#">새고객 등록</a>
</body>
</html>