<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- id, pass 받아온 후에 화면에 출력 --%>

welcome, <%= request.getParameter("id") %>!
your password is... <%= request.getParameter("pass") %>

<br>
<a href="login.jsp">back</a>
</body>
</html>