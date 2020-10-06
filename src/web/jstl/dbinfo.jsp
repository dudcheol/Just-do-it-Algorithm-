<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<body>
	<%-- 접속할 db 정보 등록 : connectionPool --%>
	<sql:setDataSource 
		var="ds"
		driver="com.mysql.cj.jdbc.Driver" 
		url="jdbc:mysql://localhost:3306/scott?characterEncoding=UTF-8&serverTimezone=UTC" 
		user="scott" 
		password="scott"/>
</body>
</html>