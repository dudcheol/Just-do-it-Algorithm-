<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<%!String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/scott?characterEncoding=UTF-8&serverTimezone=UTC";
	String user = "scott";
	String password = "scott";
	String query = "select num, name, address from customer where num = ?";%>

<%
	// 1.Driver 등록
Class.forName(driver); // 객체를 생성하는 문장. 객체 생성이 유연해

// 2. Connection 생성 - network 연결
Connection con = DriverManager.getConnection(url, user, password);

// 3.Statement 생성
PreparedStatement stat = con.prepareStatement(query);
stat.setString(1, request.getParameter("num"));

// 4.Query 실행
ResultSet rs = stat.executeQuery();
%>
<html><body>
			<h1>customer detail</h1>
			<table>
			<thead><tr style='background:blue; color:white; text-align:center'>
			<td>번호</td>
			<td>이름</td>
			<td>주소</td>
			</tr></thead>

<%
			// 초기의 rs는 제목을 가리키고 있음
			rs.next(); // rs.next() : 실제 데이터를 호출하기 위해선 반드시 호출할 것!

			int num = rs.getInt(1); // column의 index는 1부터 시작

			String name = rs.getString("name"); // column의 이름을 주어도 괜찮음. 하지만 index접근이 속도가 더 빠름!
			String address = rs.getString(3);
%>
			<tr>
			<td><%=num %></td>
			<td><%=name %></td>
			<td><%=address %></td>
			</tr>

			</table>

			<a href=Start.jsp>초기화면으로</a>
			<a href=Delete.jsp?num=<%=num %>>삭제하기</a>
			</body></html>

<%
	rs.close();
stat.close();
con.close();
%>