<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>당신의 성적 결과는...</h1>
<c:set var="score" value="${param.score}"/>
<c:if test="${score >= 90 }">
pass!
</c:if>
<c:if test="${score < 90 }">
fail!
</c:if>

</body>
</html>