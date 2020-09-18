<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<%!String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/scott?characterEncoding=UTF-8&serverTimezone=UTC";
	String user = "scott";
	String password = "scott";
	String query = "delete from customer where num = ?";%>

<%
	// 1.Driver 등록
Class.forName(driver); // 객체를 생성하는 문장. 객체 생성이 유연해

// 2. Connection 생성 - network 연결
Connection con = DriverManager.getConnection(url, user, password);

// 3.Statement 생성
PreparedStatement stat = con.prepareStatement(query);
stat.setString(1, request.getParameter("num"));

// 4.Query 실행
stat.executeUpdate();

stat.close();
con.close();

response.sendRedirect("Start.jsp");
%>