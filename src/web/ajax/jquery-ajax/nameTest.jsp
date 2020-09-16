<%
	String name=request.getParameter("name");
	boolean isLong = (name.length() > 5);
%><%=isLong%>
