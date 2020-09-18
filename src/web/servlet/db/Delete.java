package web.servlet.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Delete")
public class Delete extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 클라이언트가 보낸 한글을 안깨지게 처리
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String resultString = "<h3 style='color:red'>문제가 발생하여 추가하지 못했습니다!</h3>";
		try {

			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/scott?characterEncoding=UTF-8&serverTimezone=UTC";

			// DB 접속할 계정
			String user = "scott";
			String password = "scott";

			String query = "delete from customer where num = ?";

			// 1.Driver 등록
			Class.forName(driver); // 객체를 생성하는 문장. 객체 생성이 유연해

			// 2. Connection 생성 - network 연결
			Connection con = DriverManager.getConnection(url, user, password);

			// 3.Statement 생성
			PreparedStatement stat = con.prepareStatement(query);
			stat.setString(1, request.getParameter("num"));

			// 4.Query 실행
			int result = stat.executeUpdate();

			// 5. 결과 처리
			if (result != 0) {
				resultString = "<h3>성공적으로 삭제되었습니다!</h3>";
			}
			
			// 6. 마무리
			stat.close();
			con.close();
			response.sendRedirect("Start"); // ()안에 지정된 화면으로 페이지가 넘어감
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.println("<html><body>");
			out.println("<h1>customer insert</h1>");
			out.println(resultString);
			out.println("<a href=Start>초기화면으로</a>");
			out.println("</body></html>");
		}
	}

}
