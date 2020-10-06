<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
	<c:forEach begin="1" end="5" step="1" var="i">
		${i}. hello<hr>
	</c:forEach>

	<p>
	<h1>구구단을 외자</h1>
	<c:set var="num" value="5"></c:set>
	<c:forEach begin="1" end="9" step="1" var="i">
		${num} * ${i} = ${i*num}<hr>
	</c:forEach>
</body>
</html>