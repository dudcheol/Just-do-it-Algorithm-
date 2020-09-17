package web.servlet.java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormTestServlet")
public class FormTestServlet extends HttpServlet {
	// doGet: 클라이언트가 get방식으로 요청했을 때 서비스해주는 함수(url창에서 주소를 직접 입력할 때, link로 호출할 때,
	// form태그에서 method=get 일때
	// doPost: 클라이언트가 post방식으로 요청했을 때 서비스해주는 함수(form태그에서 method=post일때)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트 보낼 컨텐트(내용)의 타입을 지정(MIME TYPE)
		response.setContentType("text/html;charset=utf-8");

		// 출력용 스트림 얻어냄
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		out.println("<html><body>");
		out.println("<h1>로그인 정보</h1>");
		out.println("Welcome! " + id + "<br>");
		out.println("Your password is " + pass + "<br>");
		
		// checkbox 받기
//		String[] hobbies = request.getParameterValues("hobby");
//		if(hobbies != null) {
//			for (String hobby : hobbies) {
//				out.println(hobby + "<hr>");
//			}
//		}
		
		out.println(request.getParameter("hobby"));
		
		
		out.println("</body></html>");
	}

}
