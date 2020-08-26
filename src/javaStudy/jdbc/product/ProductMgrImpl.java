package javaStudy.jdbc.product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Impl 객체 (인터페이스를 implements한 객체)
// == DAO 객체 (Data Access Object : db에 접근해서 crud 작업을 하는 객체)
public class ProductMgrImpl implements IProductmgr {
    private ArrayList<Product> list;
    private String driver, url, user, password, query;

    private static ProductMgrImpl productMgr;

    private ProductMgrImpl() {
        list = new ArrayList<>();

        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/scott?characterEncoding=UTF-8&serverTimezone=UTC";
        user = "scott";
        password = "scott";
    }

    public static ProductMgrImpl getInstance() {
        if (productMgr == null) {
            productMgr = new ProductMgrImpl();
        }
        return productMgr;
    }

    @Override
    public int add(Product p) {
        int ret = 0;
        query = "insert into product values(?, ?, ?, ?, ?, ?)";
        try {
            Connection con = connectJDBC();
            PreparedStatement pstat = con.prepareStatement(query);
            pstat.setInt(1, p.getNum());
            pstat.setString(2, p.getName());
            pstat.setString(3, p.getMaker());
            pstat.setString(4, p.getType());
            pstat.setInt(5, p.getPrice());
            pstat.setInt(6, p.getQty());
            ret = pstat.executeUpdate();
            closeJDBC(pstat, con);
        } catch (SQLException throwables) {
            if (throwables instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("상품번호는 유일한 번호여야 합니다. 다시 입력해주세요.");
            } else System.out.println("상품을 추가하는데 실패했습니다.");
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
        }
        return ret;
    }

    @Override
    public List<Product> findAll() {
        query = "select * from product";
        try {
            Connection con = connectJDBC();
            PreparedStatement pstat = con.prepareStatement(query);
            ResultSet rs;
            list = parseResultSet(rs = pstat.executeQuery());
            closeJDBC(rs, pstat, con);
        } catch (SQLException throwables) {
            System.out.println("상품을 받아오지 못했습니다.");
            list.clear();
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
        }
        return list;
    }

    @Override
    public List<Product> findByName(String name) {
        query = "select * from product where name like ?";
        ArrayList<Product> ret = new ArrayList<>();
        name = "%" + name + "%";
        try {
            Connection con = connectJDBC();
            PreparedStatement pstat = con.prepareStatement(query);
            pstat.setString(1, name);
            ResultSet rs;
            ret = parseResultSet(rs = pstat.executeQuery());
            closeJDBC(rs, pstat, con);
        } catch (SQLException throwables) {
            System.out.println("상품 검색 중 오류가 발생했습니다.");
            ret.clear();
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
        }
        return ret;
    }

    @Override
    public List<Product> findByPrice(int price) {
        query = "select * from product where price<=?";
        ArrayList<Product> ret = new ArrayList<>();
        try {
            Connection con = connectJDBC();
            PreparedStatement pstat = con.prepareStatement(query);
            pstat.setInt(1, price);
            ResultSet rs;
            ret = parseResultSet(rs = pstat.executeQuery());
            closeJDBC(rs, pstat, con);
        } catch (SQLException throwables) {
            System.out.println("상품 검색 중 오류가 발생했습니다.");
            ret.clear();
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
        }
        return ret;
    }

    @Override
    public Product findByNum(int num) {
        query = "select * from product where num=?";
        ArrayList<Product> ret = new ArrayList<>();
        try {
            Connection con = connectJDBC();
            PreparedStatement pstat = con.prepareStatement(query);
            pstat.setInt(1, num);
            ResultSet rs;
            ret = parseResultSet(rs = pstat.executeQuery());
            closeJDBC(rs, pstat, con);
        } catch (SQLException throwables) {
            System.out.println("상품 검색 중 오류가 발생했습니다.");
            ret.clear();
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
        }
        return ret.isEmpty() ? null : ret.get(0);
    }

    @Override
    public boolean removeByNum(int num) {
        query = "delete from product where num=?";
        int ret = 0;
        try {
            Connection con = connectJDBC();
            PreparedStatement pstat = con.prepareStatement(query);
            pstat.setInt(1, num);
            ret = pstat.executeUpdate();
            closeJDBC(pstat, con);
        } catch (SQLException throwables) {
            System.out.println("상품 삭제 중 오류가 발생했습니다.");
            ret = 0;
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
        }
        return ret == 0 ? false : true;
    }

    @Override
    public boolean updatePrice(int num, int price) {
        query = "update product set price=? where num=?";
        int ret = 0;
        try {
            Connection con = connectJDBC();
            PreparedStatement pstat = con.prepareStatement(query);
            pstat.setInt(1, price);
            pstat.setInt(2, num);
            ret = pstat.executeUpdate();
            closeJDBC(pstat, con);
        } catch (SQLException throwables) {
            System.out.println("상품 업데이 중 오류가 발생했습니다.");
            ret = 0;
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
        }
        return ret == 0 ? false : true;
    }

    @Override
    public List<Product> findByPrice(String type, int price1, int price2) {
        query = "select * from product where type = ? and price between ? and ?";
        ArrayList<Product> ret = new ArrayList<>();
        try {
            Connection con = connectJDBC();
            PreparedStatement pstat = con.prepareStatement(query);
            pstat.setString(1, type);
            pstat.setInt(2, price1);
            pstat.setInt(3, price2);
            ResultSet rs;
            ret = parseResultSet(rs = pstat.executeQuery());
            closeJDBC(rs, pstat, con);
        } catch (SQLException throwables) {
            System.out.println("상품 검색 중 오류가 발생했습니다.");
            ret.clear();
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
        }
        return ret;
    }

    private ArrayList<Product> parseResultSet(ResultSet rs) throws SQLException {
        ArrayList<Product> ret = new ArrayList<>();

        while (rs.next()) {
            int num = rs.getInt(1);
            String name = rs.getString(2);
            String maker = rs.getString(3);
            String type = rs.getString(4);
            int price = rs.getInt(5);
            int qty = rs.getInt(6);

            ret.add(new Product(num, name, maker, type, price, qty));
        }

        return ret;
    }

    // 매 쿼리마다 JDBC 연결과 해제를 하는 이유?
    // 웹에서 connection작업을 진행할 때, connection을 하나만 만들어놓고 사용하게 된다면
    // 웹에서는 클라이언트가 많을 것인데 많은 사람들이 하나의 connetion을 사용하게 되면 문제가 생겼을 때
    // connection연결이 끊기면 다른 사용자들도 모두 DB작업을 할 수 없게 된다
    private Connection connectJDBC() throws SQLException {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("예상치 못한 오류가 발생했습니다. 다시 시도해주세요.");
        } catch (Exception e) {
            System.out.println("연결 실패!");
        }
        return con;
    }

    private void closeJDBC(ResultSet rs, PreparedStatement pstat, Connection con) throws SQLException {
        if (rs != null) rs.close();
        if (pstat != null) pstat.close();
        if (con != null) con.close();
    }

    private void closeJDBC(PreparedStatement pstat, Connection con) throws SQLException {
        if (pstat != null) pstat.close();
        if (con != null) con.close();
    }
}
