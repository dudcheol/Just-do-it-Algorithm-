<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
String id = request.getParameter("id");
String pass = request.getParameter("pass");

if(id.equals("tommy") && pass.equals("12345")){%>
success<%}else{%>error<%}%>