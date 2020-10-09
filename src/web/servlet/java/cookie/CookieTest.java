package web.servlet.java.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieTest")
public class CookieTest extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	  throws IOException, ServletException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();		

		//클라이언트의 요청 정보에서부터 모든 쿠키 정보를 알아냄
		Cookie[] cookies = request.getCookies();

		if(cookies == null || cookies.length == 0) { //cookie 없다
			out.println("<html><body>");
			out.println("<HR>");		
			out.println("<h1>no cookie... </h1>");			
			out.println("</body></html>");

		}else{ //cookie 있다
			out.println("<html><body><center><h2>쿠키테스트</h2></center>");
			
			for(int i=0; i < cookies.length; i++) {			
				String cookieName = cookies[i].getName(); 	//쿠키이름
				String cookieValue = cookies[i].getValue(); //쿠키 값
				
				out.println(cookieName+":"+cookieValue +"<BR>");
			}			
			out.println("<HR>");		
			out.println("<a href=CookieTest>visit again</a>");			
			out.println("</body></html>");
		}		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
	
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		//클라이언트로부터 받은 정보를 쿠키에 저장시켜서 나중에 서버에서 확인할 수 있게 함
		Cookie idCookie = new Cookie("login", id);
		idCookie.setMaxAge(60*60*24*365);//cookie 

		Cookie passCookie = new Cookie("pass", pass);
		passCookie.setMaxAge(-1);// 브라우저를 닫으면 쿠키가 사라짐
		
		//응답정보안에 쿠키 저장
		response.addCookie(idCookie);
		response.addCookie(passCookie);

		out.println("<html><body>");
		out.println("<h1>cookie saved!</h1>");
		out.println("<hr><a href=CookieTest>visit again</a>"); // Get방식
		out.println("</body></html>");   
	}

}





