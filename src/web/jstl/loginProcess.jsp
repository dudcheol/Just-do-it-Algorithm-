<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
	<c:set var="id" value="${param.id}" />
	<c:set var="pass" value="${param.pass}" />

	<c:if test="${id == 'tom' &&  pass == '123'}">
		<jsp:forward page="welcome.jsp"/>
		<%-- <c:redirect url="welcome.jsp" /> --%>
	</c:if>

	<c:if test="${id ne 'tom' or  pass ne '123'}">
		<c:redirect url="login.jsp" />
	</c:if>
</body>
</html>