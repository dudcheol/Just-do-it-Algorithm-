package javaStudy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

// Transaction : 여러개의 쿼리가 하나의 단위로 묶여 처리되어야 하는 논리적 작업단위
public class JdbcBatch {

    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/scott?characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "scott";
        String password = "scott";
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            Statement stat = con.createStatement();

            con.setAutoCommit(false); // auto Commit 기능 off

            // 여러개의 쿼리가 전부 적용되거나 아예 적용되지 않아야 함.
            // 이중 하나는 되고 나머지는 안되는 식으로 적용된다면 문제가 발생함.
            stat.addBatch("insert into customer values(40, 'kim', 'la')");
            stat.addBatch("insert into customer values(41, 'kim', 'la')");
            stat.addBatch("insert into customer values(32, 'kim', 'la')"); // DB에 이미 32번이 존재함
            // 봐야할 포인트 : 3번째 쿼리에서 예외가 발생해서 위의 40, 41도 함께 취소된 것을 확인한다.

            stat.executeBatch(); // batch 실행
            con.commit(); // ok~ 물리적 반영

            stat.close();
        } catch (Exception e) {
            System.out.println(" 예외 발생 ");
            con.rollback(); // 메모리에 남겨있는 성공했던 쿼리 모두 취소
        } finally {
            // 마무리
            con.close();
        }
    }
}
