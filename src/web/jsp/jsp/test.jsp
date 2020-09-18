<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	// 선언
	int num = 100; // 모든 클라이언트가 공유하는 전역 변수 
%>

<%
	int count = 100; // client마다 하나씩 할당받아 사용하는 지역변수 (doGet 서비스 메서드 안에 생성)
%>

num : <%= ++num %><br> <%-- num은 새로고침해도 증가하고 count는 증가하지 않음. 왜? num만 필드로 들어가서 --%>
count : <%= ++count %>
</body>
</html>