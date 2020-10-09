package web.servlet.java.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트 보낼 컨텐트(내용)의 타입을 지정(MIME TYPE)
		response.setContentType("text/html;charset=utf-8");

		// 출력용 스트림 얻어냄
		PrintWriter out = response.getWriter();

		out.println("<html><body>");
		out.println("<h1>message servlet</h1>");
		out.println("<a href=Hello>헬로서블릿으로 가기</a>");
		out.println("</body></html>");
	}

}
