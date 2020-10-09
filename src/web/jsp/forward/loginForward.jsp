<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- id, pass 받아온 후에 체크해서 맞으면 welcome.jsp로 화면 이동시키고 틀리면 login.jsp로 이동 --%>

	<%
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");

	if (id.equals("tom") && pass.equals("1111")) {
	%>
		<jsp:forward page="welcome.jsp"/>
			<%-- <jsp:param value="<%=id%>" name="id" /> 사실이거 없어도됨--%>
			<%-- <jsp:param value="<%=pass%>" name="pass" /> --%>
		<%-- </jsp:forward> --%>
	<%
		} else {
	%>
		<jsp:forward page="login.jsp"/>
	<%
		}
	%>

	<br>
	<a href="login.jsp">back</a>
</body>
</html>