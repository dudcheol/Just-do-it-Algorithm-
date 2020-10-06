<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>set : 변수 선언</h1>
<c:set var="num1" value="${123}"/>
<c:set var="num2" value="${100}"/>
num1 + num2 = <c:out value="${num1+num2}"/><br>
num1 + num2 = ${num1+num2}
</body>
</html>