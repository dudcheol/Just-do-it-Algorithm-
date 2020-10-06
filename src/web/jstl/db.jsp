<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<body>
	<%-- 지시어 include : copy & paste --%>
	<%@ include file="dbinfo.jsp"%>

	<%-- JSP에서 DB작업은 보통 하지 않는다 --%>
	<sql:query var="rs" dataSource="${ds}">
		select * from customer order by num
	</sql:query>

	<center>
		<h1>customer data</h1>
		<table border="1">
			<c:forEach var="row" items="${rs.rows}">
				<tr>
					<td>${row.num}</td>
					<td>${row.name}</td>
					<td>${row.address}</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>