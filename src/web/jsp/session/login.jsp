<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="loginCheck.jsp" id="loginForm" method="post">
		<fieldset>
			<legend>로그인하기</legend>
			<ul>
				<li>아이디: <input type="text" id="id" name="id"></li>
				<li>비밀번호: <input type="password" id="pass" name="pass"></li>
			</ul>
		</fieldset>
		<input type="submit" value="로그인">
		<a href="Start.jsp">메인으로</a>
	</form>
</body>
</html>