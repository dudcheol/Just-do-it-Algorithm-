package javaStudy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
JDBC 실행절차>>>

1. JDBC driver 등록
2. Connection(network) 객체 얻어내기:Driver Manager의 getConnection()를 이용
3. Statement객체 얻어내기: Connection 객체의 createStatement()를 이용
4. SQL을 DB에 전송: Statement객체의 executeQuery()나 executeUpdate()를 이용
5. SQL문장 실행결과 처리: ResultSet 객체의 getXXX()를 이용 
6. 자원 반납: Statement객체와 Connection객체의 close()호출
*/
public class Insert {

    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";

        //jdbc url
        String url = "jdbc:mysql://localhost:3306/scott?characterEncoding=UTF-8&serverTimezone=UTC";

        //DB 접속할 계정
        String user = "scott";
        String password = "scott";

        String query = "insert into customer values(?,?,?)";

        //1.Driver 등록
        Class.forName(driver); // 객체를 생성하는 문장. 객체 생성이 유연해
        Class.forName("com.test.Car"); // com.test.Car c = new com.test.Car();

        //2. Connection 생성 - network 연결
        Connection con = DriverManager.getConnection(url, user, password);

        //3.Statement  생성
        PreparedStatement pstat = con.prepareStatement(query);

        pstat.setInt(1, 729);
        pstat.setString(2, "Genie");
        pstat.setString(3, "osaka");

        //4.Query  실행
        pstat.executeUpdate();

        //5. 마무리
        pstat.close();
        con.close();
    }

}
















