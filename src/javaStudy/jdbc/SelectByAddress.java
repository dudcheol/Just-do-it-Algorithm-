package javaStudy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
JDBC 실행절차>>>

1. JDBC driver 등록
2. Connection(network) 객체 얻어내기:Driver Manager의 getConnection()를 이용
3. Statement객체 얻어내기: Connection 객체의 createStatement()를 이용
4. SQL을 DB에 전송: Statement객체의 executeQuery()나 executeUpdate()를 이용
5. SQL문장 실행결과 처리: ResultSet 객체의 getXXX()를 이용 
6. 자원 반납: Statement객체와 Connection객체의 close()호출
*/
public class SelectByAddress {

	public static void main(String[] args) throws Exception{
		String driver = "com.mysql.cj.jdbc.Driver";
	
		//jdbc url		
		String url = "jdbc:mysql://localhost:3306/scott?characterEncoding=UTF-8&serverTimezone=UTC";

		//DB 접속할 계정
		String user = "scott";
		String password = "scott";
		
		String query = "select num, name, address from customer where address == 'seoul' order by num";
		
		//1.Driver 등록
		Class.forName(driver); // 객체를 생성하는 문장. 객체 생성이 유연해
		Class.forName("com.test.Car"); // com.test.Car c = new com.test.Car();
		
		//2. Connection 생성 - network 연결
		Connection con = DriverManager.getConnection(url, user, password);
		
		//3.Statement  생성
		Statement stat = con.createStatement();
		
		//4.Query  실행
		ResultSet rs = stat.executeQuery(query);
		
		//5. 결과 처리
		while(rs.next()) {
			int num = rs.getInt(1); // column의 index는 1부터 시작
			String name = rs.getString("name"); // column의 이름을 주어도 괜찮음. 하지만 index접근이 속도가 더 빠름!
			String address = rs.getString(3);

			System.out.println(num + "--" + name + "--" + address);

		}
		
		//6. 마무리
		rs.close();
		stat.close();
		con.close();

	}

}
















