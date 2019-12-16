package cn.knight.fly.test.crawler;

/**
 * @Package cn.knight.fly.test.crawler
 * @Description
 * @Author Fly
 * @Date 2019-11-07 9:42
 * @Version V1.0
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    private Connection con;
    private PreparedStatement sta;
    private ResultSet rs;
    private static final String DRIVER= "com.mysql.cj.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/poems?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&autoReconnect=true&useSSL=false";
    private static final String USER="root";
    private static final String PASSWORD="123456";
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        try {
            con =DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("连接失败");
        }
        return con;
    }

    public int update(String sql) {
        int count = 0;
        con = getConnection();
        try {
            con.prepareStatement(sql);
            count = sta.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            this.close();
        }
        return count;
    }
    /**
     * 获取结果集
     *
     * @param sql
     * @return
     */
    public ResultSet query(String sql) {
        con = getConnection();
        try {
            con.prepareStatement(sql);
            rs = sta.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 关闭资源
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (sta != null) {
                sta.close();
                sta = null;
            }
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
