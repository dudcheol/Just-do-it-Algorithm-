package web.servlet.java.basic;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LifeCycleTestServlet")
public class LifeCycleTestServlet extends HttpServlet {
	
	public LifeCycleTestServlet() {
		System.out.println("constructor");
	}
	
	// 서블릿이 생성된 후에 맨처음 요청에 의해 한번만 호출(서비스메소드 실행 전에 준비작업, 초기화 작업...)
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
	}

	// 실행하던 서블릿을 메모리에서 삭제하기 전에 마지막으로 한 번 호출되는 메서드(마무리 작업 내용)
	public void destroy() {
		System.out.println("destroy");
	}

	// 요청이 있을 때마다 실행
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet...33");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
	}

}
