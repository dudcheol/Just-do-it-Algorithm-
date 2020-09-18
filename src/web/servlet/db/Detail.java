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

@WebServlet("/Detail")
public class Detail extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try {
			String driver = "com.mysql.cj.jdbc.Driver"; // jar파일 안에 들어있던 파일이었음.
			// 만약, db가 oracle로 바뀐다면 여기있는 driver를 바꿔주면 됨!

			// jdbc url
			// jdbc:protocol 이름, mysql:db 종류, localhost:db server 주소, 33306:db port no,
			// scott:schema 이름, ?부터는 db와 관련된 옵션
			String url = "jdbc:mysql://localhost:3306/scott?characterEncoding=UTF-8&serverTimezone=UTC";

			// DB 접속할 계정
			String user = "scott";
			String password = "scott";

			String query = "select num, name, address from customer where num = ?"; // + asc:오름차순, + desc:내림차순

			// 1.Driver 등록
			Class.forName(driver); // 객체를 생성하는 문장. 객체 생성이 유연해

			// 2. Connection 생성 - network 연결
			Connection con = DriverManager.getConnection(url, user, password);

			// 3.Statement 생성
			PreparedStatement stat = con.prepareStatement(query);
			stat.setString(1, request.getParameter("num"));

			// 4.Query 실행
			ResultSet rs = stat.executeQuery();

			// 5. 결과 처리
			out.println("<html><body>");
			out.println("<h1>customer detail</h1>");
			out.println("<table>");
			out.println("<thead><tr style='background:blue; color:white; text-align:center'>");
			out.println("<td>번호</td>");
			out.println("<td>이름</td>");
			out.println("<td>주소</td>");
			out.println("</tr></thead>");

			// 초기의 rs는 제목을 가리키고 있음
			rs.next(); // rs.next() : 실제 데이터를 호출하기 위해선 반드시 호출할 것!

			int num = rs.getInt(1); // column의 index는 1부터 시작

			String name = rs.getString("name"); // column의 이름을 주어도 괜찮음. 하지만 index접근이 속도가 더 빠름!
			String address = rs.getString(3);

			out.println("<tr>");
			out.println("<td>" + num + "</td>");
			out.println("<td>" + name + "</td>");
			out.println("<td>" + address + "</td>");
			out.println("</tr>");

			out.println("</table>");

			out.println("<a href=Start>초기화면으로</a>");
			out.println("<a href=Delete?num=" + num + ">삭제하기</a>");
			out.println("</body></html>");

			// 6. 마무리
			rs.close();
			stat.close();
			con.close();
		} catch (Exception e) {

		}
	}

}
