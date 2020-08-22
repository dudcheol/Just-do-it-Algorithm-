package javaStudy.jdbc.product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Impl 객체 (인터페이스를 implements한 객체)
// == DAO 객체 (Data Access Object : db에 접근해서 crud 작업을 하는 객체)
public class ProductMgrImpl implements IProductmgr {
    private ArrayList<Product> list;
    private String driver, url, user, password, query;
    private Connection con;
    private PreparedStatement pstat;
    private ResultSet rs;

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
            connectJDBC();
            pstat = con.prepareStatement(query);
            pstat.setInt(1, p.getNum());
            pstat.setString(2, p.getName());
            pstat.setString(3, p.getMaker());
            pstat.setString(4, p.getType());
            pstat.setInt(5, p.getPrice());
            pstat.setInt(6, p.getQty());
            ret = pstat.executeUpdate();
            closeJDBC();
        } catch (SQLException throwables) {
            if (throwables instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("상품번호는 유일한 번호여야 합니다. 다시 입력해주세요.");
            } else System.out.println("상품을 추가하는데 실패했습니다.");
        }
        return ret;
    }

    @Override
    public List<Product> findAll() {
        query = "select * from product";
        try {
            connectJDBC();
            pstat = con.prepareStatement(query);
            list = parseResultSet(rs = pstat.executeQuery());
            closeJDBC();
        } catch (SQLException throwables) {
            System.out.println("상품을 받아오지 못했습니다.");
            list.clear();
        }
        return list;
    }

    @Override
    public List<Product> findByName(String name) {
        query = "select * from product where name like ?";
        ArrayList<Product> ret = new ArrayList<>();
        name = "%" + name + "%";
        try {
            connectJDBC();
            pstat = con.prepareStatement(query);
            pstat.setString(1, name);
            ret = parseResultSet(rs = pstat.executeQuery());
            closeJDBC();
        } catch (SQLException throwables) {
            System.out.println("상품 검색 중 오류가 발생했습니다.");
            ret.clear();
        }
        return ret;
    }

    @Override
    public List<Product> findByPrice(int price) {
        query = "select * from product where price<=?";
        ArrayList<Product> ret = new ArrayList<>();
        try {
            connectJDBC();
            pstat = con.prepareStatement(query);
            pstat.setInt(1, price);
            ret = parseResultSet(rs = pstat.executeQuery());
            closeJDBC();
        } catch (SQLException throwables) {
            System.out.println("상품 검색 중 오류가 발생했습니다.");
            ret.clear();
        }
        return ret;
    }

    @Override
    public Product findByNum(int num) {
        query = "select * from product where num=?";
        ArrayList<Product> ret = new ArrayList<>();
        try {
            connectJDBC();
            pstat = con.prepareStatement(query);
            pstat.setInt(1, num);
            ret = parseResultSet(rs = pstat.executeQuery());
            closeJDBC();
        } catch (SQLException throwables) {
            System.out.println("상품 검색 중 오류가 발생했습니다.");
            ret.clear();
        }
        return ret.isEmpty() ? null : ret.get(0);
    }

    @Override
    public boolean removeByNum(int num) {
        query = "delete from product where num=?";
        int ret = 0;
        try {
            connectJDBC();
            pstat = con.prepareStatement(query);
            pstat.setInt(1, num);
            ret = pstat.executeUpdate();
            closeJDBC();
        } catch (SQLException throwables) {
            System.out.println("상품 삭제 중 오류가 발생했습니다.");
            ret = 0;
        }
        return ret == 0 ? false : true;
    }

    @Override
    public boolean updatePrice(int num, int price) {
        query = "update product set price=? where num=?";
        int ret = 0;
        try {
            connectJDBC();
            pstat = con.prepareStatement(query);
            pstat.setInt(1, price);
            pstat.setInt(2, num);
            ret = pstat.executeUpdate();
            closeJDBC();
        } catch (SQLException throwables) {
            System.out.println("상품 업데이 중 오류가 발생했습니다.");
            ret = 0;
        }
        return ret == 0 ? false : true;
    }

    @Override
    public List<Product> findByPrice(String type, int price1, int price2) {
        query = "select * from product where type = ? and price between ? and ?";
        ArrayList<Product> ret = new ArrayList<>();
        try {
            connectJDBC();
            pstat = con.prepareStatement(query);
            pstat.setString(1, type);
            pstat.setInt(2, price1);
            pstat.setInt(3, price2);
            ret = parseResultSet(rs = pstat.executeQuery());
            closeJDBC();
        } catch (SQLException throwables) {
            System.out.println("상품 검색 중 오류가 발생했습니다.");
            ret.clear();
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

    private void connectJDBC() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("예상치 못한 오류가 발생했습니다. 다시 시도해주세요.");
        }
        con = DriverManager.getConnection(url, user, password);
    }

    private void closeJDBC() throws SQLException {
        if (rs != null) rs.close();
        if (pstat != null) pstat.close();
        if (con != null) con.close();
    }
}
