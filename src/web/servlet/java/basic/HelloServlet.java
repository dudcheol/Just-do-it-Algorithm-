package web.servlet.java.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @: annotation. 프로그램 실행 시 필요한 설정 정보, 환경 정보를 표시해주는 기능
//		/HelloServlet : url mapping
@WebServlet("/Hello")
public class HelloServlet extends HttpServlet {
	void go() {
	}

	// doGet(): 서비스메소드, 클라이언트로부터 요청이 들어왔을 때 응답을 만들어 내는 메소드.
	// request: 클라이언트에서 서버로 들어오는 "요청" 관련한 정보가 들어있는 객체
	// response: 서버에서 클라이언트로 나가는 "응답" 관련한 정보가 들어있는 객체
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 클라이언트 보낼 컨텐트(내용)의 타입을 지정(MIME TYPE)
		response.setContentType("text/html;charset=utf-8");

		// 출력용 스트림 얻어냄
		PrintWriter out = response.getWriter();

		out.println("<html><body>");
		out.println("<h1>hello servlet</h1>");
		out.println("<a href=MessageServlet>메시지서블릿으로 가기</a>");
		out.println("</body></html>");
	}

}
