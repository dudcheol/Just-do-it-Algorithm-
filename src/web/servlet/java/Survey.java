package web.servlet.java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Survey")
public class Survey extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String dept = request.getParameter("dept");
		String employee = request.getParameter("employee");
		String comment = request.getParameter("comment");
		String[] ides = request.getParameterValues("ide");

		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>dept : " + dept + "<hr>");
		out.println("employee : " + employee + "<br>");
		out.println("comment : " + comment + "<br>");
		out.println("use ide : ");
		if(ides != null) {
			for (int i = 0; i < ides.length; i++) {
				out.print(ides[i]);
				if(i != ides.length-1) 
					out.print(", ");
			}
		} else {
			out.println("none");
		}
		out.println("</body></html>");
	}

}
